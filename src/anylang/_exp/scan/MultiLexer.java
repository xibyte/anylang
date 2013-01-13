package anylang._exp.scan;

import java.util.Map;

import mlang.Logger;

import org.eclipse.jface.text.rules.IToken;

import anylang._exp.Lexer2;


public class MultiLexer implements Lexer2 {

	private final Lexer2 mainLexer;
	private IToken deferredToken;
	private Lexer2 injectedLexer;
	private CharSequence input;
	private int beginInjecting;
	private MultiState defaultState;
	private State propogatedState;
	private final Map<IToken, Lexer2> injection;
	
	public MultiLexer(Lexer2 mainLexer, Map<IToken, Lexer2> injection) {
		this.mainLexer = mainLexer;
		this.injection = injection;
		defaultState = new MultiState(mainLexer.defaultState(), null);
	}
	
	@Override
	public IToken next() {
		if (deferredToken != null) {
			IToken injectNext = injectedLexer.next();
			if (injectNext.isEOF()) { 
				try {
					return deferredToken;
				} finally {
					deferredToken = null;
					//injected lexer will be destroyed on the next step 
					//to have proper state 
				}
			} else {
				return injectNext;
			}
		}
		
		injectedLexer = null;
		try {
			IToken next = mainLexer.next();
			injectedLexer = lexerForToken(next);
			if (injectedLexer != null) {
			
				IToken injectingToken = next;	
				
				beginInjecting = mainLexer.tokenOffset();
				
				do {
					next = mainLexer.next();
				} while (injectingToken.equals(next));
				
				int endInjecting = mainLexer.tokenOffset();
				deferredToken = next;
				Logger.debug("Begin Injecting region " + beginInjecting + " : " + endInjecting);
				State state = propogatedState == null ? injectedLexer.defaultState() : propogatedState; 
				injectedLexer.init(input.subSequence(beginInjecting, endInjecting), state); 
				return next();
			} else {
				return next;
			}
		} finally {
			//Can be apllyied only at starting 
			propogatedState = null;
		}
	}

	private Lexer2 lexerForToken(IToken token) {
		return injection.get(token);
	}

	@Override
	public State state() {
		if (injectedLexer != null) {
			return new MultiState(mainLexer.state(), injectedLexer.state());
		} else {
			return new MultiState(mainLexer.state(), null);
		}
	}

	@Override
	public void init(CharSequence input, State state) {
		this.input = input;
		MultiState ms = (MultiState) state;
		propogatedState = ms.slaveState;
		mainLexer.init(input, ms.masterState);
	}
	
	@Override
	public int offset() {
		if (deferredToken != null) {
			return injectedLexer.offset() + beginInjecting; 
		} else {
			return mainLexer.offset();
		}
	}
	
	@Override
	public int tokenOffset() {
		if (deferredToken != null) {
			return injectedLexer.tokenOffset() + beginInjecting; 
		} else {
			return mainLexer.tokenOffset();
		}
	}

	@Override
	public int tokenLength() {
		if (deferredToken != null) {
			return injectedLexer.tokenLength(); 
		} else {
			return mainLexer.tokenLength();
		}
	}

	@Override
	public State defaultState() {
		return defaultState;
	}

	@Override
	public Lexer2 getActiveLexer() {
		return (injectedLexer == null ? mainLexer : injectedLexer).getActiveLexer();
	}

	@Override
	public String getLanguage() {
		return mainLexer.getLanguage();
	}

	@Override
	public CharSequence value() {
		return getActiveLexer().value();
	}
}
