package anylang._exp.scan;

import static java.util.Arrays.fill;
import static java.util.regex.Pattern.MULTILINE;
import static org.eclipse.jface.text.rules.Token.UNDEFINED;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

import anylang._exp.Lexer2;


public class RuleLexer implements Lexer2 {

	private static final ScalarState DEFAULT_SCALAR_STATE = new ScalarState(DEFAULT_STATE);

	private final Action[][] actions;
	private final IToken[] defaults;
	
	private CharSequence input;
	private int state;

	private int offset;
	private int length;

	private final String language;
	

	public RuleLexer(Action[][] actions, IToken[] defaults, String language) {
		this.actions = actions;
		this.defaults = defaults;
		this.language = language;
	}
	
	public RuleLexer(Action[][] actions, String language) {
		this(actions, new IToken[actions.length], language);
		fill(defaults, UNDEFINED);
	}

	public void setDefault(int state, IToken token) {
		defaults[state] = token;
	}
	
	@Override
	public void init(CharSequence input, State state) {
		this.input = input;
		this.state = ((ScalarState) state).value;
		offset = 0;
		length = 0;
	}

	@Override
	public IToken next() {
		if (offset >= input.length()) {
			length = 0;
			return Token.EOF;
		}

		Action[] hs = actions[state];
		
		for (int i = 0; i < hs.length; ++i) {
			Action h = hs[i];
			
			Rule rule = h.rule;
			
			if (rule.match(input, offset)) {
				if (h.state >= 0) {
					state = h.state;
				}
				length = rule.end() - offset;
				if (length <= 0) {
					length = 1;
				}
				offset += length;
				return rule.token();
			}
		}
		offset++;
		length = 1;
		
		return defaults[state];
	}

	
	/**
	 * Like jflex
	 */
	public IToken nextGreedy() {
		if (offset >= input.length()) {
			length = 0;
			return Token.EOF;
		}

		Action[] hs = actions[state];
		int[] matches = new int[hs.length];
		for (int i = 0; i < hs.length; ++i) {
			Action h = hs[i];
			
			Rule rule = h.rule;
			
			if (rule.match(input, offset)) {
				matches[i] = rule.end() - offset;
			}
		}
		
		int maxLength = 0;
		Action bestMatched = null;
		for (int i = 0; i < hs.length; ++i) {
			if (matches[i] > maxLength) {
				maxLength = matches[i];
				bestMatched = hs[i];
			}
		}
		
		if (bestMatched == null) {
			offset++;
			length = 1;
			return defaults[state];
		} else {
			length = bestMatched.rule.end() - offset;
			if (bestMatched.state >= 0) {
				state = bestMatched.state;
			}
			if (length <= 0) {
				length = 1;
			}
			offset += length;
			return bestMatched.rule.token();
		}
	}

	@Override
	public int offset() {
		return offset;
	}
	
	@Override
	public int tokenOffset() {
		return offset - length;
	}
	
	@Override
	public int tokenLength() {
		return length;
	}
	
	@Override
	public State state() {
		return new ScalarState(state);
	}

	@Override
	public State defaultState() {
		return DEFAULT_SCALAR_STATE;
	}

	@Override
	public Lexer2 getActiveLexer() {
		return this;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public CharSequence value() {
		return input.subSequence(offset - length, offset);
	}
	
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(".*$", MULTILINE);
		Matcher matcher = pattern.matcher("111\n222\n333");
//		matcher.region(4, 9);
		System.out.println(matcher.lookingAt());
		System.out.println(matcher.group());
	}
}
