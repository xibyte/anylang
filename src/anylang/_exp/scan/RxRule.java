package anylang._exp.scan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.rules.IToken;

public class RxRule implements Rule {

	protected Pattern pattern;
	protected IToken token;
	protected Matcher matcher;
	
	public RxRule(Pattern pattern, IToken token) {
		this.pattern = pattern;
		this.token = token;
	}

	@Override
	public boolean match(CharSequence input, int offset) {
		matcher = pattern.matcher(input);
		matcher.useTransparentBounds(true); // allow lookBehind and lookAhead
		matcher.region(offset, input.length());
		return matcher.lookingAt();
	}

	@Override
	public int end() {
		return matcher.end();
	}

	@Override
	public IToken token() {
		return token;
	}
	
	@Override
	public String toString() {
		return "rule[" + pattern + "] -> " + token();
	}
}
