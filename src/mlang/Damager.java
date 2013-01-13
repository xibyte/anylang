package mlang;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.presentation.IPresentationDamager;

public class Damager implements IPresentationDamager {

	private IDocument document;

	@Override
	public void setDocument(IDocument document) {
		this.document = document;
	}

	@Override
	public IRegion getDamageRegion(ITypedRegion partition, DocumentEvent e,
			boolean documentPartitioningChanged) {
		
		if (!documentPartitioningChanged) {
			try {

				IRegion info= document.getLineInformationOfOffset(e.getOffset());
				int start= Math.max(partition.getOffset(), info.getOffset());

				int end= e.getOffset() + (e.getText() == null ? e.getLength() : e.getText().length());

				if (info.getOffset() <= end && end <= info.getOffset() + info.getLength()) {
					// optimize the case of the same line
					end= info.getOffset() + info.getLength();
				} else
					end= endOfLineOf(end);

				end= Math.min(partition.getOffset() + partition.getLength(), end);
				return new Region(start, end - start);

			} catch (BadLocationException x) {
			}
		}

		return partition;
	}

	
	/**
	 * Returns the end offset of the line that contains the specified offset or
	 * if the offset is inside a line delimiter, the end offset of the next line.
	 *
	 * @param offset the offset whose line end offset must be computed
	 * @return the line end offset for the given offset
	 * @exception BadLocationException if offset is invalid in the current document
	 */
	protected int endOfLineOf(int offset) throws BadLocationException {

		IRegion info= document.getLineInformationOfOffset(offset);
		if (offset <= info.getOffset() + info.getLength())
			return info.getOffset() + info.getLength();

		int line= document.getLineOfOffset(offset);
		try {
			info= document.getLineInformation(line + 1);
			return info.getOffset() + info.getLength();
		} catch (BadLocationException x) {
			return document.getLength();
		}
	}
	
}
