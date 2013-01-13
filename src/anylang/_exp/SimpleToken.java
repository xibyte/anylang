package anylang._exp;

import org.eclipse.jface.text.rules.Token;

public class SimpleToken extends Token {

	public SimpleToken(Object data) {
		super(data);
	}

	@Override
	public String toString() {
		return "[" +getData() + "]";
	}
}
