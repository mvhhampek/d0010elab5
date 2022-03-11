package lab5.store;

import lab5.general.Event;

public class CloseEvent extends Event {
	private StoreState storeState;

	public CloseEvent(StoreState storeState) {
		this.storeState = storeState;
	}

	public void execute() {
		storeState.close();
	}

	public double getTime() {
		return storeState.getCloseTime();
	}

	public String getName() {
		return "Stängning";
	}
}
