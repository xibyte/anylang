package anylang._exp;

import org.eclipse.jface.text.rules.IToken;

import anylang._exp.scan.State;


public interface Lexer2 {

	static final int DEFAULT_STATE = 0;

	IToken next();
	
	State state();
	
	State defaultState();

	void init(CharSequence input, State state);

	int offset();
	
	int tokenOffset();

	int tokenLength();
	
	CharSequence value();
	
	Lexer2 getActiveLexer();
	
	String getLanguage();
}
