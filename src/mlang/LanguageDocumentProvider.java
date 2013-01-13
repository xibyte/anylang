package mlang;

import static mlang.DocumentPartitioner.PARTITIONING;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class LanguageDocumentProvider extends FileDocumentProvider {

	private final Language language;
	
	public LanguageDocumentProvider(Language language) {
		this.language = language;
	}

	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		
		if (document != null) {
			Set<String> typeSet = language.getPresentationContentTypes();
			String[] types = new String[typeSet.size() + 1];
			typeSet.toArray(types);
			types[types.length - 1] = IDocument.DEFAULT_CONTENT_TYPE;
			DocumentPartitioner partitioner = new DocumentPartitioner(types);
			partitioner.connect(document);
			((IDocumentExtension3) document).setDocumentPartitioner(PARTITIONING, partitioner);
		}
		return document;
	}
}