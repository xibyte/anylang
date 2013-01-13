package anylang._exp;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class DocumentCharSequence implements CharSequence {

	private final IDocument document;
	
	public DocumentCharSequence(IDocument document) {
		this.document = document;
	}

	@Override
	public int length() {
		return document.getLength();
	}

	@Override
	public char charAt(int index) {
		try {
			return document.getChar(index);
		} catch (BadLocationException e) {
			throw new IndexOutOfBoundsException(e.getMessage() + ". index: " + index);
		}
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		try {
			return document.get(start, end - start);
		} catch (BadLocationException e) {
			throw new IndexOutOfBoundsException(e.getMessage());
		}
	}
}
