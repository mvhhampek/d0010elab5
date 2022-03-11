package lab5.general;

public class Simulator {
    private State state;
    private EventQueue eventQueue;

    public Simulator(State state, EventQueue eventQueue) {
        this.state = state;
        this.eventQueue = eventQueue;
    }

    public void run() {
        while (state.getSimRunning()) {
            Event currentEvent = eventQueue.pop();
            
            //System.out.println(eventQueue.size()+ currentEvent.getClass().getSimpleName());
            currentEvent.execute();
        }
    }
}
