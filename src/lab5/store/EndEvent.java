package lab5.store;

import lab5.general.Event;

public class EndEvent extends Event  {
	private StoreState storeState;
	public EndEvent(StoreState storeState){
		this.storeState = storeState;
	}

	public void execute() {
		storeState.endSimulation();
	}

	public double getTime() {
		return 999;
	}

	public String getName() {
		return "Stopp";
	}
}
