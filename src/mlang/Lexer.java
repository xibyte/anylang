package mlang;

import java.io.Reader;

public interface Lexer {

	IElementType advance() throws java.io.IOException;
	
	int offset();
	
	int yylength();
	
	int yystate();
	
	void yypushback(int number);

	void yyreset(Reader reader);

	String yytext();
}
