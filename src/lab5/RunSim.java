package lab5;
import lab5.general.EventQueue;
import lab5.general.PrintView;
import lab5.general.Simulator;
import lab5.general.State;
import lab5.store.EndEvent;
import lab5.store.StartEvent;
import lab5.store.StoreState;

public class RunSim {
    public static void main(String[] args){
        
        int maxCheckouts = 2;
        int maxCustomers = 5;
        int lambda = 1;
        double pickMin = 0.5;
        double pickMax = 1.0;
        double payMin = 2.0;
        double payMax = 3.0;
        long seed = 1234;
        
        StoreState storeState = new StoreState(maxCheckouts, maxCustomers, lambda, pickMin, pickMax, payMin, payMax, seed);
        State state = new State();
        EventQueue eventQueue = new EventQueue();
        eventQueue.push(new StartEvent(storeState, eventQueue));
        eventQueue.push(new EndEvent(storeState)); //tiden 999
        Simulator simulator = new Simulator(state, eventQueue);
        PrintView printView = new PrintView(storeState);
        simulator.run();
    }
}
