package lab5.general;

public abstract class GeneralStartEvent extends Event {
    public GeneralStartEvent(State state, EventQueue eventQueue){
        super(state, eventQueue);
    }    
}
