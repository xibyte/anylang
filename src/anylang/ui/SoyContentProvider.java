package anylang.ui;

import java.util.ArrayList;
import java.util.List;

import mlang.dom.DOM;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipselabs.mlang.models.soy.Doclet;
import org.eclipselabs.mlang.models.soy.Param;
import org.eclipselabs.mlang.models.soy.Tag;
import org.eclipselabs.mlang.models.soy.TemplateTag;

public class SoyContentProvider implements ITreeContentProvider {

	@Override
	public void inputChanged(Viewer viewer, Object oldInput,
			Object newInput) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return children != null && children.length != 0;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Tag) {
			return ((Tag) element).getParent();
		} else if (element instanceof Doclet) {
			return ((Doclet) element).getTemplate();
		} else if (element instanceof Param) {
			return ((Param) element).getDoclet();
		} else {
			return null;
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return ((DOM) inputElement).getRoot().getBody().toArray();
	}

	@Override
	public Object[] getChildren(Object parent) {
		if (parent instanceof TemplateTag) {
			TemplateTag tt = (TemplateTag) parent;
			List<Object> children = new ArrayList<Object>(tt.getChildren().size() + 1);
			children.add(tt.getDoclet());
			children.addAll(tt.getChildren());
			return children.toArray();
		} else if (parent instanceof Tag) {
			return ((Tag) parent).getChildren().toArray();
		} else if (parent instanceof Doclet) {
			return ((Doclet) parent).getParams().toArray();
		} else {
			return null;
		}
	}
}
