package anylang._exp;


public class LanguageImpl implements Language2 {

	private String id;
	private LexerDefinition lexdef;
	private Provider<Parser2> parserProvider;

	@Override
	public Lexer2 makeLexer(String... scopes) {
		return lexdef.make(id, scopes);
	}

	public void setLexerDefinitoin(LexerDefinition lexdef) {
		this.lexdef = lexdef;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Parser2 makeParser() {
		if (parserProvider == null) {
			return null;
		}
		return parserProvider.get();
	}

	public void setParserProvider(Provider<Parser2> parserProvider) {
		this.parserProvider = parserProvider;
	}
}
