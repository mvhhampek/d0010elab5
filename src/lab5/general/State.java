package lab5.general;

import java.util.Observable;

public class State extends Observable {
    private Simulator simulator;
    public State(Simulator s){
        simulator=s;
    }
}
