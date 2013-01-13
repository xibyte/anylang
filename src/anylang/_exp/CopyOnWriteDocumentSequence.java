package anylang._exp;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;

public class CopyOnWriteDocumentSequence implements CharSequence {

	private class InternalDocumentListener implements IDocumentListener {

		public void documentAboutToBeChanged(DocumentEvent event) {
			handleDocumentAboutToBeChanged();
		}

		public void documentChanged(DocumentEvent event) {
		}
	}

	public CopyOnWriteDocumentSequence(IDocument document) {
		document.addDocumentListener(documentListener);
		this.document = document;
		charSequence = new DocumentCharSequence(document);
	}
	
	private IDocument document;

	private volatile CharSequence charSequence;

	private IDocumentListener documentListener = new InternalDocumentListener();

	public void close() {
		charSequence = null;
		releaseDocument();
	}

	private void handleDocumentAboutToBeChanged() {
		IDocument document = this.document;
		if (charSequence == null || document == null)
			return;
		String content = document.get();
		synchronized (this) {
			if (charSequence == null)
				return;
			charSequence = content;
		}
		releaseDocument();
	}

	private synchronized void releaseDocument() {
		if (document != null)
			document.removeDocumentListener(documentListener);
		document = null;
		documentListener = null;
	}

	@Override
	public int length() {
		return charSequence.length();
	}

	@Override
	public char charAt(int index) {
		return charSequence.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return charSequence.subSequence(start, end);
	}
}
