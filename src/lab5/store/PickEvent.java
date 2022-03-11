package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;

public class PickEvent extends Event {
	private Customer customer;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	private CustomerQueue customerQueue;

	public PickEvent(StoreState storeState, EventQueue eventQueue, double time, Customer customer) {
		this.eventQueue = eventQueue;
		this.time = time;
		this.customer = customer;
		this.storeState = storeState;
		customerQueue = storeState.getCustomerQueue();
	}

	public void execute() {
		if (storeState.getFreeCheckouts() > 0) {
			storeState.occupyACheckout();
			eventQueue.push(new PayEvent(storeState, eventQueue, time + storeState.getPayTime(), customer));			
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

	public String getName() {
		return "Plock";
	}
}
