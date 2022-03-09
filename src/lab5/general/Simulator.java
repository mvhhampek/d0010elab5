package lab5.general;

import lab5.store.CloseEvent;
import lab5.store.EndEvent;
import lab5.store.StartEvent;
import lab5.store.StoreState;

public class Simulator {
    private StoreState storeState;
    private State state;
    private EventQueue eventQueue;

    public Simulator(State state) {
        this.state = state;
        eventQueue = new EventQueue();
    }

    public void run() {
        eventQueue.push(new StartEvent(state, eventQueue));
        eventQueue.push(new CloseEvent());
        eventQueue.push(new EndEvent()); //tiden 999
        while (storeState.getSimRunning()) {
            eventQueue.pop().execute();
        }
    }
}
