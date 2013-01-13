package anylang._exp;

public class ClassRefParserProvider implements Provider<Parser2> {

	private final Class<Parser2> parserClass;
	
	public ClassRefParserProvider(Class<Parser2> parserClass) {
		this.parserClass = parserClass;
	}

	@Override
	public Parser2 get() {
		try {
			return parserClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
