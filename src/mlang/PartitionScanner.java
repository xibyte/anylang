package mlang;

import html.ScannerInfo;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;

public class PartitionScanner extends RuleBasedPartitionScanner implements ScannerInfo {

	@Override
	public int offset() {
		return fOffset;
	}
	@Override

	public IDocument document() {
		return fDocument;
	}
}
