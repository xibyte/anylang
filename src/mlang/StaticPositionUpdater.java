package mlang;

import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.Position;



public class StaticPositionUpdater implements IPositionUpdater {

	private final String fCategory;

	protected Position fPosition;
	protected Position fOriginalPosition= new Position(0, 0);

	protected int fOffset;
	protected int fLength;
	protected int fReplaceLength;
	protected IDocument fDocument;

	public StaticPositionUpdater(String category) {
		fCategory= category;
	}

	protected String getCategory() {
		return fCategory;
	}

	protected boolean isAffectingReplace() {
		return fLength > 0 && fReplaceLength > 0 && fPosition.length < fOriginalPosition.length;
	}

	protected void adaptToInsert() {

		int myStart= fPosition.offset;
		int myEnd=   fPosition.offset + fPosition.length - 1;
		myEnd= Math.max(myStart, myEnd);

		int yoursStart= fOffset;
		int yoursEnd=   fOffset + fReplaceLength -1;
		yoursEnd= Math.max(yoursStart, yoursEnd);

		if (myEnd < yoursStart)
			return;

		if (myStart <= yoursStart)
			fPosition.length += fReplaceLength;
		else
			fPosition.offset += fReplaceLength;
	}

	protected void adaptToRemove() {

		int myStart= fPosition.offset;
		int myEnd=   fPosition.offset + fPosition.length -1;
		myEnd= Math.max(myStart, myEnd);

		int yoursStart= fOffset;
		int yoursEnd=   fOffset + fLength -1;
		yoursEnd= Math.max(yoursStart, yoursEnd);

		if (myEnd < yoursStart)
			return;

		if (myStart <= yoursStart) {

			if (yoursEnd <= myEnd)
				fPosition.length -= fLength;
			else
				fPosition.length -= (myEnd - yoursStart +1);

		} else if (yoursStart < myStart) {

			if (yoursEnd < myStart)
				fPosition.offset -= fLength;
			else {
				fPosition.offset -= (myStart - yoursStart);
				fPosition.length -= (yoursEnd - myStart +1);
			}

		}

		// validate position to allowed values
		if (fPosition.offset < 0)
			fPosition.offset= 0;

		if (fPosition.length < 0)
			fPosition.length= 0;
	}

	protected void adaptToReplace() {

		if (fLength > 0
				&& fPosition.offset <= fOffset
				&& fOffset + fLength <= fPosition.offset + fPosition.length) {

			fPosition.length += fReplaceLength - fLength;

		} else {

			if (fLength >  0)
				adaptToRemove();

			if (fReplaceLength > 0)
				adaptToInsert();
		}
	}

	protected boolean notDeleted() {

		if (fOffset < fPosition.offset && (fPosition.offset + fPosition.length < fOffset + fLength)) {

			fPosition.delete();

			try {
				fDocument.removePosition(fCategory, fPosition);
			} catch (BadPositionCategoryException x) {
			}

			return false;
		}

		return true;
	}

	public void update(DocumentEvent event) {

		try {


			fOffset= event.getOffset();
			fLength= event.getLength();
			fReplaceLength= (event.getText() == null ? 0 : event.getText().length());
			fDocument= event.getDocument();

			Position[] category= fDocument.getPositions(fCategory);
			for (int i= 0; i < category.length; i++) {

				fPosition= category[i];
				fOriginalPosition.offset= fPosition.offset;
				fOriginalPosition.length= fPosition.length;

				if (notDeleted())
					adaptToReplace();
			}

		} catch (BadPositionCategoryException x) {
			// do nothing
		} finally {
			fDocument= null;
		}
	}
}
