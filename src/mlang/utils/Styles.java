package mlang.utils;

import mlang.TextAttribute;

import org.eclipse.jface.text.IRegion;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;

public class Styles {

	@Deprecated
	public static StyleRange makeStyle(TextAttribute attr) {
		StyleRange styleRange= new StyleRange();
		styleRange.fontStyle = attr.style & (SWT.ITALIC | SWT.BOLD | SWT.NORMAL);
		styleRange.strikeout= (attr.style & TextAttribute.STRIKETHROUGH) != 0;
		styleRange.underline= (attr.style & TextAttribute.UNDERLINE) != 0;
		if (attr.foreground != null) {
			styleRange.foreground = new Color(null, attr.foreground); //TODO!!!
		}
		if (attr.background != null) {
			styleRange.background = new Color(null, attr.background); //TODO!!!
		}
		return styleRange;
	}
	
	public static StyleRange makeStyle(org.eclipse.jface.text.TextAttribute attr, IRegion region) {
		StyleRange styleRange= new StyleRange();
		int style = attr.getStyle();
		styleRange.fontStyle = style & (SWT.ITALIC | SWT.BOLD | SWT.NORMAL);
		styleRange.strikeout= (style & TextAttribute.STRIKETHROUGH) != 0;
		styleRange.underline= (style & TextAttribute.UNDERLINE) != 0;
		styleRange.foreground = attr.getForeground(); //TODO!!!
		styleRange.background = attr.getBackground(); //TODO!!!
		if (region != null) {
			styleRange.start = region.getOffset();
			styleRange.length = region.getLength();
		}
		return styleRange;
	}
	
	public static StyleRange makeStyle(org.eclipse.jface.text.TextAttribute attr) {
		return makeStyle(attr, null);
	}
}
