package mlang;

import org.eclipse.emf.ecore.EObject;
import org.eclipselabs.mlang.models.common.Scope;

public class ScopeTraverser {

	public static void accept(Scope scope, Handler handler) {
		handler.begin();
		try {
			acceptInternal(scope, handler);
		} finally {
			handler.done();
		}
	}
	
	private static boolean acceptInternal(Scope scope, Handler handler) {
		if (handler.handle(scope)) {
			for (EObject node : scope.getBody()) {
				if (node instanceof Scope) {
					if (!acceptInternal((Scope) node, handler)) {
						break;
					}
				} else {
					if (!handler.handle(node)) {
						break;
					}
				}
			}		
		}
		return true;
	}

	public static interface Handler {

		boolean handle(EObject node);

		void begin();
		
		void done();
	}
}
