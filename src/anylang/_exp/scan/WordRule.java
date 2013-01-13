package anylang._exp.scan;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class WordRule extends RxRule {

	public static class Config {
		
		protected List<IToken> tokens = new ArrayList<IToken>();
		protected List<Set<String>> words = new ArrayList<Set<String>>();
		
		public Config add(IToken forToken, String... words) {
			tokens.add(forToken);
			this.words.add(new HashSet<String>(asList(words)));
			return this;
		}

		protected IToken tokenForWord(String word) {
			for (int i = 0; i < words.size();++i) {
				if (words.get(i).contains(word)) {
					return tokens.get(i);
				}
			}
			return null;
		}
	}
	
	private final Config config;
	private final IToken anyWordToken;

	public WordRule(IToken anyWordToken, Config config) {
		this("[\\$_\\w]+", anyWordToken, config);
	}
	
	public WordRule(String wordPattern, IToken anyWordToken, Config config) {
		super(Pattern.compile(wordPattern), Token.UNDEFINED);
		this.anyWordToken = anyWordToken;
		this.config = config;
	}

	@Override
	public boolean match(CharSequence input, int offset) {
		token = null;
		if (super.match(input, offset)) {
			token = config.tokenForWord(matcher.group());
			if (token == null) {
				token = anyWordToken;
			}
			return true;
		} 
		return false;
	}
	
	@Override
	public String toString() {
		return "word rule";
	}
}
