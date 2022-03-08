package lab5.store;

import lab5.general.State;
import lab5.general.Event;
import lab5.general.EventQueue;


public class PayEvent extends Event  {
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;

	public PayEvent(State state, EventQueue eventQueue, double time){
		this.state = state;
		this.eventQueue = eventQueue;
		this.time = time;
		storeState = state.getStore();
	}

	public void execute() {

	}

	public double getTime() {

		return 0;
	}

	public Customer getCustomer(){
		return customer;
	}
}
