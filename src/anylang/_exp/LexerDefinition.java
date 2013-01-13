package anylang._exp;

import static java.util.Arrays.fill;
import static org.eclipse.jface.text.rules.Token.UNDEFINED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.text.rules.IToken;

import anylang._exp.scan.Action;
import anylang._exp.scan.MultiLexer;
import anylang._exp.scan.RuleLexer;
import anylang._exp.scan.TemplateLexer;


public class LexerDefinition {

	public final ActionDef[][] actionDefs;
	public final Map<IToken, Language2> injection = new HashMap<IToken, Language2>();
	public IToken dataToken = UNDEFINED;
	public Language2 dataLanguage;
	
	private final IToken[] defaults;
	
	public LexerDefinition(ActionDef[][] actions) {
		this.actionDefs = actions;
		defaults = new IToken[actions.length];
		fill(defaults, UNDEFINED);
	}
	
	public Lexer2 make(String language, String... scopes) {
		
		Action[][] actions = new Action[actionDefs.length][];
		
		for (int i = 0;i < actions.length; ++i) {
			ActionDef[] defsForState = actionDefs[i];
			List<Action> scopeActtions = new ArrayList<Action>();
			for (ActionDef def : defsForState) {
				if (def.inScopes(scopes)) {
					scopeActtions.add(def.getAction());
				}
			}
			actions[i] = new Action[scopeActtions.size()];  
			scopeActtions.toArray(actions[i]);
		}
		
		Lexer2 lexer = new RuleLexer(actions, defaults, language);
		if (!injection.isEmpty()) {
			HashMap<IToken, Lexer2> injectedLexers = new HashMap<IToken, Lexer2>(); 
			for (Map.Entry<IToken, Language2> inject : injection.entrySet()) {
				injectedLexers.put(inject.getKey(), inject.getValue().makeLexer(scopes));
			}
			lexer = new MultiLexer(lexer, injectedLexers);
		}
		if (dataLanguage != null) {
			lexer = new TemplateLexer(lexer, dataLanguage.makeLexer(scopes), dataToken);
		}
		return lexer;
	}
	
	public void setDefault(int state, IToken token) {
		defaults[state] = token;
	}
}
