package mlang;

import org.eclipse.jface.text.rules.ICharacterScanner;


public interface LiteralStrategy {

	void skip(ICharacterScanner scanner);

	boolean startSkip(ICharacterScanner scanner);
	
}
