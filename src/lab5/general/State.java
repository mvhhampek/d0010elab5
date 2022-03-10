package lab5.general;

import lab5.store.StoreState;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class State extends Observable {
    private StoreState storeState;
    private View view;
    private boolean simulationRunning;
    public State(StoreState storeState, View view){
        this.storeState = storeState;
        this.view = view;
    }
    public StoreState getStore() {
        return storeState;
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


}
