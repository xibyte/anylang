package mlang;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.swt.custom.StyleRange;

public interface PresentationBuilder {

	void init(DocumentReader reader, List<StyleRange> presentation,
			List<ITypedRegion> partitioning, IProgressMonitor monitor);
	
	void setState(String contentType);
	
	void build() throws IOException;

	void shiftLexer(int start);

}
