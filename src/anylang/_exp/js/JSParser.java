package anylang._exp.js;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.rules.IToken;

import anylang._exp.Lexer2;
import anylang._exp.Parser2;
import anylang._exp.dom.Attrs;
import anylang._exp.dom.Scope;
import anylang._exp.dom.Variable;


public class JSParser implements Parser2 {

	enum Scopes {
		FUNCTION;
	}
	
	enum State {
		DEFAULT, FUNCTION_NAME, ARGUMENTS, WAIT_FUNCTION_BODY, VAR_ASSIGMENT
	}

	private Lexer2 lexer;
	private Scope scope;
	private State state;
	private String value;
	private Scope root;
	private String prevWord;
	private boolean dereference;
	
	public void init(Scope root, IProgressMonitor monitor, Lexer2 lexer) {
		this.root = root; 
		scope = root;
		this.lexer = lexer;
		state = State.DEFAULT;
		prevWord = null;
		dereference = false;
	}
	
	@Override
	public void build() throws Exception {
	
		for (IToken itoken = lexer.next(); !itoken.isEOF(); itoken = lexer.next()) {
			Object data = itoken.getData();
			if (!(data instanceof String)) {
				continue;
			}
			
			String token = (String) itoken.getData(); System.out.println(token);
			value = null;
			
			switch (state) {
			case DEFAULT:
				boolean dot = false;
				switch (token) {
				case "KEYWORD":
					
					switch (value()) {
					case "function":
						openScope();
						setType(Scopes.FUNCTION);
						state = State.FUNCTION_NAME;
						break;
					}
					break;
				case "OPEN_BRACE":
					openScope();
					break;
				case "CLOSE_BRACE":
					closeScope();
					break;
				case "WORD":
					prevWord = value();
					break;
				case "DOT":
					dot = true;
					break;
				case "ASSIGN":
					handleAssign();
					break;
				}
				dereference = dot;
				break;
			case FUNCTION_NAME:
				switch (token) {
				case "WORD":
					setScopeName();
					break;
				case "OPEN_BRACKET":
					state = State.ARGUMENTS;
					break;
				}
				break;
			case ARGUMENTS:
				
				switch (token) {
				case "WORD":
					addArgument();
					break;
				case "CLOSE_BRACKET":
					state = State.WAIT_FUNCTION_BODY;
					break;
				}
				break;
			case WAIT_FUNCTION_BODY:
				switch (token) {
				case "OPEN_BRACE":
					state = State.DEFAULT;
					break;
				case "CLOSE_BRACE":
					closeScope();
					state = State.DEFAULT;
					break;
				}
				
				break;
			}
		}
	}

	private void handleAssign() {
		if (dereference) {
			return;
		}
		if (prevWord == null) {
			return;
		}
		Scope scope = this.scope;
		while (scope != null) {
			if (!scope.hasVar(prevWord)) {
				scope.getVariables().add(new Variable(prevWord, null));
				break;
			}
			
			if (scope == root) {
				break;
			}
			scope = scope.parent;
		}
	}

	private void closeScope() {
		Scope parent = scope.getParent();
		if (parent != root) {
			scope = parent;
		}
	}

	private void addArgument() {
		scope.getVariables().add(new Variable(value(), null));
	}

	private void setType(Object type) {
		scope.setAttribute(Attrs.TYPE, type);
	}

	private void setScopeName() {
		scope.setAttribute(Attrs.NAME, value());
	}

	private void openScope() {
		Scope scope = new Scope();
		this.scope.add(scope);
		this.scope = scope;
	}

	public String value() {
		if (value == null) {
			value = lexer.value().toString();
		}
		return value; 
	}
}
