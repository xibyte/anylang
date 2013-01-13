package mlang;

import html.lexer.HtmlPresentationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mlang.utils.Ranges;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

public class UIReconciling implements IReconcilingStrategy,
		IReconcilingStrategyExtension {

	private final ISourceViewer viewer;
	private final MLangContext mctx;
	private final PresentationBuilder presentationBuilder;
	private final Color defaultColor;
	private IDocument document;
	private IProgressMonitor monitor;

	public UIReconciling(ISourceViewer viewer, MLangContext mctx,
			PresentationBuilder presentationBuilder) {
		this.viewer = viewer;
		this.mctx = mctx;
		this.presentationBuilder = presentationBuilder;
		defaultColor = mctx.getColor(new RGB(0, 0, 0));
	}

	@Override
	public void setDocument(IDocument document) {
		this.document = document;
	}

	@Override
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
		reconcile();
	}

	@Override
	public void reconcile(IRegion partition) {
		reconcile();
	}

	public void reconcile() {
		reconcile(true);
	}
	
	public void reconcile(boolean async) {
		boolean DEBUG_PARTITIONG = !true;
		final List<StyleRange> presentation = new ArrayList<StyleRange>();
		final List<ITypedRegion> partitioning = new ArrayList<ITypedRegion>();
		try {
			DocumentReader reader = new DocumentReader(document);
			presentationBuilder.init(reader, presentation, partitioning, monitor);
			presentationBuilder.build();
			if (DEBUG_PARTITIONG) {
				presentation.clear();
				present(partitioning, presentation);
			}
			if (!monitor.isCanceled()) {
				Runnable runnable = new Runnable() {
					
					@Override
					public void run() {
						if (!monitor.isCanceled()) {
							setPresentation(presentation);
							setPartitioning(partitioning);
						}
					}
				};
				if (async) {
					PlatformUI.getWorkbench().getDisplay().asyncExec(runnable);
				} else {
					runnable.run();
				}
			}
		} catch (IOException e) {
			Logger.log(e);
		}
	}

	private void present(List<ITypedRegion> partitioning, List<StyleRange> result) {
		Color one = mctx.getColor(new RGB(230, 230, 250));
		Color two = mctx.getColor(new RGB(255, 248, 220));
		boolean kind = true;
		for (ITypedRegion r : partitioning) {
			if (r.equals(IDocument.DEFAULT_CONTENT_TYPE)) {
				continue;
			}
			Color color = (kind = !kind) ? one : two; 
			result.add(new StyleRange(r.getOffset(), r.getLength(), defaultColor, color, SWT.NULL));
		}
	}

 	private void setPresentation(List<StyleRange> presentation) {
		IDocument document = viewer.getDocument();
		final TextPresentation tp = new TextPresentation();
		tp.setDefaultStyleRange(new StyleRange(0, document.getLength(), defaultColor, null));
		
		int length = document.getLength();
		for (StyleRange range : presentation) {
			//TODO we need to fit the last range to length and through out the rest 
			if (Ranges.behind(length, range.start, range.length)) {
				break;
			}
			tp.addStyleRange(range);
		}
		viewer.changeTextPresentation(tp, true);
	}

	private void setPartitioning(List<ITypedRegion> partitioning) {
		IDocument document = viewer.getDocument();
		DocumentPartitioner.setPartitioning(document, partitioning);
	}

	@Override
	public void setProgressMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void initialReconcile() {
	}
}
