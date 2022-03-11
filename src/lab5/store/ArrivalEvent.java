package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;

public class ArrivalEvent extends Event {
	private Customer customer;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;

	// skapar ett nytt pick event och arrivalevent

	public ArrivalEvent(StoreState storeState, EventQueue eventQueue, double time) {
		this.storeState = storeState;
		this.eventQueue = eventQueue;
		this.time = time;
	}

	public void execute() {
		if (storeState.isOpen() && storeState.space()) {
			//time += storeState.getArrivalTime();
			eventQueue.push(new PickEvent(storeState, eventQueue, time + storeState.getPickTime(),
					storeState.customerFactory.createCustomer()));
			eventQueue.push(new ArrivalEvent(storeState, eventQueue, time + storeState.getArrivalTime()));
		}
		if (storeState.isOpen() && !storeState.space()) {
			storeState.missedCustomer();
			eventQueue.push(new ArrivalEvent(storeState, eventQueue, time + storeState.getArrivalTime()));
		}
		if (!storeState.isOpen()) {
			// inte missad kund
		}
	}

	public double getTime() {
		return time;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getName() {
		return "Ankomst";
	}
}
