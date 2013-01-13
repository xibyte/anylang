package mlang;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.DocumentRewriteSessionEvent;
import org.eclipse.jface.text.IDocumentRewriteSessionListener;

public class RebuildPresentationRewriteSessionListener implements IDocumentRewriteSessionListener {

	private final UIReconciling reconciling;
	
	public RebuildPresentationRewriteSessionListener(UIReconciling reconciling) {
		this.reconciling = reconciling;
	}

	@Override
	public void documentRewriteSessionChanged(DocumentRewriteSessionEvent event) {
		//after heavy text operations(like text shifting) we do reconciling in UI thread
		if (event.fChangeType == DocumentRewriteSessionEvent.SESSION_STOP) {
			reconciling.setProgressMonitor(new NullProgressMonitor());
			reconciling.reconcile(false);
		}
	}

}
