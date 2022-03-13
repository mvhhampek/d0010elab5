package lab5.general;

import java.util.Observable;

/**
 * General State
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
@SuppressWarnings("deprecation")
public class State extends Observable {

	private boolean simulationRunning;

	/**
	 * Constructor
	 */
	public State() {
		simulationRunning = true;
	}

	/**
	 * Ends the simulation
	 */
	public void endSimulation() {
		simulationRunning = false;
	}

	/**
	 * Returns state of simulation
	 * 
	 * @return true if the simulation is running, false otherwise
	 */
	public boolean getSimRunning() {
		return simulationRunning;
	}

	/**
	 * Notifies observers
	 */
	public void notifyObs() {
		setChanged();
		notifyObservers();
	}
}
