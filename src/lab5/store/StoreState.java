package lab5.store;

import lab5.general.Event;
import lab5.general.State;

import lab5.store.time.*;

public class StoreState extends State {
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

    private boolean open;
    private int maxCustomers;
    private int customersInStore;
    private int occupiedCheckouts;
    private int missedCostumers;
    private int maxCheckouts;
    private int lambda; // customers per timme
    private long seed;
    private int customersQueued;
    private double totalQueueTime;
    private double freeCashierTime;
    private boolean occupied;
    private double timeOfFreedome;
    private double timeOfOccupation;
    private int finishedCustomers;
    private Event currentEvent;

    public StoreState(int maxCheckouts, int maxCustomers, int lambda, double minPick, double maxPick, double minPay,
            double maxPay, long seed) {
        arriveTime = new ExponentialRandomStream(lambda, seed);
        pickTime = new UniformRandomStream(minPick, maxPick, seed);
        payTime = new UniformRandomStream(minPay, maxPay, seed);
        closingTime = 10.0; // denna får man bestämma själv, körexempel 1 har 10.0, 2 har 8.0
        this.maxCheckouts = maxCheckouts;
        this.maxCustomers = maxCustomers;
        this.seed = seed;
        this.maxPay = maxPay;
        this.minPay = minPay;
        this.maxPick = maxPick;
        this.minPick = minPick;
        this.lambda = lambda;
        missedCostumers = 0;
        totalQueueTime = 0;
        freeCashierTime = 0;
        occupied = false;
        timeOfFreedome = 0;
        timeOfOccupation = 0;
        finishedCustomers = 0;
        customersQueued = 0;
    }

    public int getMissedCostumers() {
        return missedCostumers;
    }

    public int getLambda() {
        return lambda;
    }

    public void setCurrentEvent(Event e) {
        currentEvent = e;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentTime(double value) {
        currentTime = value;
    }

    /**
     * send the current time
     * 
     * @return the current time
     */
    public double getCurrentTime() {
        return currentTime;
    }

    /**
     * checks if the store is open/closed.
     * 
     * @return returns a bolian depending if the store is open or not
     */
    public boolean isOpen() {
        return open;
    }

    public void decreaseCustomersInStore() {
        customersInStore--;
    }

    public double getPickTime() {
        return pickTime.next();
    }

    public double getPayTime() {
        return payTime.next();
    }

    public double getArrivalTime() {
        return arriveTime.next();
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
     * Increases missedCustomers by 1
     */
    public void missedCustomer() {
        missedCostumers++;
    }

    public double getCloseTime() {
        return closingTime;
    }

    public int getCustomersInStore() {
        return customersInStore;
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
        if (getFreeCheckouts() == 0 && !occupied) {
            occupied = true;
            getOccupationTime();
        }
    }

    /**
     * Frees a checkout
     */
    public void freeACheckout() {
        occupiedCheckouts--;
        if (getFreeCheckouts() > 0 && occupied) {
            occupied = false;
            getFreeTime();
        }
    }

    public int getMaxCheckouts() {
        return maxCheckouts;
    }

    public int getMaxCustomers() {
        return maxCustomers;
    }

    public double getMaxPickTime() {
        return maxPick;
    }

    public double getMinPickTime() {
        return minPick;
    }

    public double getMaxPayTime() {
        return maxPay;
    }

    public double getMinPayTime() {
        return minPay;
    }

    public long getSeed() {
        return seed;
    }

    public void customerQueued() {
        customersQueued++;
    }

    public int getCustomersQueued() {
        return customersQueued;
    }

    public void increaseQueuedTime(double value) {
        totalQueueTime += value;
    }

    public void getOccupationTime() {
        timeOfOccupation = getTime();
    }

    public void getFreeTime() {
        timeOfFreedome = getTime();
    }

    public void increaseFreeCashierTime(double value) {
        freeCashierTime += timeOfOccupation - timeOfFreedome;
    }
}