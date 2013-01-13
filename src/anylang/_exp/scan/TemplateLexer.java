package anylang._exp.scan;

import org.eclipse.jface.text.rules.IToken;

import anylang._exp.Lexer2;


public class TemplateLexer implements Lexer2 {

	private final Lexer2 masterLexer;
	private final Lexer2 dataLexer;
	private final IToken dataToken;
	private IToken deferredToken;
	private CharSequence input;
	private State dataState;
	private int beginData;
	private MultiState defaultState;
	
	public TemplateLexer(Lexer2 masterLexer, Lexer2 dataLexer,
			IToken dataToken) {
		this.masterLexer = masterLexer;
		this.dataLexer = dataLexer;
		this.dataToken = dataToken;
		defaultState = new MultiState(masterLexer.defaultState(), dataLexer.defaultState());
	}
	
	@Override
	public IToken next() {
		if (dataProcessing()) {
			IToken dataNext = dataLexer.next();
			if (dataNext.isEOF()) {
				dataState = dataLexer.state();
				try {
					return deferredToken;
				} finally {
					deferredToken = null;
				}
			} else {
				return dataNext;
			}
		}
		
		IToken next = masterLexer.next();
		if (dataToken.equals(next)) {
		
			beginData = masterLexer.tokenOffset();
			
			while (dataToken.equals(next)) {
				next = masterLexer.next();
			}
			
			int endData = masterLexer.tokenOffset();
			deferredToken = next;
			dataLexer.init(input.subSequence(beginData, endData), dataState); 
			return next();
		} else {
			return next;
		}
	}

	@Override
	public State state() {
		return new MultiState(masterLexer.state(), dataState);
	}

	@Override
	public void init(CharSequence input, State state) {
		this.input = input;
		MultiState ts = (MultiState) state;
		dataState = ts.slaveState;
		masterLexer.init(input, ts.masterState);
	}

	@Override
	public int tokenOffset() {
		if (dataProcessing()) {
			return dataLexer.tokenOffset() + beginData; 
		} else {
			return masterLexer.tokenOffset();
		}
	}

	@Override
	public int offset() {
		if (dataProcessing()) {
			return dataLexer.offset() + beginData; 
		} else {
			return masterLexer.offset();
		}
	}
	
	private boolean dataProcessing() {
		return deferredToken != null;
	}

	@Override
	public int tokenLength() {
		if (dataProcessing()) {
			return dataLexer.tokenLength(); 
		} else {
			return masterLexer.tokenLength();
		}
	}

	@Override
	public State defaultState() {
		return defaultState;
	}

	@Override
	public Lexer2 getActiveLexer() {
		return (dataProcessing() ? dataLexer : masterLexer).getActiveLexer();
	}

	@Override
	public String getLanguage() {
		return masterLexer.getLanguage();
	}

	@Override
	public CharSequence value() {
		return getActiveLexer().value();
	}

}
