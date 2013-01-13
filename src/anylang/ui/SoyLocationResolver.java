package anylang.ui;

import mlang.ui.LocationResolver;

import org.eclipselabs.mlang.models.common.Location;
import org.eclipselabs.mlang.models.soy.Param;
import org.eclipselabs.mlang.models.soy.Tag;

public class SoyLocationResolver implements LocationResolver {

	@Override
	public Location resolve(Object element) {
		if (element instanceof Tag) {
			return ((Tag) element).getOpenLocation();
		} else if (element instanceof Param) {
			return ((Param) element).getLocation();
		}
		return null;
	}

}
