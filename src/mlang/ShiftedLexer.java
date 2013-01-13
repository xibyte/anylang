package mlang;

import java.io.IOException;
import java.io.Reader;

public class ShiftedLexer implements Lexer {

	private final Lexer delegate;
	private int shift;

	public ShiftedLexer(Lexer delegate, int value) {
		this.delegate = delegate;
		shift(value);
	}
	
	public void shift(int value) {
		this.shift = value;
	}

	public IElementType advance() throws IOException {
		return delegate.advance();
	}

	public int offset() {
		return delegate.offset() + shift;
	}

	public int yylength() {
		return delegate.yylength();
	}

	public int yystate() {
		return delegate.yystate();
	}

	public void yypushback(int number) {
		delegate.yypushback(number);
	}

	public void yyreset(Reader reader) {
		delegate.yyreset(reader);
	}

	public String yytext() {
		return delegate.yytext();
	}
	
}
