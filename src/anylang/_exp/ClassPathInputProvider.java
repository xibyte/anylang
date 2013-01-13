package anylang._exp;

import java.io.InputStream;

public class ClassPathInputProvider implements InputProvider {
	
	private final String path;

	public ClassPathInputProvider(String path) {
		this.path = path;
	}

	@Override
	public InputStream openStream() {
		return XMLBasedLanguageProvider.class.getResourceAsStream(path);
	}
}