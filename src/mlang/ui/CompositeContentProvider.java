package mlang.ui;

import java.util.List;

import mlang.utils.LazyList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class CompositeContentProvider implements ITreeContentProvider {

	private final List<ITreeContentProvider> providers;

	public CompositeContentProvider(List<ITreeContentProvider> providers) {
		this.providers = providers;
	}

	@Override
	public void dispose() {
		for (ITreeContentProvider provider : providers) {
			provider.dispose();
		}
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		for (ITreeContentProvider provider : providers) {
			provider.inputChanged(viewer, oldInput, newInput);
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		LazyList<Object> list = new LazyList<Object>();
		for (ITreeContentProvider provider : providers) {
			list.addArray(provider.getElements(inputElement));
		}
		return list.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		LazyList<Object> list = new LazyList<Object>();
		for (ITreeContentProvider provider : providers) {
			list.addArray(provider.getChildren(parentElement));
		}
		return list.toArray();
	}

	@Override
	public Object getParent(Object element) {
		for (ITreeContentProvider provider : providers) {
			Object parent = provider.getParent(element);
			if (parent != null) {
				return parent;
			}
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		for (ITreeContentProvider provider : providers) {
			if (provider.hasChildren(element)) {
				return true;
			}
		}
		return false;
	}
}
