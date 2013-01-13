package anylang.editors.ae;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;

public class AutoClose implements IAutoEditStrategy {

	@Override
	public void customizeDocumentCommand(IDocument document,
			DocumentCommand c) {
		
		if (c.text != null) {
			if (c.text.endsWith("/")) {
				c.text += '}';
			} else if (c.text.endsWith("}")) {
				
				try {
					ITypedRegion part = document.getPartition(c.offset);
					int idx = part.getOffset();
					if (document.getChar(idx) == '{') {
						++ idx;
						if (document.getChar(idx) == '/') {
							return; //Don't close closed tag	
						}
						char ch = document.getChar(idx);
						StringBuilder closed = new StringBuilder(c.text).append("{/");
						while (Character.isLetterOrDigit(ch)) {
							closed.append(ch);
							try {
								ch = document.getChar(++idx);
							} catch (BadLocationException e) {
								break;
							}
						}
						closed.append('}');
						c.text = closed.toString();
						c.caretOffset = c.offset +1;
						c.shiftsCaret = false;
					}
				} catch (BadLocationException e) {
					
				}
			}
		}
	}

}
