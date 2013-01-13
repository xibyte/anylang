package html.lexer;

import static html.lexer.HTMLElementTypes.CLOSING_TAG;
import static org.eclipselabs.mlang.models.html.TagType.OPENING;
import static org.eclipselabs.mlang.models.html.TagType.SINGLE;
import js.lexer.JSParser;
import mlang.DocumentReader;
import mlang.FlexParser;
import mlang.IElementType;
import mlang.Lexer;
import mlang.utils.Loc;

import org.eclipselabs.mlang.models.common.Scope;
import org.eclipselabs.mlang.models.html.HtmlFactory;
import org.eclipselabs.mlang.models.html.JSScope;
import org.eclipselabs.mlang.models.html.Tag;
import org.eclipselabs.mlang.models.html.TagScope;
import org.eclipselabs.mlang.models.html.TagType;

public class HTMLParser extends FlexParser {

	protected static final HtmlFactory hf = HtmlFactory.eINSTANCE;

	private final JSParser jsParser = new JSParser();
	private State state = new State();
	
	@Override
	protected boolean handle(IElementType token) throws Exception {
		switch (((HTMLElementTypes)token)) {
		case START_TAG:
			state.tag = hf.createTag();
			state.tag.setOffset(lexer.offset());
			break;
		case TAG_NAME:
			if (state.tag != null) {
				state.tag.setName(lexer.yytext());
			}
			break;
		case END_TAG:
			addTag(OPENING);
			if (js()) {
				JSScope jsScope = hf.createJSScope();
				open(jsScope);
				int offset = jsScope.getOffset();
				upto(CLOSING_TAG);
				
				int length = lexer.offset() - offset;
				if (length > 0) {
					DocumentReader subReader = new DocumentReader(reader, offset, length);
					jsParser.init(jsScope, subReader, monitor);
					jsParser.shiftLexer(offset);
					jsParser.build();
				}
				handleClosingTag();
			} else {
				TagScope tagScope = hf.createTagScope();
				open(tagScope);
			}
			break;
		case END_SINGLE_TAG:
			addTag(SINGLE);
			break;
		case CLOSING_TAG:
			handleClosingTag();
			break;
		case COMMENT:
			add(cf.createComment());
			break;
		}
		return true;
	}

	private Scope handleClosingTag() {
		Tag tag = hf.createTag();
		tag.setName(extractNameFromClosing(lexer.yytext()));
		Scope scope = close();
		add(tag);
		tag.setType(TagType.CLOSING);
		if (scope instanceof TagScope) {
			((TagScope) scope).setClosing(tag);
		}
		return scope;
	}

	private String extractNameFromClosing(String name) {
		if (name.length() < 4) {
			return name;
		}
		return name.substring(2, name.length() - 1);
	}

	public void open(TagScope scope) {
		if (state.tag != null) {
			scope.setOpening(state.tag);
		}
		super.open(scope);
	}
	
	private boolean js() {
		return state.tag != null
				&& "script".equalsIgnoreCase(state.tag.getName());
	}

	private void addTag(TagType type) {
		if (state.tag != null) {
			state.tag.setType(type);
			Loc.setLength(state.tag, end());
			scopes.peek().getBody().add(state.tag);
		}
	}

	@Override
	protected Lexer createLexer(DocumentReader reader) {
		return new _HTMLLexer(reader);
	}

	@Override
	protected void reset() {
		state  = new State();
	}
	
	static class State {
		Tag tag;
	}
}
