package lab5.store;

import lab5.general.State;

import lab5.general.Event;

public class CloseEvent extends Event {
	private StoreState storeState;
	private State state;
	public CloseEvent(StoreState storeState, State state) {
		this.state = state;
		this.storeState = storeState;
	}

	public void execute() {
		storeState.updateTime(this);
		storeState.setCurrentEvent(this);
		state.notifyObs();
		storeState.close();
	}

	public double getTime() {
		return storeState.getCloseTime();
	}

	public String getName() {
		return "Stäng";
	}
}
