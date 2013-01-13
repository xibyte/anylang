package anylang.editors.ae;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;

public class AutoParam implements IAutoEditStrategy {

	private final String contentType;
	
	public AutoParam(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public void customizeDocumentCommand(IDocument document,
			DocumentCommand c) {
		
		try {
			if (!contentType.equals(document.getContentType(c.offset - 1))) {
				return;
			}
		} catch (BadLocationException e1) {
			return;
		}
		
		if (c.text != null) {
			try {
				if (document.getChar(c.offset-1) == '=' ) {
					if (c.text.endsWith("\"")) {
						c.text += '"';
						c.caretOffset = c.offset +1;
						c.shiftsCaret = false;
					} else if (c.text.endsWith("'")) {
						c.text += '\'';
						c.caretOffset = c.offset +1;
						c.shiftsCaret = false;
					}
				}
			} catch (BadLocationException e) {
			}
		}
	}
}
