package lab5.store;

import lab5.general.State;

import lab5.general.Event;
import lab5.general.EventQueue;
/**
 * Specific closing event for the store
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class CloseEvent extends Event {
	private StoreState storeState;
	private State state;
	/**
	 * Constructor
	 * @param storeState	State of the store
	 * @param state			State of the simulator
	 * @param eventQueue	Eventqueue
	 */
	public CloseEvent(StoreState storeState, State state, EventQueue eventQueue) {
		super(state, eventQueue);
		this.state = state;
		this.storeState = storeState;
	}

	/**
	 * Closes the store
	 */
	public void execute() {
		storeState.updateTime(this);
		state.notifyObs();
		storeState.close();
	}

	/**
	 * Returns closing time of the store
	 * @return closing time
	 */
	public double getTime() {
		return storeState.getCloseTime();
	}

	/**
	 * Returns this events name
	 * @return name
	 */
	public String getName() {
		return "Stäng";
	}
}
