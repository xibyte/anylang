package anylang._exp;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.swt.graphics.RGB;

public enum CommonTokens implements IToken {

	KEYWORD(new RGB(127, 0, 85)), 
	CONSTANT, 
	M_COMMENT, 
	S_COMMENT, 
	BRACKETS(new RGB(0, 0, 125)),
	DOCLET, 
	WORD, 
	PARAM(new RGB(0, 125, 0)), 
	LITERAL(new RGB(125, 0, 0)),
	
	DEFAULT;

	;
	
	
	private final RGB defaultColor;
	
	private CommonTokens(RGB defaultColor) {
		this.defaultColor = defaultColor;
	}
	
	private CommonTokens() {
		this(null);
	}

	@Override
	public boolean isUndefined() {
		return false;
	}

	@Override
	public boolean isWhitespace() {
		return false;
	}

	@Override
	public boolean isEOF() {
		return false;
	}

	@Override
	public boolean isOther() {
		return true;
	}

	@Override
	public Object getData() {
		return name();
	}

	public RGB getDefaultColor() {
		return defaultColor;
	}
}
