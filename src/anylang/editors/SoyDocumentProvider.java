package anylang.editors;

import mlang.ui.LanguageEditor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class SoyDocumentProvider extends FileDocumentProvider {

	private final LanguageEditor editor;
	
	//TODO make thru context
	public SoyDocumentProvider(LanguageEditor editor) {
		this.editor = editor;
	}

	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new SoyPartitionScanner(),
					new String[] {
						SoyPartitionScanner.SOY_TAG_U,
						SoyPartitionScanner.SOY_TAG,
						SoyPartitionScanner.SOY_DOC_U,
						SoyPartitionScanner.SOY_DOC });
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}
}