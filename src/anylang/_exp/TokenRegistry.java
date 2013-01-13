package anylang._exp;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.rules.IToken;

public class TokenRegistry {

	private final Map<String, IToken> tokens = new HashMap<String, IToken>();
	
	{
		//registerEnum(CommonTokens.values());
	}
	
	public void registerEnum(Enum<?>[] values) {
		for (Enum<?> e : values) {
			tokens.put(e.name(), (IToken) e);
		}
	}
	
	public IToken getToken(String name) {
		IToken token = tokens.get(name);
		if (token == null) {
			throw new RuntimeException("unable to found token with name " + name);
		}
		return token;
	}

	public IToken findOrRegisterToken(String tokenLiteral) {
		IToken token = tokens.get(tokenLiteral);
		if (token == null) {
			token = register(tokenLiteral);
		}
		return token;
	}

	public IToken register(String tokenLiteral) {
		tokenLiteral = tokenLiteral.intern();
		IToken token = new SimpleToken(tokenLiteral);
		tokens.put(tokenLiteral, token);
		return token;
	}

	public IToken findToken(String tokenLiteral) {
		return tokens.get(tokenLiteral);
	}
}
