package mlang;

import static java.lang.Character.isJavaIdentifierPart;
import static mlang.DocumentPartitioner.PARTITIONING;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mlang.utils.Utils;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;

public class InputPresentationReconciler implements ITextListener {

	private final Map<String, List<InputHandler>> handlers = new HashMap<String, List<InputHandler>>();
	private final ITextViewer viewer;

	public InputPresentationReconciler(ITextViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void textChanged(TextEvent event) {
		if (event == null) {
			return;
		}
		DocumentEvent documentEvent = event.getDocumentEvent();
		if (documentEvent == null) {
			return;
		}
		if (documentEvent.fDocument == null) {
			return;
		}
		ITypedRegion partition;
		int offset = event.getOffset();
		try {
			partition = TextUtilities.getPartition(documentEvent.fDocument, PARTITIONING, offset, false);
		} catch (BadLocationException e) {
			return;
		}
		if (partition == null) {
			return;
		}
		List<InputHandler> handlerList = handlers.get(partition.getType());
		if (handlerList != null) {
			for (InputHandler h : handlerList) {
				TextPresentation presentation = h.textChanged(new InputContext(event, partition));
				if (presentation != null) {
					viewer.changeTextPresentation(presentation, false);
					return;
				}
			}
		}
		String text = event.getText();
		if (text != null && !text.isEmpty() && offset != 0) {
			StyledText control = viewer.getTextWidget();
			int beforePos = offset - 1;
			if (beforePos >= control.getCharCount()) {
				return;
			}
			StyleRange before = control.getStyleRangeAtOffset(beforePos);
			if (before != null) {
				StyleRange styleRange = new StyleRange(before);
				styleRange.start = offset;
				styleRange.length = text.length();
				TextPresentation presentation = new TextPresentation(1);
				presentation.addStyleRange(styleRange);
				viewer.changeTextPresentation(presentation, false);
			}
		}
	}

	public void addHandler(String contentType, InputHandler handler) {
		Utils.addToListInMap(handlers, contentType, handler);
	}

	public void removeHandler(String contentType, InputHandler handler) {
		Utils.removeFromListInMap(handlers, contentType, handler);
	}

	public static interface InputHandler {

		TextPresentation textChanged(InputContext ctx);
	}

	public static class InputContext {
	
		public final TextEvent event;
		public final ITypedRegion partition;
		private IRegion word;
		
		public InputContext(TextEvent event, ITypedRegion partition) {
			this.event = event;
			this.partition = partition;
		}
		
		public IDocument document() {
			return event.getDocumentEvent().getDocument();
		}
		
		public IRegion word() {
			if (word == null) {
				word = extractWord();
			}
			return word;
		}
		
		private IRegion extractWord() {
			IDocument doc = event.getDocumentEvent().fDocument;
			int offset = event.getOffset();
			String replacedText = event.getReplacedText();
			if (replacedText != null) {
				offset -= replacedText.length();
				if (offset < 0) {
					offset = 0;
				}
			}
			try {
				if (!isJavaIdentifierPart(doc.getChar(offset))) {
					return null;
				}
			} catch (BadLocationException e) {
				return null;
			}
			int start = offset - 1;
			
			try {
				while (start >= 0 && isJavaIdentifierPart(doc.getChar(start))) {
					-- start;
				}
			} catch (BadLocationException e) {
			}
			int end = offset + 1;
			try {
				while (end < doc.getLength() && isJavaIdentifierPart(doc.getChar(end))) {
					++ end;
				}
			} catch (BadLocationException e) {
			}
			++ start;
			return new Region(start, end - start);
		}
	}

}
