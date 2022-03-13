package lab5.store;

import lab5.general.Event;
import lab5.general.EventQueue;
import lab5.general.State;

public class StartEvent extends Event {
	private EventQueue eventQueue;
	private double time;
	private StoreState storeState;
	private State state;

	public StartEvent(StoreState storeState,State state, EventQueue eventQueue) {
		super(state, eventQueue);
		this.eventQueue = eventQueue;
		this.time = 0;
		this.storeState = storeState;
		this.state = state;
	}

	public void execute() {
		storeState.open();
		storeState.setCurrentEvent(this);
		storeState.updateTime(this);
		state.notifyObs();
		eventQueue.push(new CloseEvent(storeState,state, eventQueue));
		eventQueue.push(new ArrivalEvent(storeState,state, eventQueue, time + storeState.getArrivalTime()));
	}

	public double getTime() {
		return time;
	}

	public String getName() {
		return "Start";
	}
}