package lab5.store;

import lab5.general.State;

public class StoreState extends State  {   
    private CustomerQueue customerQueue;
    private boolean open;
    private int maxCustomers;
    private int customersInStore;
    private int checkouts;
    private double minPayTime;
    private double maxPayTime;
    private double minPickTime;
    private double maxPickTime;
    
    public boolean isOpen(){
        return open;
    }

    public void close(){
        open=false;
    }
    public boolean space()


    private int lambda; // customers per timme 
}
