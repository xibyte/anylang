package js.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipselabs.mlang.models.js.Argument;
import org.eclipselabs.mlang.models.js.Function;

public class JsLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Function) {
			return ((Function) element).getName();
		} else if (element instanceof Argument) {
			return ((Argument) element).getName();
		}
		return super.getText(element);
	}
}
