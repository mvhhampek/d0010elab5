package lab5.store;

import lab5.general.Event;
import lab5.general.State;
import lab5.general.EventQueue;

public class StartEvent extends Event {
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	
	// skapar ett nytt pick event och arrivalevent

	public StartEvent(State state, EventQueue eventQueue){
		this.state = state;
		this.eventQueue = eventQueue;
		this.time = 0;
		storeState = state.getStore();
	}


	public void execute() {
		
	}

	public double getTime() {

		return time;
	}

	public Customer getCustomer(){
		return null;
	}
}