package lab5.general;
import lab5.store.StoreState;;
import java.util.Observable;

public class State extends Observable {
    private Simulator simulator;
    private StoreState storeState; 
    public State(Simulator s){
        simulator=s;
    }


    public StoreState getStore(){
        return storeState;
    }

}
