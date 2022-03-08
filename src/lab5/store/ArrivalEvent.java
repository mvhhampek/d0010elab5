package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;


public class ArrivalEvent extends Event  {
	private Customer customer;
	private StoreState storeState;
	private EventQueue eventQueue;
	private double time;
	
	// skapar ett nytt pick event och arrivalevent

	public ArrivalEvent(StoreState storeState, EventQueue eventQueue, double time){
		this.storeState = storeState;
		this.eventQueue = eventQueue;
		this.time = time;
	}

	public void execute() {
		if(storeState.isOpen() && storeState.space()){
			//PickEvent pick = new PickEvent();
			//ArrivalEvent arrive = new ArrivalEvent(storeState, eventQueue);
			eventQueue.push(new PickEvent());
			eventQueue.push(new ArrivalEvent(storeState, eventQueue, time));
		}
		if(storeState.isOpen() && !storeState.space()){
			//ArrivalEvent arrive = new ArrivalEvent(storeState, eventQueue);
			storeState.missedCustomer();
			eventQueue.push(new ArrivalEvent(storeState, eventQueue, time));
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
