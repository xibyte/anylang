package js.lexer;

import java.io.Reader;

import js.JSTokens;
import mlang.FlexPartitionTokenScanner;
import mlang.Lexer;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class JSPartitionScanner extends FlexPartitionTokenScanner {

	@Override
	protected IToken doNextToken() throws Exception {
		
		JSElementTypes type = (JSElementTypes) readFirst();
		tokenOffset = lexer.offset();
		tokenLength = lexer.yylength();
		
		if (type == null) {
			return Token.EOF;
		}
		
		switch (type) {
		case STRING:
			return JSTokens.STRING;
		case COMMENT:
			return JSTokens.COMMENT;
		case DOCLET:
			return JSTokens.DOCLET;
		case EOF_COMMENT:
			return JSTokens.EOF_COMMENT;
		case OTHER:
			consume(type);
			return JSTokens.DEFAULT;
		}
		
		return Token.UNDEFINED;
	}

	@Override
	protected Lexer createLexer(Reader reader) {
		return new _JSPartitionLexer(reader);
	}

	@Override
	protected void reset() {
		
	}
}
