package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;
import lab5.general.State;

public class ArrivalEvent extends Event {
	private State state;
	private Customer customer;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;

	public ArrivalEvent(StoreState storeState, State state, EventQueue eventQueue, double time) {
		super(state, eventQueue, time);
		this.state = state;
		this.storeState = storeState;
		this.eventQueue = eventQueue;
		this.time = time;
		customer = storeState.customerFactory.createCustomer();
	}

	public void execute() {
		storeState.updateTime(this);
		storeState.setCurrentCustomer(customer);
		state.notifyObs();
		if (storeState.isOpen()) {
			if (storeState.space()) {
				storeState.increaseCustomersInStore();
				eventQueue.push(new PickEvent(storeState, state, eventQueue, time + storeState.getPickTime(), customer));
				eventQueue.push(new ArrivalEvent(storeState, state, eventQueue, time + storeState.getArrivalTime()));
			} else {
				storeState.missedCustomer();
				eventQueue.push(new ArrivalEvent(storeState, state, eventQueue, time + storeState.getArrivalTime()));
			}
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
