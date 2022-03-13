package lab5.store;

import lab5.general.Event;
import lab5.general.State;
import lab5.store.time.*;

public class StoreState extends State {
    private double closingTime;
    private double lastTime;
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
    private double freeCheckoutTime;
    private Event currentEvent;
    private Customer currentCustomer;
    private int payedCustomers;

    public StoreState(int maxCheckouts, int maxCustomers, int lambda, double minPick, double maxPick, double minPay,
            double maxPay, long seed, double closingTime) {
        arriveTime = new ExponentialRandomStream(lambda, seed);
        pickTime = new UniformRandomStream(minPick, maxPick, seed);
        payTime = new UniformRandomStream(minPay, maxPay, seed);
        this.closingTime = closingTime; // denna får man bestämma själv, körexempel 1 har 10.0, 2 har 8.0
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
        freeCheckoutTime = 0;
        customersQueued = 0;
        customerFactory = new CustomerFactory();
        customerQueue = new CustomerQueue();
        payedCustomers = 0;
    }

    public void increasePayedCustomers() {
        payedCustomers++;
    }

    public int getPayedCustomers() {
        return payedCustomers;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
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

    public void open() {
        open = true;
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

    public void setCurrentCustomer(Customer c) {
        currentCustomer = c;
    }

    public void increaseCustomersInStore() {
        customersInStore++;
    }

    public void increaseFreeCheckoutTime(double value) {
        freeCheckoutTime += value;
    }

    public void increaseTotalQueueTime(double value) {
        totalQueueTime += value;
    }

    public double getFreeCheckoutTime() {
        return freeCheckoutTime;
    }

    public double getTotalQueueTime() {
        return totalQueueTime;
    }

    public void updateTime(Event nextEvent) {
        lastTime = currentEvent.getTime();
        currentTime = nextEvent.getTime();

        currentEvent = nextEvent;

        if (nextEvent.getName() != "Stopp" && !(nextEvent.getName() == "Ankomst" && !isOpen())) {
            increaseFreeCheckoutTime((currentTime - lastTime) * getFreeCheckouts());
            increaseTotalQueueTime((currentTime - lastTime) * customerQueue.size());
        }
    }
}