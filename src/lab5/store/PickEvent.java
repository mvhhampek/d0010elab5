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

	public PickEvent(State state, EventQueue eventQueue, double time, Customer customer) {
		this.state = state;
		this.eventQueue = eventQueue;
		this.time = time;
		this.customer = customer;
		storeState = state.getStore();
		customerQueue = storeState.getCustomerQueue();
	}

	public void execute() {
		if (storeState.getFreeCheckouts() > 0) {
			storeState.occupyACheckout();
			eventQueue.push(new PayEvent(state, eventQueue, time, customer));			
		} else {
			customerQueue.add(customer);
		}
	}

	public double getTime() {
		return time;
	}

	public Customer getCustomer() {
		return customer;
	}
}
