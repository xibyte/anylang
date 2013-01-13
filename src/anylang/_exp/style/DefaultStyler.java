package anylang._exp.style;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import mlang.Logger;
import mlang.MLangContext;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.swt.custom.StyleRange;

public class DefaultStyler implements Styler {

	private final MLangContext ctx = new MLangContext();
	private final Map<String, Style> styles;
	
	public DefaultStyler() {
		Properties properties = new Properties();
		InputStream stream = getClass().getResourceAsStream("base.style");
		try {
			properties.load(stream);
		} catch (IOException e) {
			Logger.log(e);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				Logger.log(e);
			}
		}
		
		styles = new HashMap<String, Style>(properties.size());
		
		for (Map.Entry<Object, Object> e : properties.entrySet()) {
			String tokenName = e.getKey() + "";
			String def = e.getValue() + "";
			styles.put(tokenName, new Style(def));
		}
	}
	
	@Override
	public void fill(StyleRange textStyle, IToken token) {
		
		Object data = token.getData();
		
		if (!(data instanceof String)) {
			return;
		}
		
		Style style = styles.get(data);
		if (style == null) {
			return;
		}
		if (style.foreground != null) {
			textStyle.foreground = ctx.getColor(style.foreground); 
		}
		if (style.background != null) {
			textStyle.background = ctx.getColor(style.background); 
		}
		textStyle.fontStyle = style.font; 
	}

}
