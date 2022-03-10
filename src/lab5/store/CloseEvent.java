package lab5.store;

import lab5.general.Event;
import lab5.general.State;

public class CloseEvent extends Event {
	private StoreState storeState;
	private State state;

	public CloseEvent(State state) {
		this.state = state;
		storeState = state.getStore();
	}

	public void execute() {
		storeState.close();
	}

	public double getTime() {
		return storeState.getCloseTime();
	}

	public Customer getCustomer() {
		return null;
	}

	public String getName() {
		return "Stängning";
	}
}
