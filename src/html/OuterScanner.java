package html;

import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class OuterScanner extends RuleBasedPartitionScanner implements
		OffsetProvider, DocumentProvider {

	private static final IToken OUTER_TOKEN = new Token(Html.OUTER);

	public OuterScanner(List<IPredicateRule> innerRules) {
		innerRules.add(0, new OuterRule(OUTER_TOKEN, this, this));
		innerRules.add(1, new MultiLineRule("<script", ">", OUTER_TOKEN));
		innerRules.add(2, new MultiLineRule("</script", ">", OUTER_TOKEN));
		
		setPredicateRules(innerRules.toArray(new IPredicateRule[innerRules.size()]));
	}

	@Override
	public int offset() {
		return fOffset;
	}

	@Override
	public IDocument document() {
		return fDocument;
	}
}
