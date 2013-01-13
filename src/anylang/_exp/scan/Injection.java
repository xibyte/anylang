package anylang._exp.scan;

import anylang._exp.Lexer2;

public class Injection {

	final Lexer2 lexer;
	final Rule finishRule;
	
	public Injection(Lexer2 lexer, Rule finishRule) {
		this.lexer = lexer;
		this.finishRule = finishRule;
	}
}
