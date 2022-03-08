package lab5.store;

import lab5.general.State;
import lab5.general.Event;
import lab5.general.EventQueue;


public class ArrivalEvent extends Event  {
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	
	// skapar ett nytt pick event och arrivalevent

	public ArrivalEvent(State state, EventQueue eventQueue, double time){
		this.state = state;
		this.eventQueue = eventQueue;
		this.time = time;
		storeState = state.getStore();
	}

	public void execute() {
		if(storeState.isOpen() && storeState.space()){
			eventQueue.push(new PickEvent(state, eventQueue, time));
			eventQueue.push(new ArrivalEvent(state, eventQueue, time));
		}
		if(storeState.isOpen() && !storeState.space()){
			storeState.missedCustomer();
			eventQueue.push(new ArrivalEvent(state, eventQueue, time));
		}
		if(!storeState.isOpen()){
			//inte missad kund
		}
	}

	public double getTime() {
		return time;
	}

	public Customer getCustomer(){
		return customer;
	}
}
