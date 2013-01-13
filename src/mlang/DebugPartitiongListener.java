package mlang;

import static mlang.DocumentPartitioner.PARTITIONING;
import static org.eclipse.swt.SWT.NULL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextPresentationListener;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public class DebugPartitiongListener implements ITextPresentationListener{

	private final ISourceViewer viewer;
	private final MLangContext mctx;
	
	public DebugPartitiongListener(ISourceViewer viewer, MLangContext mctx) {
		this.viewer = viewer;
		this.mctx = mctx;
	}

	@Override
	public void applyTextPresentation(TextPresentation textPresentation) {
		
		IDocument document = viewer.getDocument();
		ITypedRegion[] partitioning;
		try {
			partitioning = TextUtilities.computePartitioning(document, PARTITIONING, 0, document.getLength(), false);
			System.out.println(Arrays.toString(partitioning));
		} catch (BadLocationException e) {
			return;
		}
		
		Color one = mctx.getColor(new RGB(230, 230, 250));
		Color two = mctx.getColor(new RGB(255, 248, 220));
		boolean kind = true;
		List<StyleRange> styles = new ArrayList<StyleRange>(partitioning.length);
		for (ITypedRegion r : partitioning) {
			if (r.getOffset() >= document.getLength()) {
				break;
			}
			if (r.equals(IDocument.DEFAULT_CONTENT_TYPE)) {
				continue;
			}
			Color color = (kind = !kind) ? one : two; 
			
			styles.add(new StyleRange(r.getOffset(), r.getLength(), null, color, NULL));
		}

//		textPresentation.mergeStyleRanges(styles.toArray(new StyleRange[styles.size()]));
	}

}
