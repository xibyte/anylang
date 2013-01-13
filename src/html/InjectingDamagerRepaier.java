package html;

import static java.lang.Math.min;
import mlang.Logger;
import mlang.utils.LazyList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.TypedRegion;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;

public class InjectingDamagerRepaier extends DefaultDamagerRepairer {

	public static final boolean PRIMARY_MODE = true;
	public static final boolean SLAVE_MODE = false;
	
	private final boolean mode;

	public InjectingDamagerRepaier(ITokenScanner scanner, boolean mode) {
		super(scanner);
		this.mode = mode;
	}

	private boolean eligble(ITypedRegion region) {
		try {
			ITypedRegion[] partitioning = TextUtilities.computePartitioning(
					fDocument, Html.JS_PARTITIONING, 0, fDocument.getLength(),
					false);

			boolean inScript = false;
			for (ITypedRegion part : partitioning) {
				if (Html.SCRIPT_START.equals(part.getType())) {
					inScript = true;
				} else if (Html.SCRIPT_END.equals(part.getType())) {
					inScript = false;
				} else {
					if (include(part, region)) {
						return inScript;
					} else if (after(part, region)) {
						return false;
					}
				}
			}
		} catch (BadLocationException ex) {
			Logger.log(ex);
		}
		return false;
	}

	private boolean after(ITypedRegion r1, ITypedRegion r2) {
		return !include(r1, r2)
				&& r1.getOffset() > r2.getOffset() + r2.getLength();
	}

	private boolean include(ITypedRegion r1, ITypedRegion r2) {
		return r2.getOffset() >= r1.getOffset()
				&& r2.getOffset() + r2.getLength() <= r1.getOffset()
						+ r1.getLength();
	}

	@Override
	public void createPresentation(TextPresentation presentation,
			ITypedRegion region) {

		String type = region.getType();
		ITypedRegion[] partitioning = InjectingUtils.computePartitioning(Html.JS_PARTITIONING, fDocument);

		
		LazyList<ITypedRegion> regions = new LazyList<ITypedRegion>(1);
		int roffset = region.getOffset();
		int rlength = region.getLength();
		
		int idx = findIdx(roffset, partitioning);
		if (idx == -1) {
			return;
		}
		ITypedRegion part = partitioning[idx];
		if (isEligable(part)) {
			int length = part.getLength() - (roffset - part.getOffset());
			regions.add(new TypedRegion(roffset, min(length, rlength) , type));
		}
		
		int endIdx = findIdx(roffset+rlength, partitioning);
		
		if (endIdx != -1 && endIdx != idx) {
			
			for (++idx; idx < endIdx; ++idx) {
				part = partitioning[idx];
				if (isEligable(part)) {
					regions.add(new TypedRegion(part.getOffset(), part.getLength(), type));
				}
			}
			part = partitioning[endIdx];
			if (isEligable(part)) {
				regions.add(new TypedRegion(part.getOffset(), (roffset + rlength) - part.getOffset(), type));
			}
		}
		
		for (ITypedRegion r : regions) {
			super.createPresentation(presentation, r);
		}
	}
	
	private boolean isEligable(ITypedRegion region) {
		if (mode) {
			return isOuter(region);
		} else {
			return !isOuter(region);
		}
	}

	private boolean isOuter(ITypedRegion region) {
		return Html.OUTER.equals(region.getType());
	}

	private int findIdx(int offset, ITypedRegion[] partitioning) {
		for (int i = 0; i < partitioning.length; ++i) {
			ITypedRegion part = partitioning[i];
			if (offset >= part.getOffset()
					&& offset < part.getOffset() + part.getLength()) {
				return i;
			}
		}
		return -1;
	}

}
