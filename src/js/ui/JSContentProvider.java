package js.ui;

import mlang.ui.DOMContentProvider;
import mlang.utils.EmptyArrays;

import org.eclipselabs.mlang.models.js.Argument;
import org.eclipselabs.mlang.models.js.Function;

public class JSContentProvider extends DOMContentProvider {

	@Override
	public Object[] getChildren(Object parent) {
		if (parent instanceof Function) {
			return ((Function) parent).getArguments().toArray();
		}
		return EmptyArrays.OBJECT;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Argument) {
			return ((Argument) element).getFunction();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Function) {
			return !((Function) element).getArguments().isEmpty();
		}
		return false;
	}
}
