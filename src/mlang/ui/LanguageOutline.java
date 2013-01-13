package mlang.ui;

import mlang.dom.DOM;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipselabs.mlang.models.common.Location;


public class LanguageOutline extends ContentOutlinePage {

	private final LanguageEditor editor;
	private final OutlineInfo outlineInfo;

	public LanguageOutline(LanguageEditor editor, OutlineInfo outlineInfo) {
		this.editor = editor;
		this.outlineInfo = outlineInfo;
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(outlineInfo.getContentProvider());
		viewer.setLabelProvider(outlineInfo.getLabelProvider());
	}

	public void setInput(DOM dom) {
		TreeViewer treeViewer = getTreeViewer();
		treeViewer.setInput(dom);
		if (dom != null) {
			treeViewer.expandAll();
		}
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		super.selectionChanged(event);
		
		ISelection selection = event.getSelection();
		if (selection.isEmpty())
			editor.resetHighlightRange();
		else {
			IStructuredSelection sel = (IStructuredSelection) selection;
			Object element = sel.getFirstElement();

			if (element == null) {
				return;
			}
			Location loc = outlineInfo.resolveLocation(element);
			if (loc == null) {
				editor.resetHighlightRange();
			} else {
				try {
					editor.setHighlightRange(loc.getOffset(), loc.getLength(), true);
				} catch (IllegalArgumentException x) {
					editor.resetHighlightRange();
				}
			}
			
		}
	}

}
