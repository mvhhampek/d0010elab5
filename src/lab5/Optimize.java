package lab5;

import lab5.general.EventQueue;
import lab5.general.Simulator;
import lab5.general.State;
import lab5.general.View;
import lab5.store.CloseEvent;
import lab5.store.EndEvent;
import lab5.store.StartEvent;
import lab5.store.StoreState;

public class Optimize {

    public StoreState metod1(int kassor){
        StoreState storeState = new StoreState(kassor,15, 4, 0.5, 1.00, 0.5, 1.0, 1234,8.0);
        //View view = new View();
        //State state = new State(storeState, view);
        //Simulator simulator = new Simulator(state);
        //simulator.run();

        return storeState;
        }
    public void metod2(){

    }
    public void metod3(){
        
    }
}
