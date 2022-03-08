package lab5.store;

import lab5.general.Simulator;
import lab5.general.State;
import java.lang.reflect.Array;

public class StoreState extends State {
    public StoreState(Simulator s) {
        super(s);
        //TODO Auto-generated constructor stub
    }

    private boolean simulationRunning;
    public CustomerFactory customerFactory;
    public CustomerQueue customerQueue;
    private Array customers;
    private boolean open;
    private int maxCustomers;
    private int customersInStore;
    private int checkouts;
    private int occupiedCheckouts;
    private int missedCostumers;

    private double openTime;
    private double currentTime;

    private double minPayTime;
    private double maxPayTime;
    private double minPickTime;
    private double maxPickTime;
    private int lambda; // customers per timme


    /**
     * Checks if the simulation is running.
     *
     * @return returns a boolean, true if simulation is running and false if the simulation has ended.
     */
    public boolean SimulationIsRunning(){
        return simulationRunning;
    }

    /**
     * Ends the Simulation.
     */
    public void EndSimulation(){
    simulationRunning = false;
    }

    /**
     * Checks if the store is open/closed.
     * 
     * @return returns a boolean, true if the store is opened and false if the store is closed.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Closes the store.
     */
    public void close() {
        open = false;
    }

    /**
     * 
     * @return true if there is place for more customers in the store
     */
    public boolean space() {
        return customersInStore < maxCustomers;
    }

    /**
     * Increased missedCustomers by 1
     */
    public void missedCustomer() {
        missedCostumers++;
    }

    /**
     * send the current time
     * 
     * @return the current time
     */
    public double getTime() {
        return currentTime;
    }

    /**
     * Updates the time.
     * 
     * @param timeElapsed how much time has passed
     */
    public void updateTime(double timeElapsed) {
        currentTime += timeElapsed;
    }

    public CustomerQueue getCustomerQueue() {
        return customerQueue;
    }

    /**
     * Returns amount of unoccupied checkouts
     * 
     * @return amount of unoccupied checkouts
     */
    public int freeCheckouts() {
        return checkouts - occupiedCheckouts;
    }

    /**
     * +1 to occupy a checkout, -1 to free one
     * @param value value to change occupiedCheckouts by
     */
    public void changeOccupiedCheckouts(int value) {
        occupiedCheckouts += value;
    }
}
