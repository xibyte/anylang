package anylang.editors;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class CommonWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return isWhitespaceChar(c);
	}
	
	public static boolean isWhitespaceChar(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
