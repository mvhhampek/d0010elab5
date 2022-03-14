package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;
import lab5.general.State;

/**
 * Specific pick event for the store
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class PickEvent extends Event {
	private Customer customer;
	private State state;
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	private CustomerQueue customerQueue;

	/**
	 * Constructor
	 * 
	 * @param storeState The state of the store
	 * @param state      The state of the simulation
	 * @param eventQueue The eventqueue
	 * @param time       This events execution time
	 * @param customer   This events customer
	 */
	public PickEvent(StoreState storeState, State state, EventQueue eventQueue, double time, Customer customer) {
		super(state, eventQueue, time);
		this.state = state;
		this.eventQueue = eventQueue;
		this.time = time;
		this.customer = customer;
		this.storeState = storeState;
		customerQueue = storeState.getCustomerQueue();
	}

	/**
	 * Creates a payevent for this events customer if there are free checkouts,
	 * otherwise places this events customer in the checkout queue
	 */
	public void execute() {
		storeState.updateTime(this);
		storeState.setCurrentCustomer(customer);
		state.notifyObs();
		if (storeState.getFreeCheckouts() > 0) {
			storeState.occupyACheckout();
			eventQueue.push(new PayEvent(storeState, state, eventQueue, time + storeState.getPayTime(), customer));
		} else {
			storeState.customerQueued();
			customerQueue.push(customer);
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
		return "Plock";
	}
}
