package mlang;

import static org.eclipse.jface.text.rules.Token.UNDEFINED;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;


public class ClosedRule implements IPredicateRule {

	private final IToken token;
	private final CharSequence start;
	private final CharSequence end;
	private final boolean unreadToFirstToken;
	private final boolean onlyUnclosed;
	private final LiteralStrategy literalStrategy;
	
	public ClosedRule(CharSequence start, CharSequence end, IToken token,
			boolean unreadToFirstToken, boolean onlyUnclosed, LiteralStrategy literalStrategy) {
		this.start = start;
		this.end = end;
		this.token = token;
		this.unreadToFirstToken = unreadToFirstToken;
		this.onlyUnclosed = onlyUnclosed;
		this.literalStrategy = literalStrategy;
	}

	public ClosedRule(CharSequence start, CharSequence end, IToken token) {
		this(start, end, token, false, false, JavaStringLiteralStrategy.INSTANCE);
	}

	public ClosedRule(CharSequence start, CharSequence end, IToken token, LiteralStrategy literalStrategy) {
		this(start, end, token, false, false, literalStrategy);
	}
	
	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		
		if (resume || startSequenceDetected(scanner, start)) {
			
			ScannerWrapper wrapper = new ScannerWrapper(scanner);
			
			for (;;) {

				if (literalStrategy.startSkip(wrapper)) {
					literalStrategy.skip(wrapper);
					continue;
				}
				
				wrapper.checkpoint();
				
				if (endSequenceDetected(wrapper, start) || wrapper.wasEof) {
					if (unreadToFirstToken) {
						wrapper.unreadAll();
					} else {
						wrapper.unreadLocal(); // Unread EOF or start seq of another tag
					} 
					return token;
				}

				int hits1 = wrapper.hits();
				
				wrapper.unreadLocal();
				
				if (endSequenceDetected(wrapper, end)) {
					if (onlyUnclosed) {
						//Everything OK. Just comment. unread all;
						wrapper.unreadAll();
						if (!resume) {
							unread(scanner, start.length()); //---?????WHY?
						}
						return UNDEFINED;
					} else {
						return token;
					}
				}
				
				int hits2 = wrapper.hits();
				wrapper.unreadLocal();
				
				wrapper.skip(1);
				//wrapper.skip(Math.min(hits1, hits2)); //BUG! we don't got to skip
			}
		}
		
		return UNDEFINED; //TODO: ---?????WHY? HANDLE resume?????
	}

	@Override
	public IToken getSuccessToken() {
		return token;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		return evaluate(scanner, false);
	}
	
	private void unread(ICharacterScanner scanner, int count) {
		for (int i = 0; i < count; ++i) {
			scanner.unread();
		}
	}

	protected boolean startSequenceDetected(ICharacterScanner scanner,
			CharSequence sequence) {
		for (int i= 0; i < sequence.length(); i++) {
			int c= scanner.read();
			if (c != sequence.charAt(i)) {
				for (int j = 0; j < i + 1; j++) {
					scanner.unread();
				}
				return false;
			}
		}
		return true;
	}

	protected boolean endSequenceDetected(ScannerWrapper scanner,
			CharSequence sequence) {
		for (int i= 0; i < sequence.length(); i++) {
			int c = scanner.read();
			if (c != sequence.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}
