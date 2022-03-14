package lab5.store;

import lab5.general.Event;
import lab5.general.State;
import lab5.store.time.*;
/**
 * State of the store
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class StoreState extends State {
    private double closingTime;
    private double lastTime;
    private double lastPayTime;
    private double currentTime;
    private double minPay;
    private double maxPay;
    private double minPick;
    private double maxPick;
    private ExponentialRandomStream arriveTime;
    private UniformRandomStream pickTime;
    private UniformRandomStream payTime;

    private CustomerFactory customerFactory;
    private CustomerQueue customerQueue;

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

    /**
     * Constructor
     * @param maxCheckouts Max number of checkouts in the store
     * @param maxCustomers Max number of customers in the store
     * @param lambda Customer arrival speed
     * @param minPick Minimum time for a pick event
     * @param maxPick Maximum time for a pick event
     * @param minPay Minimum time for a pay event
     * @param maxPay Maximum time for a pay event
     * @param seed Seed for the random number generators
     * @param closingTime Closing time of the store
     */
    public StoreState(int maxCheckouts, int maxCustomers, int lambda, double minPick, double maxPick, double minPay,
            double maxPay, long seed, double closingTime) {
        arriveTime = new ExponentialRandomStream(lambda, seed);
        pickTime = new UniformRandomStream(minPick, maxPick, seed);
        payTime = new UniformRandomStream(minPay, maxPay, seed);
        this.closingTime = closingTime;
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

    /**
     * return the customer factory
     * @return return the customer factory
     */
    public CustomerFactory getFactory() {
        return customerFactory;
    }

    /**
     * Increases payedcustomers by 1
     */
    public void increasePayedCustomers() {
        payedCustomers++;
    }

    /**
     * returns the amount of customers that have paid
     * @return return the amount of customers that have paid
     */
    public int getPayedCustomers() {
        return payedCustomers;
    }

    /**
     * returns the amount of current customers
     * @return return the current customers
     */
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    /**
     * returns the amount of missed customers
     * @return return the amount of missed customers
     */
    public int getMissedCostumers() {
        return missedCostumers;
    }

    /**
     * returns the value lambda, which is the amount of customers allowed per hour
     * @return return value lambda which is the amount of customers allowed per hour
     */
    public int getLambda() {
        return lambda;
    }

    /**
     * sets what event is currently in use
     * @param e e is what event is currently in use
     */
    public void setCurrentEvent(Event e) {
        currentEvent = e;
    }

    /**
     * returns the current event in play
     * @return return the current event
     */
    public Event getCurrentEvent() {
        return currentEvent;
    }

    /**
     * Checks if the store is open/closed.
     * 
     * @return returns a boolean depending if the store is open or not
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Decreases amount of customers in the store by 1
     */
    public void decreaseCustomersInStore() {
        customersInStore--;
    }

    /**
     * Gets time for a pick event
     * @return random time for pick event
     */
    public double getPickTime() {
        return pickTime.next();
    }

    /**
     * Gets time for a pay event
     * @return random time for pay event
     */
    public double getPayTime() {
        return payTime.next();
    }

    /**
     * Gets time for an arrival event
     * @return random time for arrival event
     */
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
     * Opens the store
     */
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

    /**
     * returns the closing time of the store
     * @return return the closing time of the store
     */
    public double getCloseTime() {
        return closingTime;
    }

    /**
     * returns the number of customers in the store
     * @return return the number of customers in the store
     */
    public int getCustomersInStore() {
        return customersInStore;
    }

    /**
     * returns the customer queue
     * @return return the customer queue
     */
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

    /**
     * returns the max number of checkouts
     * @return return the max amount of checkouts.
     */
    public int getMaxCheckouts() {
        return maxCheckouts;
    }

    /**
     * returns the maximum customer
     * @return return the maximum customers
     */
    public int getMaxCustomers() {
        return maxCustomers;
    }

    /**
     * returns the upper bound of the pick time intervall
     * @return upper bound pick time
     */
    public double getMaxPickTime() {
        return maxPick;
    }

    /**
     * returns the lower bound of the pick time intervall
     * @return lower bound pick time
     */
    public double getMinPickTime() {
        return minPick;
    }

    /**
     * returns the upper bound of the pay time intervall
     * @return upper bound pay time
     */
    public double getMaxPayTime() {
        return maxPay;
    }

    /**
     * returns the lower bound of the pay time intervall
     * @return lower bound pay time
     */
    public double getMinPayTime() {
        return minPay;
    }

    /**
     * returns the current seed
     * @return return the current seed
     */
    public long getSeed() {
        return seed;
    }

    /**
     * Increased total amount of queued customers by 1
     */
    public void customerQueued() {
        customersQueued++;
    }

    /**
     * returns the amount of customers queued
     * @return return the amount of customers queued
     */
    public int getCustomersQueued() {
        return customersQueued;
    }

    /**
     * sets the current amount of customers that the program will run on
     * @param c c amount of customers
     */
    public void setCurrentCustomer(Customer c) {
        currentCustomer = c;
    }

    /**
     * increases the amount of customers in the store
     */
    public void increaseCustomersInStore() {
        customersInStore++;
    }

    /**
     * returns the total amount of time any checkout has not been used
     * @return return the total amount of time any checkouts has not been used
     */
    public double getFreeCheckoutTime() {
        return freeCheckoutTime;
    }

    /**
     * returns total queue time of customers
     * @return return total queue time of customers
     */
    public double getTotalQueueTime() {
        return totalQueueTime;
    }

    /**
     * returns last time someone paid
     * @return return last time someone paid
     */
    public double getLastPayTime() {
        return lastPayTime;
    }

    /**
     * Updates the time customers have been in the checkout queue as well as
     * updates the time checkouts have been unoccupied
     * @param nextEvent the next event to be executed
     */
    public void updateTime(Event nextEvent) {
        lastTime = currentEvent.getTime();
        currentTime = nextEvent.getTime();
        currentEvent = nextEvent;

        if (nextEvent.getName() != "Stopp" && nextEvent.getName() != "Ankomst" || isOpen()) {
            totalQueueTime += (currentTime - lastTime) * customerQueue.size();
            freeCheckoutTime += (currentTime - lastTime) * getFreeCheckouts();
            lastPayTime = currentTime;
        }
    }
}