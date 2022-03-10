package lab5.general;

import lab5.store.CloseEvent;
import lab5.store.EndEvent;
import lab5.store.StartEvent;

public class Simulator {
    private State state;
    private EventQueue eventQueue;

    public Simulator(State state) {
        this.state = state;
        eventQueue = new EventQueue();
    }

    public void run() {
        eventQueue.push(new StartEvent(state, eventQueue));
        eventQueue.push(new CloseEvent(state));
        eventQueue.push(new EndEvent(state)); //tiden 999
        while (state.getSimRunning()) {
            eventQueue.pop().execute();
        }
    }
}
