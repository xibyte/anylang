package mlang;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class MLangContext implements ColorManager {

	protected Map<RGB, Color> colors = new HashMap<RGB, Color>();
	protected Map<FontData, Font> fonts = new HashMap<FontData, Font>();

	public void dispose() {
		for (Color color : colors.values()) {
			color.dispose();
		}
		for (Font font : fonts.values()) {
			font.dispose();
		}
	}
	
	public synchronized Color getColor(RGB rgb) {
		if (rgb == null) {
			return null;
		}
		Color color = colors.get(rgb);
		if (color == null) {
			color = new Color(getDisplay(), rgb);
			colors.put(rgb, color);
		}
		return color;
	}
	
	public synchronized Font getFont(FontData fontData) {
		if (fontData == null) {
			return null;
		}
		Font font = fonts.get(fontData);
		if (font == null) {
			font = new Font(getDisplay(), fontData);
			fonts.put(fontData, font);
		}
		return font;
	}

	private Display getDisplay() {
		return Display.getCurrent();
	}
	
	public Scheme getColorScheme(Class<? extends Language> lang) {
		return null;
	}
	
	public IToken makeToken(RGB color) {
		return makeToken(color, SWT.NORMAL);
	}

	public IToken makeToken(RGB color, int style) {
		return new Token(new TextAttribute(getColor(color), null, style));
	}

	public IToken makeToken(mlang.TextAttribute attr) {
		TextAttribute converted = attr.toJFaceAttr(this);
		return new Token(converted);
	}

	public IToken makeToken(DefaultAttrAware styleType) {
		return new Token(textAttrForStyle(styleType));
	}
	
	public TextAttribute textAttrForStyle(DefaultAttrAware styleType) {
		mlang.TextAttribute attr = styleType.getDefault();
		return attr.toJFaceAttr(this);
	}
	
}
