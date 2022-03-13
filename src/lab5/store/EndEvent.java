package lab5.store;

import lab5.general.State;
import lab5.general.EventQueue;
import lab5.general.GeneralEndEvent;

public class EndEvent extends GeneralEndEvent {
	private StoreState storeState;
	private State state;

	public EndEvent(StoreState storeState, State state, EventQueue eventQueue) {
		super(state, eventQueue);
		this.state = state;
		this.storeState = storeState;
	}

	public void execute() {
		storeState.setCurrentEvent(this);
		state.notifyObs();
		state.endSimulation();
	}

	public double getTime() {
		return 999;
	}

	public String getName() {
		return "Stopp";
	}

}
