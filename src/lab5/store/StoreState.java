package lab5.store;

import lab5.general.State;
import lab5.store.time.ExponentialRandomStream;
import lab5.store.time.UniformRandomStream;

import java.lang.reflect.Array;

public class StoreState {

    private double minPay;
    private double maxPay;
    private double minPick;
    private double maxPick;
    private int lambda; // customers per timme
    private ExponentialRandomStream arriveTime;
    private UniformRandomStream pickTime;
    private UniformRandomStream payTime;

    public StoreState(int maxCheckouts, int maxCustomers, int lambda, double pickMin, double pickMax, double payMin,
            double payMax, long seed) {
        arriveTime = new ExponentialRandomStream(lambda, seed);
        pickTime = new UniformRandomStream(minPick, maxPick, seed);
        payTime = new UniformRandomStream(minPay, maxPay, seed);
        closingTime = 10.0;
        missedCostumers = 0;

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

    private double closingTime;
    private double currentTime;

    /**
     * checks if the store is open/closed.
     * 
     * @return returns a bolian depending if the store is open or not
     */
    public boolean isOpen() {
        return open;
    }

    public double getPickTime(){
        return pickTime.next();
    }
    public double getPayTime(){
        return payTime.next();
    }
    public double getArrivalTime(){
        return arriveTime.next();
    }

    /**
     * Closes the store.
     */
    public void close() {
        open = false;
    }

    /**
     * Returns state of simulation
     * @return true if the simulation is running, false otherwise
     */
    public boolean getSimRunning(){
        return simulationRunning;
    }
    /**
     * Ends the simulation
     */
    public void endSimulation(){
        simulationRunning = false;
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
    public int getFreeCheckouts() {
        return checkouts - occupiedCheckouts;
    }

    /**
     * Occupies a checkout
     */
    public void occupyACheckout() {
        occupiedCheckouts++;
    }

    /**
     * Frees a checkout
     */
    public void freeACheckout() {
        occupiedCheckouts--;
    }

}
