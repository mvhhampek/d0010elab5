package lab5.general;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class State extends Observable {

    private boolean simulationRunning;
    public State(){
        simulationRunning = true;
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

    public void notifyObs(){
        setChanged();
        notifyObservers();
    }
}
