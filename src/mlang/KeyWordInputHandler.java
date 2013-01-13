package mlang;

import java.util.Set;

import mlang.InputPresentationReconciler.InputContext;
import mlang.InputPresentationReconciler.InputHandler;
import mlang.utils.Styles;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.swt.custom.StyleRange;

public class KeyWordInputHandler implements InputHandler {

	private final TextAttribute keyWordStyle;
	private final Set<String> keywords; 
	
	public KeyWordInputHandler(TextAttribute keyWordStyle, Set<String> keywords) {
		this.keyWordStyle = keyWordStyle;
		this.keywords = keywords;
	}

	@Override
	public TextPresentation textChanged(InputContext ctx) {
		IRegion word = ctx.word();
		if (word == null) {
			return null;
		}
		try {
			String wordStr = ctx.document().get(word.getOffset(), word.getLength());
			if (keywords.contains(wordStr)) {
				TextPresentation presentation = new TextPresentation();
				StyleRange styleRange = Styles.makeStyle(keyWordStyle, word);
				presentation.addStyleRange(styleRange);
				return presentation;
			}
		} catch (BadLocationException e) {
		}
		return null;
	}
}
