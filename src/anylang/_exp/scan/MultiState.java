package anylang._exp.scan;

public class MultiState implements State {

	public final State masterState;
	public final State slaveState;
	
	public MultiState(State masterState, State slaveState) {
		this.masterState = masterState;
		this.slaveState = slaveState;
	}

	@Override
	public boolean same(State state) {
		return masterState.same(state);
	}
	
	@Override
	public String toString() {
		return "MultiState [Master: " + masterState + ", Slave: " + slaveState + "]";
	}
}
