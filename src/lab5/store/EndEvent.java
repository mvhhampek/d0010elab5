package lab5.store;

import lab5.general.Event;
import lab5.general.State;

public class EndEvent extends Event  {
	private State state;
	public EndEvent(State state){
		this.state = state;
	}

	public void execute() {
		state.endSimulation();
	}

	public double getTime() {

		return 999;
	}

	public Customer getCustomer(){
		return null;
	}

	public String getName() {
		return "Stopp";
	}
}
