package mlang;

import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.presentation.PresentationReconciler;

public class DirectPresentationReconcileConfigurator implements
		PresentationReconcileConfigurator {

	private final PresentationReconciler reconciler;

	public DirectPresentationReconcileConfigurator(
			PresentationReconciler reconciler) {
		this.reconciler = reconciler;
	}

	@Override
	public void setRepairer(IPresentationRepairer repairer, String contentType) {
		reconciler.setRepairer(repairer, contentType);
	}

	@Override
	public void setDamager(IPresentationDamager damager, String contentType) {
		reconciler.setDamager(damager, contentType);
	}
}
