package anylang._exp.scan;

public class Action {
	
	final Rule rule;
	final int state;
	
	public Action(Rule rule, int state) {
		this.rule = rule;
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "action on " + rule + " switches to state " + state;
	}
}