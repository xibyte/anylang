package js.ui;

import org.eclipselabs.mlang.models.common.Location;
import org.eclipselabs.mlang.models.js.Argument;
import org.eclipselabs.mlang.models.js.Function;

import mlang.ui.LocationResolver;

public class JsLocationResolver implements LocationResolver {

	@Override
	public Location resolve(Object element) {
		if (element instanceof Function) {
			return ((Function) element).getLocation();
		} else if (element instanceof Argument) {
			return ((Argument) element).getLocation();
		}
		return null;
	}

}
