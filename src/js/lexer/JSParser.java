package js.lexer;

import mlang.DocumentReader;
import mlang.FlexParser;
import mlang.IElementType;
import mlang.Lexer;

import org.eclipselabs.mlang.models.common.Scope;
import org.eclipselabs.mlang.models.js.Argument;
import org.eclipselabs.mlang.models.js.Function;
import org.eclipselabs.mlang.models.js.JsFactory;

public class JSParser extends FlexParser {

	private State state = new State();
	protected static final JsFactory jf = JsFactory.eINSTANCE;
	
	@Override
	protected boolean handle(IElementType token) throws Exception {
		
		switch (((JSElementTypes)token)) {
		case FUNCTION:
			state.function = jf.createFunction();
			break;
		case FUNCTION_NAME:
			if (state.function != null) {
				state.function.setName(lexer.yytext());
				state.function.setLocation(location());
			}
			break;
		case ARG:
			if (state.function != null) {
				Argument arg = jf.createArgument();
				arg.setName(lexer.yytext());
				arg.setLocation(location());
				state.function.getArguments().add(arg);
			}				
			break;
		case BRACE_OPEN: {
			Scope scope;
			if (state.function != null) {
				scope = state.function;
				state.function = null;
			} else {
				scope = cf.createScope();
			}
			open(scope);
			break;
		}
		case BRACE_CLOSE:
			close();
			break;
		case STRING:
			add(cf.createLiteral());
			break;
		case DOCLET:
			add(cf.createDoclet());
			break;
		case MULTI_COMMENT:
			add(cf.createMultiComment());
			break;
		case SINGLE_COMMENT:
			add(cf.createSingleComment());
			break;
		}
		return true;
	}

	@Override
	protected Lexer createLexer(DocumentReader reader) {
		return new _JSLexer(reader);
	}

	@Override
	protected void reset() {
		state = new State();
	}
	
	static class State {
		Function function;
	}
}
