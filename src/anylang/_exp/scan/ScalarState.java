package anylang._exp.scan;

public class ScalarState implements State {

	public final int value;
	
	public ScalarState(int value) {
		this.value = value;
	}

	@Override
	public boolean same(State state) {
		return state instanceof ScalarState
				&& ((ScalarState) state).value == value;
	}

	@Override
	public String toString() {
		return "state "+ value;
	}
	
}
