package mlang.utils;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;

public class Ranges {

	public static final IRegion EMPTY = new Region(0, 0);
	
	public static boolean behind(int length, int rstart, int rlength) {
		return rstart + rlength > length;
	}
	
	public static int end(IRegion region) {
		return region.getOffset() + region.getLength(); 
	}

	public static int end(Position p) {
		return p.offset + p.length; 
	}
	
	public static IRegion merge(IRegion first, IRegion last) {
		if (first == null) {
			return last;
		}
		if (first == last) {
			return first;
		}
		
		if (first.getOffset() > last.getOffset()) {
			IRegion r = first; 
			first = last;
			last = r;
		}
		
		int length = Math.max(last.getOffset() + last.getLength() - first.getOffset(), first.getLength());
		return new Region(first.getOffset(), length);
	}
	
	public static boolean isValid(IRegion region) {
		return region.getOffset() >= 0 &&  region.getLength() >=0; 
	}
}
