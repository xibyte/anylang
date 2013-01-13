package mlang;

import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;

public interface DamagerRepaierCollector {

	void setRepairer(IPresentationRepairer repairer, String contentType);

	void setDamager(IPresentationDamager damager, String contentType);
}
