package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;

public class StartEvent extends Event {
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	
	// skapar ett nytt arrivalevent

	public StartEvent(StoreState storeState, EventQueue eventQueue){
		this.eventQueue = eventQueue;
		this.time = 0;
		this.storeState = storeState;
	}


	public void execute() {
		eventQueue.push(new ArrivalEvent(storeState, eventQueue, time+storeState.getArrivalTime()));
	}

	public double getTime() {
		return time;
	}


	public String getName() {
		return "Start";
	}
}