package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;
import lab5.general.State;

/**
 * Specific pay event for the store
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class PayEvent extends Event {
	private Customer customer;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	private CustomerQueue queue;
	private State state;

	/**
	 * Constructor
	 * 
	 * @param storeState The state of the store
	 * @param state      The state of the simulation
	 * @param eventQueue The eventqueue
	 * @param time       This events execution time
	 * @param customer   This events customer
	 */
	public PayEvent(StoreState storeState, State state, EventQueue eventQueue, double time, Customer customer) {
		super(state, eventQueue, time);
		this.state = state;
		this.eventQueue = eventQueue;
		this.time = time;
		this.storeState = storeState;
		this.customer = customer;
		queue = storeState.getCustomerQueue();
	}

	/**
	 * Creates a new payevent for the customer in front of the queue, if the queue
	 * is empty it frees a checkout
	 */
	public void execute() {
		storeState.updateTime(this);
		storeState.setCurrentCustomer(customer);
		state.notifyObs();
		storeState.increasePayedCustomers();
		storeState.decreaseCustomersInStore();
		if (!queue.isEmpty()) {
			eventQueue.push(new PayEvent(storeState, state, eventQueue, time + storeState.getPayTime(), queue.pop()));
			
		} else {
			storeState.freeACheckout();
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
		return "Betal";
	}
}
