package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;
import lab5.general.State;

public class PickEvent extends Event {
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	private CustomerQueue customerQueue;

	public PickEvent(StoreState storeState,State state, EventQueue eventQueue, double time, Customer customer) {
		this.state = state;
		this.eventQueue = eventQueue;
		this.time = time;
		this.customer = customer;
		this.storeState = storeState;
		customerQueue = storeState.getCustomerQueue();
	}

	public void execute() {
		storeState.updateTime(this);
		storeState.setCurrentEvent(this);
		storeState.setCurrentCustomer(this.customer);
		state.notifyObs();
		if (storeState.getFreeCheckouts() > 0) {
			storeState.occupyACheckout();
			eventQueue.push(new PayEvent(storeState,state, eventQueue, time + storeState.getPayTime(), customer));	
			storeState.increasePayedCustomers();		
		} else {
			storeState.customerQueued();
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
