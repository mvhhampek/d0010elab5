package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;
import lab5.general.State;

/**
 * Specific arrival event for the store
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class ArrivalEvent extends Event {
	private State state;
	private Customer customer;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;

	/**
	 * Constructor
	 * 
	 * @param storeState State of the store
	 * @param state      State of the simulation
	 * @param eventQueue Eventqueue
	 * @param time       This events execution time
	 */
	public ArrivalEvent(StoreState storeState, State state, EventQueue eventQueue, double time) {
		super(state, eventQueue, time);
		this.state = state;
		this.storeState = storeState;
		this.eventQueue = eventQueue;
		this.time = time;
		customer = storeState.getFactory().createCustomer();
	}

	/**
	 * Creates new pickevent if the store is open and there is room for more
	 * customers,
	 * otherwise notes that a customer has been missed and creates a future
	 * arrivalevent
	 */
	public void execute() {
		storeState.updateTime(this);
		storeState.setCurrentCustomer(customer);
		state.notifyObs();
		if (storeState.isOpen()) {
			if (storeState.space()) {
				storeState.increaseCustomersInStore();
				eventQueue
						.push(new PickEvent(storeState, state, eventQueue, time + storeState.getPickTime(), customer));
			} else {
				storeState.missedCustomer();
			}
			eventQueue.push(new ArrivalEvent(storeState, state, eventQueue, time + storeState.getArrivalTime()));
		}
	}

	/**
	 * Returns this events time
	 * 
	 * @return time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Returns this events customer
	 * 
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Returns this events name
	 * 
	 * @return name
	 */
	public String getName() {
		return "Ankomst";
	}
}
