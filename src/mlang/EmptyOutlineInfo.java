package mlang;

import mlang.ui.OutlineInfo;
import mlang.utils.EmptyArrays;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipselabs.mlang.models.common.Location;

public enum EmptyOutlineInfo implements OutlineInfo {

	INSTANCE;

	private static final LabelProvider LABEL_PROVIDER = new LabelProvider();

	@Override
	public ITreeContentProvider getContentProvider() {
		return ContentProvider.INSTANCE;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		return LABEL_PROVIDER;
	}

	private static enum ContentProvider implements ITreeContentProvider {

		INSTANCE;
		
		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return EmptyArrays.OBJECT;
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return EmptyArrays.OBJECT;
		}

		@Override
		public Object getParent(Object element) {
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			return false;
		}
	}

	@Override
	public Location resolveLocation(Object object) {
		return null;
	}
}
