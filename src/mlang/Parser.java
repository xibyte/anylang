package mlang;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipselabs.mlang.models.common.Scope;

public interface Parser {
	
	void init(Scope root, DocumentReader reader, IProgressMonitor monitor)
			throws Exception;

	void build() throws Exception;

	void shiftLexer(int value);

}
