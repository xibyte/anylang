package mlang;

import java.util.List;

import org.eclipse.jface.text.ITypedRegion;
import org.eclipselabs.mlang.models.common.Scope;

public interface PartitionsExtractor {

	void extract(Scope scope, List<ITypedRegion> parts, List<ITypedRegion> zones);
	
}
