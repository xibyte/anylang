package js;

import mlang.DefaultAttrAware;
import mlang.TextAttribute;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public enum JSStyles implements DefaultAttrAware {

	KEYWORD(new TextAttribute(new RGB(127, 0, 85), null, SWT.BOLD)),
	DOCLET(new TextAttribute(new RGB(127, 159, 191))),
	TODO(new TextAttribute(new RGB(127, 159, 191))),
	COMMENT(new TextAttribute(new RGB(63, 127, 95))),
	STRING(new TextAttribute(new RGB(42, 0, 255)));
	
	private final TextAttribute attr;

	private JSStyles(TextAttribute attr) {
		this.attr = attr;
	}

	@Override
	public TextAttribute getDefault() {
		return attr;
	}
}
