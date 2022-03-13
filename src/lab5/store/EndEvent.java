package lab5.store;

import lab5.general.State;
import lab5.general.EventQueue;
import lab5.general.GeneralEndEvent;

/**
 * Specific end event for the store
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class EndEvent extends GeneralEndEvent {
	private StoreState storeState;
	private State state;

	/**
	 * Constructor
	 * @param storeState	The state of the store
	 * @param state			The state of the simulator
	 * @param eventQueue	Eventqueue
	 */
	public EndEvent(StoreState storeState, State state, EventQueue eventQueue) {
		super(state, eventQueue);
		this.state = state;
		this.storeState = storeState;
	}

	/**
	 * Ends the simulation
	 */
	public void execute() {
		storeState.setCurrentEvent(this);
		state.notifyObs();
		state.endSimulation();
	}
	
	/**
	 * Returns this events time
	 * @return time
	 */
	public double getTime() {
		return 999;
	}

	/**
	 * Returns this events name
	 * @return name
	 */
	public String getName() {
		return "Stopp";
	}

}
