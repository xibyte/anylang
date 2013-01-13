package mlang;

import java.io.IOException;

import mlang.dom.DOM;
import mlang.dom.DOMListener;
import mlang.dom.DOMProvider;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipselabs.mlang.models.common.Scope;

public class BuildDomReconcilingStrategy implements IReconcilingStrategy,
		IReconcilingStrategyExtension, DOMProvider {

	private final Parser parser;
	private IDocument document;
	private IProgressMonitor monitor;
	private DOM dom;
	private final ListenerList listners = new ListenerList();
	
	public BuildDomReconcilingStrategy(Parser parser) {
		this.parser = parser;
	}

	@Override
	public void setDocument(IDocument document) {
		this.document = document;
	}

	@Override
	public void setProgressMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void initialReconcile() {
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
		DocumentReader reader = new DocumentReader(document);
		try {
			Scope root = dom.getRoot();
			DOM dom = new DOM();
			parser.init(root, reader, monitor);
			parser.build();
			this.dom = dom;
			notifyDomChanged();
		} catch (Exception e) {
			Logger.log(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
	}

	private synchronized void notifyDomChanged() {
		DOM dom = this.dom;
		for (Object l : listners.getListeners()) {
			((DOMListener) l).changed(dom);
		}
	}

	@Override
	public DOM getDOM() {
		return dom;
	}

	@Override
	public void addListener(DOMListener listener) {
		listners.add(listener);
	}

	@Override
	public void removeListener(DOMListener listener) {
		listners.remove(listener);
	}
}
