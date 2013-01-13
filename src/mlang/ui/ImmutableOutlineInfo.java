package mlang.ui;


import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipselabs.mlang.models.common.Location;

public class ImmutableOutlineInfo implements OutlineInfo {

	private final ITreeContentProvider contentProvider;
	private final ILabelProvider labelProvider;
	private final LocationResolver locationResolver;
	
	public ImmutableOutlineInfo(ITreeContentProvider contentProvider,
			ILabelProvider labelProvider, LocationResolver locationResolver) {
		this.contentProvider = contentProvider;
		this.labelProvider = labelProvider;
		this.locationResolver = locationResolver;
	}

	@Override
	public ITreeContentProvider getContentProvider() {
		return contentProvider;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		return labelProvider;
	}

	@Override
	public Location resolveLocation(Object object) {
		return locationResolver.resolve(object);
	}
}
