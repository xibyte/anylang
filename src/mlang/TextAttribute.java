package mlang;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

public class TextAttribute {

	public static final int STRIKETHROUGH= 1 << 29;
	public static final int UNDERLINE= 1 << 30;
	
	public final RGB foreground;
	public final RGB background;
	public final int style;
	public FontData font;

	public TextAttribute(RGB foreground, RGB background, int style) {
		this.foreground= foreground;
		this.background= background;
		this.style= style;
	}

	public TextAttribute(RGB foreground) {
		this(foreground, null, SWT.NORMAL);
	}

	public org.eclipse.jface.text.TextAttribute toJFaceAttr(MLangContext ctx) {
		return new org.eclipse.jface.text.TextAttribute(ctx.getColor(foreground),
				ctx.getColor(background), style, ctx.getFont(font));
	}
}
