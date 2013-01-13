package html;

import static mlang.utils.Loc.end;

import java.util.List;

import js.JSPartitionsExtractor;
import mlang.PartHandleHelper;
import mlang.PartitionsExtractor;
import mlang.ScopeTraverser;
import mlang.ScopeTraverser.Handler;
import mlang.utils.Loc;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipselabs.mlang.models.common.Comment;
import org.eclipselabs.mlang.models.common.Located;
import org.eclipselabs.mlang.models.common.Scope;
import org.eclipselabs.mlang.models.html.JSScope;
import org.eclipselabs.mlang.models.html.Tag;

public class HtmlPartitionsExtractor implements PartitionsExtractor {

	private final JSPartitionsExtractor jsExtractor = new JSPartitionsExtractor();

	@Override
	public void extract(Scope scope, List<ITypedRegion> parts,
			List<ITypedRegion> zones) {
		ScopeTraverser.accept(scope, new HtmlPartHandler(parts, zones, scope));
	}

	class HtmlPartHandler extends PartHandleHelper implements Handler {

		public HtmlPartHandler(List<ITypedRegion> parts,
				List<ITypedRegion> zones, Located forBlock) {
			super(forBlock, parts, zones, Html.DEFAULT);
		}

		@Override
		public boolean handle(EObject node) {
			if (node instanceof Comment) {
				add((Located) node, Html.COMMENT);
			} else if (node instanceof JSScope) {
				Scope scope = (Scope) node;
				endZone(scope.getOffset());
				jsExtractor.extract(scope, parts, zones);
				beginZone(end(scope));
				return false;
			} else if (node instanceof Tag) {
				add((Located) node, Html.TAG);
			}
			return true;
		}
	}
}
