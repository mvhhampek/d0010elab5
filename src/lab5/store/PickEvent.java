package lab5.store;

import lab5.general.Event;
import lab5.general.State;
import lab5.general.EventQueue;

public class PickEvent extends Event  {
    private Customer customer;
    private State state;
    private EventQueue eventQueue;
    private double time;
    private StoreState storeState;
    private CustomerQueue customerQueue;


    public PickEvent(State state, EventQueue eventQueue, double time){
        this.state = state;
        this.eventQueue = eventQueue;
        this.time = time;
        storeState = state.getStore();
        customerQueue = storeState.getCustomerQueue();
    }

    public void execute() {
        if(customerQueue.isEmpty()){
            //nytt pay event direkt
        }
        else{
            //ställ i kön och sedan nytt pay event?
        }

    }

    public double getTime() {

        return 0;
    }

    public Customer getCustomer(){
        return customer;
    }
}