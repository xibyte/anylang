package js.lexer;

import java.io.IOException;

import js.JS;
import js.JSStyles;
import mlang.DocumentReader;
import mlang.FlexPresentationBuilder;
import mlang.IElementType;
import mlang.Lexer;
import mlang.Scheme;

public class JSPresentationBuilder extends FlexPresentationBuilder {

	public JSPresentationBuilder(Scheme scheme) {
		super(scheme);
	}

	@Override
	protected boolean handle(IElementType type) throws IOException {
		switch ((JSElementTypes)type) {
		case STRING:
			style(JSStyles.STRING);
			part(JS.STRING);
			break;
		case DOCLET:
			style(JSStyles.DOCLET);
			part(JS.DOCLET);
			break;
		case COMMENT:
			style(JSStyles.COMMENT);
			part(JS.MULTI_COMMENT);
			break;
		case EOF_COMMENT:
			style(JSStyles.COMMENT);
			part(JS.MULTI_COMMENT);
			break;
		case WORD:
			if (JS.KEYWORDS.contains(lexer.yytext())) {
				style(JSStyles.KEYWORD);
			}
			combine(JS.DEFAULT);
			break;
		case OTHER:
			int from = lexer.offset();
			IElementType next = consume(type);
			combine(from, lexer.offset() - from, JS.DEFAULT);
			if (next == null) {
				return false;
			}
			handle(next);
			break;
		}
		return true; 
	}

	@Override
	protected Lexer createLexer(DocumentReader reader) {
		return new _JSPresentationLexer(reader);
	}

	@Override
	protected void reset() {
	}
}
