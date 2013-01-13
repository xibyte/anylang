package mlang.ui;

import org.eclipselabs.mlang.models.common.Location;


public interface LocationResolver {

	Location resolve(Object element);
	
}
