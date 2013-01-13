package html;

import static html.Html.JS_DEFAULT;
import static org.eclipse.jface.text.IDocument.DEFAULT_CONTENT_TYPE;
import static org.eclipse.jface.text.rules.ICharacterScanner.EOF;
import static org.eclipse.jface.text.rules.Token.UNDEFINED;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class JSRule implements IPredicateRule {
	
	private final IToken token;
	private final ScannerInfo scannerInfo;
	private final String contentType;

	public JSRule(String contentType, ScannerInfo scannerInfo) {
		this.contentType = contentType;
		this.scannerInfo = scannerInfo;
		token = new Token(adapt(contentType));
	}

	private Object adapt(String type) {
		return DEFAULT_CONTENT_TYPE.equals(type) ? JS_DEFAULT : type;
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
		int offset = scannerInfo.offset();
		ITypedRegion part = findPart(offset, partitioning);
		if (part == null) {
			return UNDEFINED;
		}

		
		String type = part.getType();
		if (Html.OUTER.equals(type)) {
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
		return InjectingUtils.computePartitioning(Html.JS_OUTER_PARTITIONING, scannerInfo.document());
	}

}
