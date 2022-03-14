package lab5.general;

/**
 * General Event
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public abstract class Event {
	/**
	 * Constructor
	 * 
	 * @param state      the state of the simulation
	 * @param eventQueue the eventqueue
	 * @param time       the events execution time
	 */
	public Event(State state, EventQueue eventQueue, double time) {
	}

	/**
	 * Constructor
	 * 
	 * @param state      the state of the simulation
	 * @param eventQueue the eventqueue
	 */
	public Event(State state, EventQueue eventQueue) {
	}

	/**
	 * The events effect on the state
	 */
	public abstract void execute();

	/**
	 * Returns this events time
	 * 
	 * @return time
	 */
	public abstract double getTime();

	/**
	 * Returns this events name
	 * 
	 * @return name
	 */
	public abstract String getName();
}
