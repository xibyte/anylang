package anylang._exp.scan;


public interface State {

	boolean same(State state);
	
	String toString();
	
}
