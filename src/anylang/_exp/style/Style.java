package anylang._exp.style;

import static java.lang.Integer.valueOf;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class Style {

	public final RGB foreground;
	public final RGB background;
	public final int font;
	
	public Style(RGB foreground, RGB background, int font) {
		this.foreground = foreground;
		this.background = background;
		this.font = font;
	}
	
	public Style(String def) {

		String[] parts = def.split(",");
		
		if (parts.length > 0) {
			String[] fgBg = parts[0].split(":");
			this.foreground = extractColor(fgBg[0].trim());
			if (fgBg.length > 1) {
				this.background = extractColor(fgBg[1].trim());
			} else {
				this.background = null;
			}
		} else {
			this.foreground = null;
			this.background = null;
		}

		if (parts.length > 1) {
			this.font = extractFont(parts[1].trim());
		} else {
			this.font = SWT.NORMAL;
		}
		
		
	}

	private int extractFont(String def) {
		int font = SWT.NORMAL;
		if ("bold".equals(def)) {
			font = font | SWT.BOLD; 
		}
		if ("italic".equals(def)) {
			font = font | SWT.ITALIC; 
		}
		return font;
	}

	private RGB extractColor(String def) {
		if (def.length() != 7) {
			return null;
		}
		
		String r = def.substring(1, 3);
		String g = def.substring(3, 5);
		String b = def.substring(5, 7);
		
		return new RGB(valueOf(r, 16), valueOf(g, 16), valueOf(b, 16));
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.valueOf("10", 16));
	}
	
}
