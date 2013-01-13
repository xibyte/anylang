package html.lexer;

import mlang.IElementType;

public enum HTMLElementTypes implements IElementType {

	START_TAG,
	END_TAG,
	START_SCRIPT_TAG,
	CLOSING_SCRIPT_TAG,
	CLOSING_TAG,
	COMMENT,
	STRING,
	OTHER, 
	PARAM,
	TAG_NAME, 
	END_SINGLE_TAG,
	
	
}
