package anylang._exp;

import static anylang._exp.dom.Attrs.NAME;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipselabs.mlang.models.common.Location;

import anylang._exp.dom.Scope;
import anylang._exp.dom.Variable;



public class LanguageOutline2 extends ContentOutlinePage {

	private final AnyLangEditor editor;

	public LanguageOutline2(AnyLangEditor editor) {
		this.editor = editor;
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(new ITreeContentProvider() {

			@Override
			public void dispose() {
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}

			@Override
			public Object[] getElements(Object inputElement) {
				ArrayList<Object> elements = new ArrayList<>();
				if (inputElement instanceof Scope) {
					Scope scope = (Scope) inputElement;
					elements.addAll(scope.getChildren());
				}
				return elements.toArray();
			}

			@Override
			public Object[] getChildren(Object parentElement) {
				return getElements(parentElement);
			}

			@Override
			public Object getParent(Object element) {
				if (element instanceof Scope) {
					return ((Scope) element).getParent();
				} 
				return null;
			}

			@Override
			public boolean hasChildren(Object element) {
				return getChildren(element).length != 0;
			}
			
		});
		
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Scope) {
					Scope scope = (Scope) element;
					String name = scope.attr(String.class, NAME);
					if (name == null) {
						name = "unnamed";
					}
					List<Variable> vars = scope.getVariables();
					if (!vars.isEmpty()) {
						name += "(";
						Iterator<Variable> it = vars.iterator();
						name += it.next().getName() + "";
						while (it.hasNext()) {
							name += ", " + it.next().getName();
						}
						name += ")";
					}
					return name;
					
				} else if (element instanceof Variable) {
					return ((Variable) element).getName();
				}
				return super.getText(element);
			}
		});
	}

	public void setInput(Scope root) {
		TreeViewer treeViewer = getTreeViewer();
		treeViewer.setInput(root);
		if (root != null) {
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
			Location loc = null;//outlineInfo.resolveLocation(element);
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
