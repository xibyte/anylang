package mlang;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IPositionUpdater;

public class CompositePositionUpdater implements IPositionUpdater {

	private final IPositionUpdater[] updaters;
	
	public CompositePositionUpdater(IPositionUpdater... updaters) {
		this.updaters = updaters;
	}

	@Override
	public void update(DocumentEvent event) {
		for (IPositionUpdater u : updaters) {
			u.update(event);
		}
	}

}
