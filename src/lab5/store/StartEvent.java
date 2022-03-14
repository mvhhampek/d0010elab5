package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;
import lab5.general.State;

/**
 * Specific start event for the store
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class StartEvent extends Event {
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	private State state;

	/**
	 * Constructor
	 * 
	 * @param storeState State of the store
	 * @param state      State of the simulation
	 * @param eventQueue Eventqueue
	 */
	public StartEvent(StoreState storeState, State state, EventQueue eventQueue) {
		super(state, eventQueue);
		this.eventQueue = eventQueue;
		this.time = 0;
		this.storeState = storeState;
		this.state = state;
	}

	/**
	 * Opens the store, adds a future arrivalevent and a future closing event
	 */
	public void execute() {
		storeState.open();
		storeState.setCurrentEvent(this);
		storeState.updateTime(this);
		state.notifyObs();
		eventQueue.push(new CloseEvent(storeState, state, eventQueue));
		eventQueue.push(new ArrivalEvent(storeState, state, eventQueue, time + storeState.getArrivalTime()));
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
	 * Returns this events name
	 * 
	 * @return name
	 */
	public String getName() {
		return "Start";
	}
}