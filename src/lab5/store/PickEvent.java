package lab5.store;

import lab5.general.Event;
import lab5.general.State;
import lab5.general.EventQueue;

public class PickEvent extends Event {
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	private CustomerQueue customerQueue;

	public PickEvent(State state, EventQueue eventQueue, double time) {
		this.state = state;
		this.eventQueue = eventQueue;
		this.time = time;
		storeState = state.getStore();
		customerQueue = storeState.getCustomerQueue();
	}

	public void execute() {
		if (storeState.freeCheckouts() > 0) {
			eventQueue.push(new PayEvent(state, eventQueue, time));
			storeState.changeOccupiedCheckouts(1);
			
		} else {
			// ställ i kön och sedan nytt pay event?
			
			// ställ(the kund).in.theKö();
			eventQueue.push(new PayEvent(state, eventQueue, time));
		}

	}

	public double getTime() {
		return time;
	}

	public Customer getCustomer() {
		return customer;
	}
}
