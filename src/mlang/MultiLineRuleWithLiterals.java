package mlang;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;

public class MultiLineRuleWithLiterals extends MultiLineRule {

	private LiteralStrategy literalStrategy = JavaStringLiteralStrategy.INSTANCE;
	
	public MultiLineRuleWithLiterals(String startSequence, String endSequence,
			IToken token) {
		super(startSequence, endSequence, token);
		
	}

	public MultiLineRuleWithLiterals(String startSequence, String endSequence,
			IToken token, char escapeCharacter, boolean breaksOnEOF) {
		super(startSequence, endSequence, token, escapeCharacter, breaksOnEOF);
	}

	@Override
	protected boolean sequenceDetected(ICharacterScanner scanner,
			char[] sequence, boolean eofAllowed) {
		
		if (literalStrategy.startSkip(scanner)) {
			literalStrategy.skip(scanner);
		}
		
		return super.sequenceDetected(scanner, sequence, eofAllowed);
	}
	
	public LiteralStrategy getLiteralStrategy() {
		return literalStrategy;
	}

	public void setLiteralStrategy(LiteralStrategy literalStrategy) {
		this.literalStrategy = literalStrategy;
	}
}
