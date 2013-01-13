package mlang;

import static org.eclipse.jface.text.rules.ICharacterScanner.EOF;

import org.eclipse.jface.text.rules.ICharacterScanner;


public enum JavaStringLiteralStrategy implements LiteralStrategy {

	INSTANCE;
	
	@Override
	public void skip(ICharacterScanner scanner) {
		boolean escape = false;
		for (;;) {
			
			int c = scanner.read();
			
			if (c == EOF) {
				return;
			}
			
			switch (c) {
			case '\n': //TODO
				return;
			case '"':
				if (!escape) {
					return;
				}
				break;
			case '\\':
				if (!escape) {
					escape = true;
					continue;
				}
				break;
			}
			escape = false;
		}
	}

	@Override
	public boolean startSkip(ICharacterScanner scanner) {
		int c = scanner.read();
		if (c == '"') {
			return true;
		} else {
			scanner.unread();
			return false;
		}
	}
	
}
