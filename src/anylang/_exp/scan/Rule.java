package anylang._exp.scan;

import org.eclipse.jface.text.rules.IToken;

public interface Rule {

	boolean match(CharSequence input, int offset);
	
	int end();
	
	IToken token();
	
}
