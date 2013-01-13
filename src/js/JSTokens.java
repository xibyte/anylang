package js;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class JSTokens {

	public static final IToken STRING = new Token(JS.STRING);
	public static final IToken COMMENT = new Token(JS.MULTI_COMMENT);
	public static final IToken EOF_COMMENT = new Token(JS.SINGLE_COMMENT);
	public static final IToken DOCLET = new Token(JS.DOCLET);
	public static final IToken DEFAULT = new Token(JS.DEFAULT);
	
}
