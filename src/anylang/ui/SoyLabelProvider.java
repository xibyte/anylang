package anylang.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipselabs.mlang.models.soy.Doclet;
import org.eclipselabs.mlang.models.soy.Tag;
import org.eclipselabs.mlang.models.soy.TemplateTag;
import org.eclipselabs.mlang.models.soy.Variable;

public class SoyLabelProvider extends LabelProvider {
	
	@Override
	public String getText(Object element) {
		
		if (element instanceof TemplateTag) {
			return "TEMPLATE: " + ((TemplateTag) element).getName();  
		} else if (element instanceof Tag) {
			return ((Tag) element).getTagName();
		} else if (element instanceof Doclet) {
			return "PARAMS";
		} else if (element instanceof Variable) {
			return ((Variable) element).getName();
		} else {
			return element + "";
		}
	}
}
