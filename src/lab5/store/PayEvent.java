package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;

public class PayEvent extends Event {
	private Customer customer;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	private CustomerQueue queue;

	public PayEvent(StoreState storeState, EventQueue eventQueue, double time, Customer customer) {
		this.customer = customer;
		this.eventQueue = eventQueue;
		this.time = time;
		this.storeState = storeState;
	}

	public void execute() {
		storeState.decreaseCustomersInStore();
		if (!queue.isEmpty()) {
			eventQueue.push(new PayEvent(storeState, eventQueue, time + storeState.getPayTime(), queue.pop()));
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
		return "Betalning";
	}
}
