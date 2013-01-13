package mlang.dom;

import static mlang.utils.Loc.end;
import static org.eclipselabs.mlang.models.common.CommonPackage.Literals.LOCATION;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipselabs.mlang.models.common.CommonFactory;
import org.eclipselabs.mlang.models.common.Located;
import org.eclipselabs.mlang.models.common.Location;
import org.eclipselabs.mlang.models.common.Scope;

public class DOM {


	private final Resource resource;
	Map<FeatureDescriptor, Location> locations = new HashMap<FeatureDescriptor, Location>();
	
	public DOM() {
		resource = new ResourceImpl();
		resource.getContents().add(CommonFactory.eINSTANCE.createScope());
		resource.eAdapters().add(new EContentAdapter(){
			
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				Object feature = notification.getFeature();
				
				if (feature instanceof EReference) {
					EClass ref = ((EReference) feature).getEReferenceType();
					if (ref != null && ref.isSuperTypeOf(LOCATION)) {
						updateIndex(notification, feature);
					}
				}
			}

			private void updateIndex(Notification notification, Object feature) {
				FeatureDescriptor fd = new FeatureDescriptor(
						(EStructuralFeature) feature,
						(EObject) notification.getNotifier());
				Object value = notification.getNewValue();
				System.out.println(notification);
				if (value == null) {
					locations.remove(fd);
				} else {
					locations.put(fd, (Location) value);
				}
			}
		});
	}

	public Scope getRoot() {
		return (Scope) resource.getContents().get(0);
	}
	
	static class FeatureDescriptor {
		
		final EStructuralFeature feature;
		final EObject target;
		
		public FeatureDescriptor(EStructuralFeature feature, EObject target) {
			this.feature = feature;
			this.target = target;
		}

		@Override
		public int hashCode() {
			return (31 + feature.hashCode())*31 + target.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null || getClass() != obj.getClass()) { 
				return false;
			}
			FeatureDescriptor other = (FeatureDescriptor) obj;
			return feature.equals(other.feature) && target.equals(other.target);
		}
	}

	public EObject find(int offset) {
		Location location = findLocation(offset);
		if (location == null) {
			return null;
		}
		if (location instanceof Located) {
			return location;
		} else {
			return location.eContainer();
		}
	}
	
	public Location findLocation(int offset) {
		
		for (Entry<FeatureDescriptor, Location> e : locations.entrySet()) {
			Location loc = e.getValue();
			int loffset = loc.getOffset();
			int length = loc.getLength();
			if (loffset <= offset && loffset + length >= offset) {
				return loc;
			}
		}
		return null;
	}
	
	public Scope findClosestScope(int offset) {
		return findClosestScope(getRoot(), offset);
	}

	private Scope findClosestScope(Scope scope, int offset) {
		if (include(scope, offset)) {
			for (EObject obj : scope.getBody()) {
				if (obj instanceof Scope) {
					Scope closest = findClosestScope((Scope) obj, offset);
					if (closest != null) {
						return closest;
					}
					if (after(scope, offset)) {
						break;
					}
				}
			}
			return scope;
		}
		return null;
	}

	private boolean include(Scope scope, int offset) {
		return offset >= scope.getOffset() && end(scope) > offset;
	}
	
	private boolean after(Scope scope, int offset) {
		return offset >= end(scope);
	}
}
