package html;

import static org.eclipse.jface.text.rules.ICharacterScanner.EOF;
import static org.eclipse.jface.text.rules.Token.UNDEFINED;

import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;

public class OuterRule implements IPredicateRule {

	private final IToken token;
	private final OffsetProvider op;
	private final DocumentProvider dp;

	public OuterRule(IToken token, OffsetProvider op, DocumentProvider dp) {
		this.token = token;
		this.op = op;
		this.dp = dp;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		return evaluate(scanner, false);
	}

	@Override
	public IToken getSuccessToken() {
		return token;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {

		ITypedRegion[] partitioning = getPartitioning();
		int offset = op.offset();
		ITypedRegion part = findPart(offset, partitioning);
		if (part == null) {
			return UNDEFINED;
		}

		
		if (Html.SCRIPT.equals(part.getType())) {
			return UNDEFINED;
		} else {
			int limit = part.getLength() - (offset - part.getOffset());
			for (int i = 0;i < limit;++i) {
				if (EOF == scanner.read()) {
					scanner.unread();
					break;
				}
			}
			return token;
		}
	}

	private ITypedRegion findPart(int offset, ITypedRegion[] partitioning) {
		for (ITypedRegion part : partitioning) {
			if (offset >= part.getOffset()
					&& offset < part.getOffset() + part.getLength()) {
				return part;
			}
		}
		return null;
	}

	private ITypedRegion[] getPartitioning() {
		// TODO CACHE IT!!!!
		return InjectingUtils.computePartitioning(Html.JS_OUTER_PARTITIONING, dp.document());
	}
}
