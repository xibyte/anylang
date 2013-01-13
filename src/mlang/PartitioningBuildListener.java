package mlang;

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static mlang.utils.Ranges.end;

import java.util.ArrayList;
import java.util.List;

import mlang.dom.DOM;
import mlang.dom.DOMListener;
import mlang.utils.Ranges;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.PlatformUI;

public class PartitioningBuildListener implements DOMListener, ITextListener {

	private final PartitionsExtractor extractor;
	private final ISourceViewer viewer;
	private final LangPresentationReconciler reconciler;
	private List<ITypedRegion> prev = emptyList();
	private Changes changes = new Changes();

	public PartitioningBuildListener(PartitionsExtractor extractor, ISourceViewer viewer, LangPresentationReconciler reconciler) {
		this.extractor = extractor;
		this.viewer = viewer;
		this.reconciler = reconciler;
	}

	@Override
	public void changed(DOM dom) {
		if (dom == null) {
			return;
		}
		
		ArrayList<ITypedRegion> parts = new ArrayList<ITypedRegion>(max(prev.size(), 10));
		ArrayList<ITypedRegion> zones = new ArrayList<ITypedRegion>();
		
		extractor.extract(dom.getRoot(), parts, zones);
		
		System.out.println("PARTS: " + parts);
		System.out.println("ZONES: " + zones);
		IDocument document = viewer.getDocument();
		//XXX: Need to be async exec
		DocumentPartitioner.setPartitioning(document, parts, zones);
		IDocumentPartitioner partitioner = ((IDocumentExtension3) document).getDocumentPartitioner(DocumentPartitioner.PARTITIONING);
		
		List<ITypedRegion> computed = asList(partitioner.computePartitioning(0, document.getLength()));
		
		System.out.println("COMPUTED: " + computed);
		
		IRegion damage;
		if (changes.fullDamage) {
			damage = null;
		} else {
			damage = findDamage(computed, prev);
		}
		prev = computed;
		restyle(damage);
		//--- LOG
//		DOMLogger logger = new DOMLogger();
//		ScopeTraverser.accept(dom.getRoot(), logger);
//		System.out.println(logger.log);
	}

	private IRegion findDamage(List<ITypedRegion> current,
			List<ITypedRegion> prev) {
		
		if (current.isEmpty()) {
			return Ranges.EMPTY;
		}
		
		if (prev.isEmpty() && !current.isEmpty()) {
			ITypedRegion first = current.get(0);
			ITypedRegion last = current.get(current.size() - 1);
			return Ranges.merge(first, last);
		}
		
		ITypedRegion up = null;
		ITypedRegion bottom = null;
		
		for (int i = 0; i < current.size(); ++i) {
			ITypedRegion c = current.get(i);
			if (i >= prev.size() ||  !c.getType().equals(prev.get(i).getType())) {
				up = c;
				break;
			}
		}
		
		for (int i = 0; i < current.size() && i < prev.size(); ++i) {
			ITypedRegion c = current.get(current.size() - 1 - i);
			if (c == up) {
				break;
			}
			ITypedRegion p = prev.get(prev.size() - 1 - i);
			if (!c.getType().equals(p.getType())) {
				bottom = c;
				break;
			}
		}

		if (up != null) {
			if (bottom == null) {
				return up;
			}
			
			return Ranges.merge(up, bottom);
		}
		
		return null;
	}

	private void restyle(final IRegion damage) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				IRegion workingDamage = getDamage(damage);
				changes = new Changes();
				System.out.println("DAMAGE: " + workingDamage);
				if (workingDamage != null) {
					TextPresentation presentation = reconciler.createRepairDescription(workingDamage, viewer.getDocument());
					viewer.changeTextPresentation(presentation, false);
				}
			}
			
			private IRegion getDamage(IRegion damage) {
				IDocument document = viewer.getDocument();
				
				if (changes.fullDamage) {
					return new Region(0, document.getLength());
				}
				
				if (damage != null && Ranges.isValid(damage) && damage.getLength() != 0) {
					if (changes.extent != null) {
						damage = Ranges.merge(damage, changes.extent);
					}
				} else {
					if (changes.extent == null) {
						return null;
					} else {
						damage = changes.extent;
					}
				}
				
				int dlength = document.getLength();
				if (damage.getOffset() >= dlength) {
					return null;
				}
				if (end(damage) > dlength) {
					int length = dlength - damage.getOffset();
					if (length <= 0) {
						return null;
					}
					return new Region(damage.getOffset(), length);
				} else {
					return damage;
				}
			}
			
		});
	}

	static class DOMLogger implements ScopeTraverser.Handler {

		public final StringBuilder log = new StringBuilder();
		
		@Override
		public boolean handle(EObject node) {
			int level = findLevel(node);
			for (int i = 0; i < level; ++i) {
				log.append('\t');
			}
			log.append(node).append('\n');
			return true;
		}

		private int findLevel(EObject node) {
			int level = 0;
			while (node != null) {
				++ level;
				node = node.eContainer();
			}
			return level;
		}
		
		@Override
		public void begin() {
		}

		@Override
		public void done() {
		}
	}

	@Override
	public void textChanged(TextEvent e) {
		if (e.getOffset() == 0 && e.getLength() == 0 && e.getText() == null) {
			changes.fullDamage = true;
		} else {
			MutableTypedRegion tmpRegion = new MutableTypedRegion();
			String text = e.getText();
			String rtext = e.getReplacedText();
			int delta = (text == null ? 0 : text.length()) - (rtext == null ? 0 : rtext.length());   
			
			if (delta == 0) {
				if (e.getLength() != 0) {
					tmpRegion.setOffset(e.getOffset());
					tmpRegion.setLength(e.getLength());
					changes.extent = Ranges.merge(changes.extent, tmpRegion); 
				}
			} else {
				if (delta > 0) {
					tmpRegion.setOffset(e.getOffset());
					tmpRegion.setLength(delta);
				} else {
					tmpRegion.setOffset(max(0, e.getOffset() + delta));
					tmpRegion.setLength(e.getOffset() - tmpRegion.getOffset());
				}
				changes.extent = Ranges.merge(changes.extent, tmpRegion); 
			}
		}
	}
	
	static class Changes {
		IRegion extent;
		volatile boolean fullDamage = false;
	}
}
