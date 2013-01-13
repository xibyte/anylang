package anylang._exp;

public interface Language2 {

	Lexer2 makeLexer(String... scopes);
	
	Parser2 makeParser();
	
	String getId();
}
