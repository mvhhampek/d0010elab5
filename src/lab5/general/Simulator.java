package lab5.general;

/**
 * General simulator
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class Simulator {
    private State state;
    private EventQueue eventQueue;

    /**
     * Constructor
     * 
     * @param state      The state of the simulation
     * @param eventQueue The eventqueue
     */
    public Simulator(State state, EventQueue eventQueue) {
        this.state = state;
        this.eventQueue = eventQueue;
    }

    /**
     * Runs the simulation
     */
    public void run() {
        while (state.getSimRunning()) {
            eventQueue.pop().execute();
        }
    }
}
