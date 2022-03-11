package lab5.store;

import lab5.general.State;

import lab5.general.Event;

public class EndEvent extends Event  {
	private StoreState storeState;
	private State state;
	public EndEvent(StoreState storeState, State state){
		this.state=state;
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
