package lab5.general;
/**
 * General start event
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public abstract class GeneralStartEvent extends Event {
	/**
	 * Constructor
	 * @param state			State of the simulation
	 * @param eventQueue	Eventqueue
	 */
    public GeneralStartEvent(State state, EventQueue eventQueue){
        super(state, eventQueue);
    }    
}
