package anylang._exp;

import mlang.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;

import anylang._exp.dom.Scope;


public class StructureReconcilingStrategy implements IReconcilingStrategy,
		IReconcilingStrategyExtension {

	private IDocument document;
	private IProgressMonitor monitor;
	private final ListenerList listners = new ListenerList();
	private final Lexer2 lexer;
	private final Parser2 parser;

	public StructureReconcilingStrategy(Lexer2 lexer, Parser2 parser) {
		this.lexer = lexer;
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
		CopyOnWriteDocumentSequence input = new CopyOnWriteDocumentSequence(document);
		try {
			Scope root = new Scope();
			lexer.init(input, lexer.defaultState());

			parser.init(root, monitor, lexer);
			parser.build();
			notifyDomChanged(root);
		} catch (Exception e) {
			Logger.log(e);
		} finally {
			input.close();
		}
	}

	private synchronized void notifyDomChanged(Scope root) {
		for (Object l : listners.getListeners()) {
			((StructureListener) l).changed(root);
		}
	}

	public void addListener(StructureListener listener) {
		listners.add(listener);
	}

	public void removeListener(StructureListener listener) {
		listners.remove(listener);
	}
}
