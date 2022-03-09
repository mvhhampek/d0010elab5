package lab5;

import lab5.general.EventQueue;
import lab5.general.Simulator;
import lab5.general.State;
import lab5.store.CloseEvent;
import lab5.store.EndEvent;
import lab5.store.StartEvent;
import lab5.store.StoreState;

public class Optimize {
    Simulator simulator;
    StoreState storeState;
    State state;

    public String metod1(int kassor){
        new StoreState(kassor,15, 4, 0.25, 0.50, 0.25, 0.50, 133742069);
        new Simulator(state);
        simulator.run();
        String result1 = ("Av " + storeState.getMaxCustomers() + " kunder handlade " + (storeState.getMaxCustomers()-storeState.getMissedCostumers()) + " medan " + storeState.getMissedCostumers() + " missades.");
        String result2 = ("Total tid " + storeState.getMaxCheckouts() + " varit lediga: " + storeState.getLedigTid +" te." + "\n" + "Genomsnittlig ledig  kassatid: " + (storeState.getLedigTid()/storeState.getMaxCheckouts()) + " (dvs " + ((storeState.getköti()/storeState.getTime())*100) + "% av tiden från öppning tills sista kunden betalat.");
        String result3 = ("Totalt  tid " + storeState.getCustomersQueued() + " kunder tvingades  köa: " + storeState.getQueueTime() + " te." + "\n" + "Genomsnittlig kötid: " + (storeState.getQueueTime()/storeState.getCustomersQueued()) + " te.");

        return (result1 + result2 + result3);
        }
    public void metod2(){

    }
    public void metod3(){
        
    }
}
