package js;

import java.util.List;

import mlang.PartHandleHelper;
import mlang.PartitionsExtractor;
import mlang.ScopeTraverser;
import mlang.ScopeTraverser.Handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipselabs.mlang.models.common.Doclet;
import org.eclipselabs.mlang.models.common.Literal;
import org.eclipselabs.mlang.models.common.Located;
import org.eclipselabs.mlang.models.common.MultiComment;
import org.eclipselabs.mlang.models.common.Scope;
import org.eclipselabs.mlang.models.common.SingleComment;

public class JSPartitionsExtractor implements PartitionsExtractor {

	@Override
	public void extract(Scope scope, List<ITypedRegion> parts,
			List<ITypedRegion> zones) {
		ScopeTraverser.accept(scope, new JSPartHandler(parts, zones, scope));
	}

	class JSPartHandler extends PartHandleHelper implements Handler {

		public JSPartHandler(List<ITypedRegion> parts,
				List<ITypedRegion> zones, Located forBlock) {
			super(forBlock, parts, zones, JS.DEFAULT);
		}

		@Override
		public boolean handle(EObject node) {
			if (node instanceof Doclet) {
				add((Located) node, JS.DOCLET);
			} else if (node instanceof MultiComment) {
				add((Located) node, JS.MULTI_COMMENT);
			} else if (node instanceof SingleComment) {
				add((Located) node, JS.SINGLE_COMMENT);
			} else if (node instanceof Literal) {
				add((Located) node, JS.STRING);
			}
			return true;
		}
	}
}
