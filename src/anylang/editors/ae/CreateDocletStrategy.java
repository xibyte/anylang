package anylang.editors.ae;

import mlang.Logger;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextUtilities;


public class CreateDocletStrategy implements IAutoEditStrategy {

	@Override
	public void customizeDocumentCommand(IDocument d,
			DocumentCommand c) {
		String[] delimiters = d.getLegalLineDelimiters();
		if (c.text != null) {
			int didx = TextUtilities.endsWith(delimiters, c.text);
			if (c.length == 0
					&& didx != -1) {
				
				try {
					if (isCommentComing(d, c)) {
						String del = delimiters[didx];
						c.text = del +" *" + del + " */"+c.text; 
					}
				} catch (BadLocationException e) {
					Logger.log(e);
				}
			}
		}
	}

	
	private boolean isCommentComing(IDocument d, DocumentCommand c)
			throws BadLocationException {
		int p= (c.offset == d.getLength() ? c.offset  - 1 : c.offset);
		IRegion info = d.getLineInformationOfOffset(p);

		boolean asteriskWas = false;
		
		for (int i=c.offset-1;i>=info.getOffset() && i >= 0; --i) {
			
			switch (d.getChar(i)) {
			case '*':
				asteriskWas = true;
				break;
			case '/':
				return asteriskWas; 
			default:
				return false;
			}
		}
		return false;
	}

}
