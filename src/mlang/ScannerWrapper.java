package mlang;

import org.eclipse.jface.text.rules.ICharacterScanner;

public class ScannerWrapper implements ICharacterScanner {
	
	private final ICharacterScanner scanner; 
	private int readedAll = 0;
	private int readedLocal = 0;
	public boolean wasEof;
	
	public ScannerWrapper(ICharacterScanner scanner) {
		this.scanner = scanner;
	}

	public void checkpoint() {
		readedLocal = 0;
	}

	public int hits() {
		return readedLocal;
	}
	
	public void skip(int count) {
		for (int i = 0; i < count; ++i) {
			scanner.read();
			++ readedAll;
			++ readedLocal;
		}
	}
	
	public int read() {
		try {
			int c = scanner.read();
			if (c == ICharacterScanner.EOF) {
				wasEof = true;
			}
			return c;
		} finally {
			++ readedAll;
			++ readedLocal;
		}	
	}

	public void unread() {
		try {
			scanner.unread();
		} finally {
			wasEof = false;
			-- readedAll;
			-- readedLocal;
		}	
	}

	
	public void unreadAll() {
		int count = readedAll;
		for (int i = 0; i < count; ++i) {
			scanner.unread();
			-- readedAll;
		}
		readedLocal = 0;
	}
	
	public void unreadLocal() {
		int count = readedLocal;
		for (int i = 0; i < count; ++i) {
			scanner.unread();
			-- readedAll;
			-- readedLocal;
		}
	}

	@Override
	public char[][] getLegalLineDelimiters() {
		return scanner.getLegalLineDelimiters();
	}

	@Override
	public int getColumn() {
		return scanner.getColumn();
	}
}