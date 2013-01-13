package anylang.editors;

import mlang.MLangContext;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public class SoyDocScanner extends RuleBasedScanner {

	static enum States {
		DEFAULT, PARAM;
	}
	
	private States state = States.DEFAULT;
	
	public SoyDocScanner(MLangContext manager) {

		TextAttribute docletAttr = new TextAttribute(manager.getColor(Colors.DOC), null, SWT.BOLD);
		TextAttribute varAttr = new TextAttribute(manager.getColor(Colors.DOC), null, SWT.ITALIC);
		IToken doclet = new Token(docletAttr);
		IToken param = new ParamToken(docletAttr);
		IToken var = new VarToken(varAttr);
		
		WordRule paramRule = new WordRule(new DocletDetector());
		paramRule.addWord("@param", param);
		paramRule.addWord("@param?", param);
		
		WordRule docletRule = new WordRule(new DocletDetector());
		docletRule.addWord("@author", doclet);
		IRule[] rules = {
			paramRule,
			docletRule,
			new WordRule(new VarDetector(), var)
		};
		
		setRules(rules);
	}

	@Override
	public IToken nextToken() {
		IToken nextToken = super.nextToken();
		if (nextToken != null) {
			Class<? extends IToken> tclass = nextToken.getClass();
			if (tclass == ParamToken.class) {
				state = States.PARAM;
			} else if (tclass == VarToken.class) {
				state = States.DEFAULT;
			}
		}
		return nextToken;
	}
	
	public IToken makeToken(Color color) {
		return makeToken(color, SWT.NORMAL);
	}
	
	public IToken makeToken(Color color, int style) {
		return new Token(new TextAttribute(color, null, style));
	}
	
	static class DocletDetector implements IWordDetector {

		@Override
		public boolean isWordStart(char c) {
			return c == '@';
		}

		@Override
		public boolean isWordPart(char c) {
			return Character.isLetterOrDigit(c) || c == '?';
		}
	}

	class VarDetector implements IWordDetector {

		@Override
		public boolean isWordStart(char c) {
			return Character.isLetter(c) && state == States.PARAM;
		}

		@Override
		public boolean isWordPart(char c) {
			return Character.isLetterOrDigit(c) && state == States.PARAM;
		}
		
	}

	static class ParamToken extends Token {
		public ParamToken(Object data) {
			super(data);
		}
	}
	
	static class VarToken extends Token {
		public VarToken(Object data) {
			super(data);
		}
	}
}
