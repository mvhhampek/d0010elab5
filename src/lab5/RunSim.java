package lab5;

import lab5.general.EventQueue;
import lab5.general.Simulator;
import lab5.general.State;
import lab5.store.EndEvent;
import lab5.store.PrintView;
import lab5.store.StartEvent;
import lab5.store.StoreState;

/**
 * Main program from where the simulation is run
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class RunSim {
    /**
     * Main
     * 
     * @param args Runtime arguments
     */
    public static void main(String[] args) {
//        int maxCheckouts = 2;
//        int maxCustomers = 5;
//        int lambda = 1;
//        double pickMin = 0.5;
//        double pickMax = 1.0;
//        double payMin = 2.0;
//        double payMax = 3.0;
//        int seed = 1234;
//        double closingTime = 10.0;

         int maxCheckouts = 2;
         int maxCustomers = 7;
         double lambda = 3;
         double pickMin = 0.6;
         double pickMax = 0.9;
         double payMin = 0.35;
         double payMax = 0.6;
         int seed = 13;
         double closingTime = 8.0;

        StoreState storeState = new StoreState(maxCheckouts, maxCustomers, lambda, pickMin, pickMax, payMin, payMax,
                seed, closingTime);
        State state = new State();
        EventQueue eventQueue = new EventQueue();
        eventQueue.push(new StartEvent(storeState, state, eventQueue));
        eventQueue.push(new EndEvent(storeState, state, eventQueue));
        Simulator simulator = new Simulator(state, eventQueue);
        new PrintView(state, storeState);
        simulator.run();
    }
}
