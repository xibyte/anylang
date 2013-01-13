package anylang._exp;

import static anylang._exp.CommonTokens.BRACKETS;
import static anylang._exp.CommonTokens.KEYWORD;
import static anylang._exp.CommonTokens.LITERAL;
import static anylang._exp.CommonTokens.PARAM;
import static anylang._exp.CommonTokens.WORD;
import static anylang._exp.Lexer2.DEFAULT_STATE;
import static java.util.Collections.singletonMap;

import java.util.regex.Pattern;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

import anylang._exp.scan.Action;
import anylang._exp.scan.MultiLexer;
import anylang._exp.scan.Rule;
import anylang._exp.scan.RuleLexer;
import anylang._exp.scan.RxRule;
import anylang._exp.scan.TemplateLexer;
import anylang._exp.scan.WordRule;


public class TestLang {

	public static final int SOY_TAG_STATE = 1;
	public static final int SOY_DOCLET_STATE = 2;

	public static final int HTML_TAG_STATE = 1;
	public static final int HTML_COMMENT_STATE = 2;
	public static final int HTML_SCRIPT_TAG_STATE = 3;
	public static final int HTML_INJECTED_JS = 4;
	public static final int HTML_LITERAL = 5;
	
	private static final IToken INJECTED_JS = new Token(null);
	
	public static Lexer2 makeMainLexer() {
		Action[][] actions = new Action[2][];

		actions[DEFAULT_STATE] = actions(rx("\\{\\{", SOY_TAG_STATE, BRACKETS));
		
		actions[SOY_TAG_STATE] = actions(
				
				a(new WordRule(WORD, new WordRule.Config().add(KEYWORD, "foreach", "template")), SOY_TAG_STATE),
				rx("\\}\\}", DEFAULT_STATE, BRACKETS)
				
		);
		
		return new RuleLexer(actions);
	}


	public static Lexer2 makeJsLexer() {
		Action[][] actions = new Action[1][];

		actions[DEFAULT_STATE] = actions(
				a(new WordRule(WORD, new WordRule.Config().add(KEYWORD, "for","var", "function", "return")), DEFAULT_STATE)
		);
		
		return new RuleLexer(actions);
	}

	
	public static Lexer2 makeDataLexer() {
		Action[][] actions = new Action[6][];

		actions[DEFAULT_STATE] = actions(
				
				rx("<script", HTML_SCRIPT_TAG_STATE, BRACKETS),
				
				rx("<!--", HTML_COMMENT_STATE, BRACKETS),
				rx("<", HTML_TAG_STATE, BRACKETS),

				a(new WordRule(CommonTokens.WORD, new WordRule.Config().add(KEYWORD, "lol","function", "my")), DEFAULT_STATE)
				
		);

		actions[HTML_SCRIPT_TAG_STATE] = actions(
				
			rx(">", HTML_INJECTED_JS, BRACKETS)
				
		);

		actions[HTML_INJECTED_JS] = actions(
				
			rx("</script>", DEFAULT_STATE, BRACKETS)
			
		);

		
		actions[HTML_TAG_STATE] = actions(
				rx("\\w+=", HTML_TAG_STATE, PARAM),
				rx("'", HTML_LITERAL, LITERAL),
				rx(">", DEFAULT_STATE, BRACKETS)
				
		);
		actions[HTML_COMMENT_STATE] = actions(
				
				a(new WordRule(CommonTokens.WORD, new WordRule.Config().add(KEYWORD, "comment")), HTML_COMMENT_STATE),
				rx("-->", DEFAULT_STATE, BRACKETS)
		);

		actions[HTML_LITERAL] = actions(
				
				rx("'", HTML_TAG_STATE, LITERAL)
		);

		
		RuleLexer mainLexer = new RuleLexer(actions);
		mainLexer.setDefault(HTML_LITERAL, LITERAL);
		mainLexer.setDefault(HTML_INJECTED_JS, INJECTED_JS);
		
		return new MultiLexer(mainLexer, singletonMap(INJECTED_JS, makeJsLexer()));
	}

	
	public static Lexer2 makeLexer() {
		return new TemplateLexer(makeMainLexer(), makeDataLexer(), Token.UNDEFINED);
	}
	
	static Action[] actions(Action... a) {
		return a;
	}

	private static Action rx(String pattern, int state, IToken token) {
		return new Action(new RxRule(Pattern.compile(pattern), token), state);
	}

	private static Action a(Rule rule, int state) {
		return new Action(rule, state);
	}

}
