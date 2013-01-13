package html;

import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class OuterScanner2 extends RuleBasedPartitionScanner {

	private static final IToken OUTER_TOKEN = new Token(Html.OUTER);
	private final IPredicateRule[] innerRules;
	private final IPredicateRule[] outerRules;
	
	public OuterScanner2(List<IPredicateRule> innerRules) {
		innerRules.add(0, new SingleLineRule("</script", ">", new DisableToken()));
		this.innerRules = innerRules.toArray(new IPredicateRule[innerRules.size()]);
		outerRules = new IPredicateRule[] {
				new MultiLineRule("<!--", "-->", OUTER_TOKEN),
				new MultiLineRule("<script", ">", new EnableToken())
				
		};
		setPredicateRules(outerRules);
	}

	@Override
	public IToken nextToken() {
		
		IToken token = super.nextToken();
		
		if (token.isEOF()) {
			return token;
		}

		if (token instanceof DisableToken) {
			setPredicateRules(outerRules);
		} else if (fRules.length == innerRules.length) { //TODO!!!!
			return token;
		}
		
		int offset = fTokenOffset;
		while (!token.isEOF() && !(token instanceof EnableToken)) {
			token = super.nextToken();
		}
		
		fTokenOffset = offset;
		if (token instanceof EnableToken) {
			setPredicateRules(innerRules);
		} else if (token.isEOF()) {
			unread();
			return OUTER_TOKEN;
		}
		
		return token;
	}

	@Override
	public void setPartialRange(IDocument document, int offset, int length,
			String contentType, int partitionOffset) {
		super.setPartialRange(document, offset, length, contentType, partitionOffset);
		if (Html.OUTER.equals(contentType) || contentType == null) {
			setPredicateRules(outerRules);
		} else {
			setPredicateRules(innerRules);
		}
	}
	
	@Override
	public void setRange(IDocument document, int offset, int length) {
		super.setRange(document, offset, length);
		setPredicateRules(outerRules);
	}
	
	static class EnableToken extends Token {

		public EnableToken() {
			super(Html.OUTER);
		}
	}

	static class DisableToken extends Token {

		public DisableToken() {
			super(Html.OUTER);
		}
	}
}
