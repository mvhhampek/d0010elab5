package lab5.store;

import lab5.store.time.*;

public class StoreState {
    private double closingTime;
    private double currentTime;
    private double minPay;
    private double maxPay;
    private double minPick;
    private double maxPick;
     
    private ExponentialRandomStream arriveTime;
    private UniformRandomStream pickTime;
    private UniformRandomStream payTime;
    
    public CustomerFactory customerFactory;
    public CustomerQueue customerQueue;
    private boolean simulationRunning;
    private boolean open;
    private int maxCustomers;
    private int customersInStore;
    private int occupiedCheckouts;
    private int missedCostumers;
    private int maxCheckouts;
    private int lambda;// customers per timme

    


    public StoreState(int maxCheckouts, int maxCustomers, int lambda, double pickMin, double pickMax, double payMin,
            double payMax, long seed) {
        arriveTime = new ExponentialRandomStream(lambda, seed);
        pickTime = new UniformRandomStream(minPick, maxPick, seed);
        payTime = new UniformRandomStream(minPay, maxPay, seed);
        closingTime = 10.0; //denna får man bestämma själv, körexempel 1 har 10.0, 2 har 8.0
        setMissedCostumers(0);
        this.maxCheckouts = maxCheckouts;
        this.maxCustomers = maxCustomers;

    }

    public int getMissedCostumers() {
        return missedCostumers;
    }

    private void setMissedCostumers(int missedCostumers) {
        this.missedCostumers = missedCostumers;
    }

    public int getLambda() {
        return lambda;
    }

    

    /**
     * checks if the store is open/closed.
     * 
     * @return returns a bolian depending if the store is open or not
     */
    public boolean isOpen() {
        return open;
    }
    public void decreaseCustomersInStore(){
        customersInStore--;
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
     * Increases missedCustomers by 1
     */
    public void missedCustomer() {
        setMissedCostumers(getMissedCostumers() + 1);
    }

    public double getCloseTime(){
        return closingTime;
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
        return maxCheckouts - occupiedCheckouts;
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

    public int getMaxCheckouts(){
        return maxCheckouts;
    }

    public int getMaxCustomers(){
        return maxCustomers;
    }
    public double getMaxPickTime(){
        return maxPick;
    }
    public double getMinPickTime(){
        return minPick;
    }
    public double getMaxPayTime(){
        return maxPay;
    }
    public double getMinPayTime(){
        return minPay;
    }

}
