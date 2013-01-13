package mlang;

import html.lexer.HtmlPresentationBuilder;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationRepairer;

public class Repairer implements IPresentationRepairer {

	private final PresentationBuilder builder = new HtmlPresentationBuilder(new SchemeImpl()); 
	
	@Override
	public void setDocument(IDocument document) {
		
	}

	@Override
	public void createPresentation(TextPresentation presentation,
			ITypedRegion damage) {
		
		
		
	}

	public void setContentType(String contentType) {
		
	}
}
