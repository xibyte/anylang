package js;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class KeywordRule extends WordRule {

	public KeywordRule(IToken token) {
		super(new WordDetector(), new Token(null));

		for (String word : JS.KEYWORDS) {
			addWord(word, token);
		}
	}

	private static class WordDetector implements IWordDetector {

		public boolean isWordPart(char c) {
			return Character.isJavaIdentifierPart(c);
		}

		public boolean isWordStart(char c) {
			return Character.isJavaIdentifierStart(c);
		}
	}
}
