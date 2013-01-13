package html;

import mlang.DefaultAttrAware;
import mlang.TextAttribute;

import org.eclipse.swt.graphics.RGB;

public enum HtmlStyles implements DefaultAttrAware {

	TAG(new TextAttribute(new RGB(0, 0, 128))), 
	PARAM(new TextAttribute(new RGB(128, 0, 0))), 
	STRING(new TextAttribute(new RGB(0, 128, 0))), 
	COMMENT(new TextAttribute(new RGB(128, 128, 128))), 
	CDATA(new TextAttribute(new RGB(128, 128, 128)));
	
	private final TextAttribute attr;

	private HtmlStyles(TextAttribute attr) {
		this.attr = attr;
	}

	@Override
	public TextAttribute getDefault() {
		return attr;
	}
}
