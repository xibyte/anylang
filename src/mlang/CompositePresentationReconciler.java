package mlang;

import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.IPresentationRepairer;

public class CompositePresentationReconciler implements IPresentationReconciler {

	private final List<IPresentationReconciler> reconcilers;

	public CompositePresentationReconciler(
			List<IPresentationReconciler> reconcilers) {
		this.reconcilers = reconcilers;
	}

	@Override
	public void install(ITextViewer viewer) {
		for (IPresentationReconciler reconciler : reconcilers) {
			reconciler.install(viewer);
		}
	}

	@Override
	public void uninstall() {
		for (IPresentationReconciler reconciler : reconcilers) {
			reconciler.uninstall();
		}
	}

	@Override
	public IPresentationDamager getDamager(String contentType) {
		return null;
	}

	@Override
	public IPresentationRepairer getRepairer(String contentType) {
		return null;
	}
}
