package mlang;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.PresentationReconciler;

public class LangPresentationReconciler extends PresentationReconciler {

	public TextPresentation createRepairDescription(IRegion damage, IDocument document) {
		setDocumentToDamagers(document);
		setDocumentToRepairers(document);
		return createPresentation(damage, document);
	}
}
