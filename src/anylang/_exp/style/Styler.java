package anylang._exp.style;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.TextStyle;

public interface Styler {

	void fill(StyleRange textStyle, IToken token);

}
