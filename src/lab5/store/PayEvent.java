package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;
import lab5.general.State;

public class PayEvent extends Event {
	private Customer customer;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	private CustomerQueue queue;
	private State state;

	public PayEvent(StoreState storeState,State state, EventQueue eventQueue, double time, Customer customer) {
		this.state = state;
		this.customer = customer;
		this.eventQueue = eventQueue;
		this.time = time;
		this.storeState = storeState;
		queue = storeState.getCustomerQueue();
	}

	public void execute() {
		storeState.updateTime(this);
		storeState.setCurrentEvent(this);
		storeState.setCurrentCustomer(this.customer);
		state.notifyObs();
		storeState.decreaseCustomersInStore();
		if (!queue.isEmpty()) {
			eventQueue.push(new PayEvent(storeState,state, eventQueue, time + storeState.getPayTime(), queue.pop()));
			storeState.increasePayedCustomers();
		} else {
			storeState.freeACheckout();
		}
	}

	public double getTime() {
		return time;
	}

	public Customer getCustomer() {
		return customer;

	}

	public String getName() {
		return "Betal";
	}
}
