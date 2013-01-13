package anylang._exp.scan;

import anylang._exp.Lexer2;

public class LexerHelper {

	public static int tokenOffset(Lexer2 lexer) {
		return lexer.offset() - lexer.tokenLength();
	}
}
