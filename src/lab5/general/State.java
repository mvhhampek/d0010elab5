package lab5.general;

import lab5.store.StoreState;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class State extends Observable {
    private StoreState storeState;

    public State(StoreState storeState, View view){
        this.storeState = storeState;
    }
    public StoreState getStore() {
        return storeState;
    }

    public void update(){
        

        setChanged();
        notifyObservers();
    }


}
