package html;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.TypedRegion;

public class InjectingUtils {

	public static ITypedRegion[] computePartitioning(String partitioning,
			IDocument document) {
		try {
			return TextUtilities.computePartitioning(document, partitioning, 0,
					document.getLength(), false);
		} catch (BadLocationException e) {
			return new ITypedRegion[] { new TypedRegion(0,
					document.getLength(), Html.OUTER) };
		}
	}

}
