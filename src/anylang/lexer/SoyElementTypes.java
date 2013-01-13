package anylang.lexer;

import mlang.IElementType;

public enum SoyElementTypes implements IElementType {

	BAD_CHARACTER,
	COMMENT,
	DOCLET_START, 
	DOCLET_END, 
	PARAM, 
	SCRIPT_NAME, 
	SCRIPT_START, 
	SCRIPT_END, 
	STRING, 
	NAME, 
	TEMPLATE_DATA, 
	VARIABLE, 
	WHITE_SPACE;

}
