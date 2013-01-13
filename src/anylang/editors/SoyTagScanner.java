package anylang.editors;

import mlang.MLangContext;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public class SoyTagScanner extends RuleBasedScanner {

	public SoyTagScanner(MLangContext manager) {
		IToken string = makeToken(manager.getColor(Colors.STRING));
		IToken attr = makeToken(manager.getColor(Colors.ATTR));
		IToken tag = makeToken(manager.getColor(Colors.TAG));
		IToken var = makeToken(manager.getColor(Colors.TAG), SWT.BOLD);
		
		IRule[] rules = {

		// Add rule for double quotes
		new SingleLineRule("\"", "\"", string, '\\'),
		// Add a rule for single quotes
		new SingleLineRule("'", "'", string, '\\'),
		
		new SingleLineRule("'", "'", string, '\\'),

		new WordRule(new TagDetector(), tag),

		new WordRule(new AttributeDetector(), attr),
		
		new WordRule(new VarDetector(), var),
		
//		new SingleLineRule("", endSequence, token)
		// Add generic whitespace rule.
		new WhitespaceRule(new CommonWhitespaceDetector())};

		setRules(rules);
	}

	public IToken makeToken(Color color) {
		return makeToken(color, SWT.NORMAL);
	}
	
	public IToken makeToken(Color color, int style) {
		return new Token(new TextAttribute(color, null, style));
	}
	
	static class AttributeDetector implements IWordDetector {

		@Override
		public boolean isWordStart(char c) {
			return Character.isLetter(c);
		}

		@Override
		public boolean isWordPart(char c) {
			return Character.isLetterOrDigit(c) || c=='=';
		}
		
	}

	static class TagDetector implements IWordDetector {

		@Override
		public boolean isWordStart(char c) {
			return c == '{';
		}

		@Override
		public boolean isWordPart(char c) {
			return Character.isLetterOrDigit(c) || c=='/';
		}
		
	}

	static class VarDetector implements IWordDetector {

		@Override
		public boolean isWordStart(char c) {
			return c == '$';
		}

		@Override
		public boolean isWordPart(char c) {
			return Character.isLetterOrDigit(c);
		}
	}
	
}
