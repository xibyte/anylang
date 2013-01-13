package mlang;

import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.presentation.PresentationReconciler;

public class MonoPresentationReconciler extends PresentationReconciler {

	private final Damager damager = new Damager();
	private final Repairer repairer = new Repairer(); 
	
	@Override
	public IPresentationDamager getDamager(String contentType) {
		return damager;
	}

	@Override
	public IPresentationRepairer getRepairer(String contentType) {
		repairer.setContentType(contentType);
		return super.getRepairer(contentType);
	}
}
