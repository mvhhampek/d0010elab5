package lab5.general;

/**
 * General end event
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public abstract class GeneralEndEvent extends Event {
	/**
	 * Constructor
	 * 
	 * @param state      State of the simulation
	 * @param eventQueue Eventqueue
	 */
	public GeneralEndEvent(State state, EventQueue eventQueue) {
		super(state, eventQueue);
	}
}
