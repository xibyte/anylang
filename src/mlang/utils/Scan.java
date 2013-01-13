package mlang.utils;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class Scan {

	public static int read(IDocument document, int offset) {
		if (0 > offset || offset >= document.getLength()) {
			return -1;
		}
		try {
			return document.getChar(offset);
		} catch (BadLocationException e) {
			return -1;
		}
	}
	
}
