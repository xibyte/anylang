package mlang.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipselabs.mlang.models.common.Location;

public interface OutlineInfo {

	ITreeContentProvider getContentProvider();

	ILabelProvider getLabelProvider();

	Location resolveLocation(Object element);
}
