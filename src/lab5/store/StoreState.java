package lab5.store;

import lab5.general.State;

import java.lang.reflect.Array;

public class StoreState extends State  {   
    private CustomerQueue customerQueue;
    private Array customers;
    private boolean open;
    private int maxCustomers;
    private int customersInStore;
    private int checkouts;
    
    private double openTime;
    private double currentTime;

    private double minPayTime;
    private double maxPayTime;
    private double minPickTime;
    private double maxPickTime;
    private int lambda; // customers per timme 
    
    /**
     * checks if the store is open/closed.
     * @return returns a bolian depending if the store is open or not
     */
    public boolean isOpen(){
        return open;
    }
    
    /**
     * Closes the store.
     */
    public void close(){
        open=false;
    }
    
    public boolean space(){
    	// kollar om det finn utrymme i affären
    }
    
    /**
     * send the current time
     * @return the current time
     */
    public double getTime() {
    	return currentTime;
    }
    
    /**
     * Updates the time.
     * @param timeElapsed how much time has passed
     */
    public void updateTime(double timeElapsed) {
    	currentTime+=timeElapsed;
    }


    /**
     * Checks if someone is in Line waiting to pay.
     * @return true if a customer is in line
     * @return false if there is no line
     */
    public boolean CustomerInLine(){
        if(customerQueue.size() == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean FreeCheckout(){
        //kolla om det finns en ledig snabbkasssa
    }


    
}

