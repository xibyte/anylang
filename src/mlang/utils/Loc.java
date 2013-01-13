package mlang.utils;

import org.eclipselabs.mlang.models.common.Location;

public class Loc {

	public static int end(Location location) {
		return location.getOffset() + location.getLength(); 
	}
	
	public static void setLength(Location location, int end) {
		location.setLength(end - location.getOffset());
	}
}
