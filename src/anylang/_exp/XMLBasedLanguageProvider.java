package anylang._exp;

import static java.util.regex.Pattern.MULTILINE;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mlang.Logger;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.located.Located;
import org.jdom2.located.LocatedJDOMFactory;

import anylang._exp.scan.Action;
import anylang._exp.scan.Rule;
import anylang._exp.scan.RxRule;
import anylang._exp.scan.WordRule;
import anylang._exp.scan.WordRule.Config;


public class XMLBasedLanguageProvider implements LanguageProvider {

	private static final String DEFAULT = "default";
	private final Map<String, InputProvider> inputs = new HashMap<String, InputProvider>();
	private final LanguagesRegistry languagesRegistry;
	private final TokenRegistry tokenRegistry;
		
	public XMLBasedLanguageProvider(LanguagesRegistry languagesRegistry,
			TokenRegistry tokenRegistry) {
		this.languagesRegistry = languagesRegistry;
		this.tokenRegistry = tokenRegistry;
	}

	
	public void addInput(String id, InputProvider langDefinition) {
		inputs.put(id, langDefinition);
	}

	@Override
	public boolean has(String id) {
		return inputs.containsKey(id);
	}

	@Override
	public Language2 make(String id) {
		InputProvider inputProvider = inputs.get(id);
		if (inputProvider != null) {
			InputStream inputStream = inputProvider.openStream();
			try {
				try {
					return parse(inputStream);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	private Language2 parse(InputStream inputStream) throws JDOMException, IOException {
	    SAXBuilder parser = new SAXBuilder();
	    parser.setJDOMFactory(new LocatedJDOMFactory());
		Document doc = parser.build(inputStream);
		Element lang = doc.getRootElement();

		LanguageImpl language = new LanguageImpl();
		language.setId(attr(lang, "id"));

		
		LexerDefinition lexdef = parseLexer(lang.getChild("lexer"));
		Element generates = lang.getChild("generates");
		
		if (generates != null) {
			String langRef = attr(generates, "lang");
			if (langRef != null) {
				final Language2 templated = languagesRegistry.get(langRef);
				String into = attr(generates, "into");
				if (into != null) {
					lexdef.dataToken = tokenRegistry.findOrRegisterToken(into);
				} else {
					lexdef.dataToken = Token.UNDEFINED;
				}
				lexdef.dataLanguage = templated;
			}
		}
		language.setLexerDefinitoin(lexdef);
		
		Provider<Parser2> parserProvider = parseParser(lang.getChild("parser"));
		language.setParserProvider(parserProvider);
		
		return language;
	}

	@SuppressWarnings("unchecked")
	private Provider<Parser2> parseParser(Element parserNode) {
		if (parserNode == null) {
			return null;
		}
		String className = parserNode.getAttributeValue("class");
		if (className != null) {
			try {
				return new ClassRefParserProvider((Class<Parser2>) Class.forName(className));
			} catch (ClassNotFoundException e) {
				Logger.warn("unable to load parser class: "+className);
			}
		}
		return null;
	}


	private LexerDefinition parseLexer(Element lexerEl) {
		if (lexerEl== null) {
			throw new UnsupportedOperationException("lexer can not be null"); 
		}
		StatesProcessor statesProcessor = new StatesProcessor(lexerEl.getChildren("when"));

		LexerDefinition lexdef = new LexerDefinition(statesProcessor.actions);
		
		for (Element def : lexerEl.getChildren("default")) {
			String forState = attr(def, "for");
			String tokenLiteral = attr(def, "token");
			if (tokenLiteral == null || tokenLiteral == null) {
				continue;
			}
			StateInfo stateInfo = statesProcessor.states.get(forState);
			if (stateInfo == null) {
				Logger.warn("invalid state reference " + forState + " at " + location(def));
			}
			IToken token = tokenRegistry.findOrRegisterToken(tokenLiteral);
			lexdef.setDefault(stateInfo.index, token);
		}
		
		for (Element inject : lexerEl.getChildren("inject")) {
			String into = attr(inject, "into");
			if (into != null) {
				IToken token = tokenRegistry.findToken(into);
				if (token == null) {
					Logger.warn("missing token " + into + " at "+location(inject));
					continue;
				}
				Language2 langRef = languagesRegistry.get(attr(inject, "lang"));
				if (langRef != null) {
					lexdef.injection.put(token, langRef);
				}
			}
		}

		return lexdef;
	}

	class StatesProcessor {
		
		final Map<String, StateInfo> states = new HashMap<String, StateInfo>();
		final List<StateInfo> statesList = new ArrayList<StateInfo>();
		private ActionDef[][] actions;
		int index = 0;
		
		private StatesProcessor(List<Element> whenElements) {

			addInfo(DEFAULT, new StateInfo());
			for (Element e : whenElements) {
				String whenStates = attr(e, "state");
				if (whenStates == null) {
					continue;
				}
				for (String when : whenStates.split(",")) {
					when = when.trim();
					StateInfo info = states.get(when);
					if (info == null) {
						info = new StateInfo();
						addInfo(when, info);  
					}
					info.ruleElements.addAll(e.getChildren());
				}
			}
			
			actions = new ActionDef[statesList.size()][];
			
			for (int i = 0; i < statesList.size(); ++i) {
				StateInfo stateInfo = statesList.get(i);

				List<ActionDef> acts = new ArrayList<ActionDef>(stateInfo.ruleElements.size());
				
				for (Element e : stateInfo.ruleElements) {
					Rule rule = makeRule(e);
					if (rule != null) {
						int stateId = -1;
						String switchToKey = e.getAttributeValue("switch");
						if (switchToKey != null) {
							StateInfo switchTo = states.get(switchToKey);
							if (switchTo == null) {
								Logger.warn("Unable to find state " + switchToKey);
							} else {
								stateId = switchTo.index; 
							}
						}
						
						Action action = new Action(rule, stateId);
						acts.add(new ActionDef(action, getScopes(e))); 
					}
				}
				actions[i] = new ActionDef[acts.size()];
				acts.toArray(actions[i]);
			}
		}
		
		private String[] getScopes(Element e) {
			String scopes = e.getAttributeValue("scope");
			if (scopes == null) {
				return null;
			}
			
			return scopes.split(",");
		}

		private Rule makeRule(Element e) {
			if ("pattern".equals(e.getName())) {
				return makePattern(e);
			} else if ("word".equals(e.getName())) {
				return makeWordRule(e);
			}
			return null;
		}

		private Rule makePattern(Element e) {
			String exp = attr(e, "exp");
			if (exp == null) {
				return null;
			}
			IToken token = getToken(e);
			return new RxRule(Pattern.compile(exp, MULTILINE), token);
		}

		private Rule makeWordRule(Element e) {
			IToken token = getToken(e);
			Config config = new WordRule.Config();
			for (Element keywords : e.getChildren("keywords")) {
				config.add(getToken(keywords), keywords.getValue().trim().split("\\s+"));
			}
			String wordPattern = e.getAttributeValue("pattern");
			if (wordPattern == null) {
				return new WordRule(token, config);
			} else {
				return new WordRule(wordPattern, token, config);
			}
		}
		
		private void addInfo(String when, StateInfo info) {
			states.put(when, info);
			statesList.add(info);
			info.index = index ++;
		}
	}
	
	private IToken getToken(Element e) {
		String tokenLiteral = e.getAttributeValue("token");
		if (tokenLiteral != null) {
			return tokenRegistry.findOrRegisterToken(tokenLiteral);
		}
		return Token.UNDEFINED;
	}

	public static String attr(Element e, String attr) {
		String value = e.getAttributeValue(attr);
		if (value == null) {
			Logger.warn("Missing attribute " + attr + " for element "
					+ e.getName() + " at " + location(e));
			return null;
		}
		return value;
	}
	
	private static String location(Element e) {
		Located loc = (Located) e;
		return "[" + loc.getLine() + " : " + loc.getColumn() + "]";
	}

	class StateInfo {
		List<Element> ruleElements = new ArrayList<Element>();
		List<Action> actions = new ArrayList<Action>();
		int index;
	}
	
	public static void main(String[] args) {
		
		Pattern pattern = Pattern.compile(".(?<!\\S)/dev/null(?!\\S).");
		
		Matcher matcher = pattern.matcher(" /dev/null ");
		
		matcher.region(1, 11);
		
		System.out.println(matcher.lookingAt());
	}
//	public static void mai	n(String[] args) {
//		
//		Lexer2 oldLexer = TestLang.make
//		
//		
//		
//		LanguagesRegistry languagesRegistry = new LanguagesRegistry();
//		XMLBasedLanguageProvider lp = new XMLBasedLanguageProvider(languagesRegistry, new TokenRegistry());
//		lp.addInput("html", new ClassPathInputProvider("/grammar/js.lang"));
//		lp.addInput("js", new ClassPathInputProvider("/grammar/html.lang"));
//		lp.addInput("test", new ClassPathInputProvider("/grammar/test.lang"));
//		languagesRegistry.register(lp);
//
//		
//		
//		
//		newLexer.
//	}
	
}
