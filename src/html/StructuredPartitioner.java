package html;

import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

public class StructuredPartitioner extends FastPartitioner {

	public StructuredPartitioner(IPartitionTokenScanner scanner,
			String[] legalContentTypes) {
		super(scanner, legalContentTypes);
	}

	@Override
	public String getContentType(int offset) {
		return super.getContentType(offset);
	}
	
	@Override
	public String getContentType(int offset, boolean preferOpenPartitions) {
		return super.getContentType(offset, preferOpenPartitions);
	}

	@Override
	public ITypedRegion[] computePartitioning(int offset, int length,
			boolean includeZeroLengthPartitions) {
		return super.computePartitioning(offset, length, includeZeroLengthPartitions);
	}
	
	
}
