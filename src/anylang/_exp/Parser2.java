package anylang._exp;

import org.eclipse.core.runtime.IProgressMonitor;

import anylang._exp.dom.Scope;


public interface Parser2 {

	void init(Scope root, IProgressMonitor monitor, Lexer2 lexer) throws Exception;

	void build() throws Exception;
	
}
