package mlang;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipselabs.mlang.models.common.Scope;

public enum DummyParser implements Parser {

	INSTANCE;

	@Override
	public void init(Scope root, DocumentReader reader, IProgressMonitor monitor)
			throws Exception {
	}

	@Override
	public void build() throws Exception {
	}

	@Override
	public void shiftLexer(int value) {
	}
}
