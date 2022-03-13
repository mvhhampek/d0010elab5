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

	public PayEvent(StoreState storeState, State state, EventQueue eventQueue, double time, Customer customer) {
		super(state, eventQueue, time);
		this.state = state;
		this.eventQueue = eventQueue;
		this.time = time;
		this.storeState = storeState;
		this.customer = customer;
		queue = storeState.getCustomerQueue();
	}

	public void execute() {
		storeState.updateTime(this);
		storeState.setCurrentCustomer(customer);
		state.notifyObs();
		storeState.decreaseCustomersInStore();
		if (!queue.isEmpty()) {
			eventQueue.push(new PayEvent(storeState, state, eventQueue, time + storeState.getPayTime(), queue.pop()));
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
