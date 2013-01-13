package mlang;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WordRule;

import anylang.editors.CommonWhitespaceDetector;


public class OpenedTagRule extends WordRule {

	public OpenedTagRule(char openingSymbol, IToken token) {
		super(new WordDetector(openingSymbol), token);
	}

	static class WordDetector implements IWordDetector {

		private final char openingSymbol;

		public WordDetector(char openingSymbol) {
			this.openingSymbol = openingSymbol;
		}

		@Override
		public boolean isWordStart(char c) {
			return c == openingSymbol;
		}

		@Override
		public boolean isWordPart(char c) {
			return !CommonWhitespaceDetector.isWhitespaceChar(c);
		}
	}
}
