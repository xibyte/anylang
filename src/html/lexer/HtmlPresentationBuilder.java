package html.lexer;

import static html.lexer.HTMLElementTypes.CLOSING_SCRIPT_TAG;
import static html.lexer._HTMLPresentationLexer.YYINITIAL;
import html.Html;
import html.HtmlStyles;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import js.lexer.JSPresentationBuilder;
import mlang.DocumentReader;
import mlang.FlexPresentationBuilder;
import mlang.IElementType;
import mlang.Lexer;
import mlang.PresentationBuilder;
import mlang.Scheme;

import org.eclipse.swt.custom.StyleRange;

public class HtmlPresentationBuilder extends FlexPresentationBuilder {

	private final PresentationBuilder jsBuilder;
	private State state;
	
	public HtmlPresentationBuilder(Scheme scheme) {
		super(scheme);
		jsBuilder = new JSPresentationBuilder(scheme);
	}

	protected boolean handle(IElementType type) throws IOException {
		switch ((HTMLElementTypes)type) {
		case START_SCRIPT_TAG:
			state.js = true;
		case START_TAG:
			state.marker = lexer.offset();
			state.startTag = lexer.offset();
			break;
		case PARAM:
			if (state.marker != -1) {
				style(state.marker, lexer.offset() - state.marker, HtmlStyles.TAG);
				state.marker = -1;
			}
			style(HtmlStyles.PARAM);
			break;
		case END_TAG:
			if (state.marker != -1) {
				style(state.marker, lexer.offset() - state.marker, HtmlStyles.TAG);
				state.marker = -1;
			}
			if (state.startTag != -1) {
				part(state.startTag, lexer.offset() + lexer.yylength() - state.startTag, Html.TAG);
				state.marker = -1;
			}
			style(HtmlStyles.TAG);
			if (state.js) {
				state.js = false;
				int start = lexer.offset() + lexer.yylength();
				upto(CLOSING_SCRIPT_TAG);
				int length = lexer.offset() - start;
				if (length != 0) {
					DocumentReader subReader = new DocumentReader(reader, start, length);
					jsBuilder.init(subReader, presentation, partitiong, monitor);
					jsBuilder.shiftLexer(start);
					jsBuilder.build();
				}
				style(HtmlStyles.TAG);
				part(Html.TAG);
			}
			break;
		case STRING:
			style(HtmlStyles.STRING);
			break;
		case CLOSING_TAG:
			style(HtmlStyles.TAG);
			part(Html.TAG);
			break;
		case COMMENT:
			style(HtmlStyles.COMMENT);
			part(Html.COMMENT);
			break;
		case OTHER:
			if (lexer.yystate() == YYINITIAL) {
				int from = lexer.offset();
				IElementType next = consume(type);
				part(from, lexer.offset() - from, Html.DEFAULT);
				if (next == null) {
					return false;
				}
				handle(next);
			}
		}
		return true;
	}

	@Override
	public void setState(String contentType) {	
		
	}
	
	@Override
	protected Lexer createLexer(DocumentReader reader) {
		return new _HTMLPresentationLexer(reader);
	}
	
	@Override
	protected void reset() {
		state = new State();		
	}

	static class State {
		StyleRange lastRange;
		int marker = -1;
		int startTag = -1;
		boolean js;
	}
}
