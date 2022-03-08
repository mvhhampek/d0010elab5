package lab5.store;

import lab5.general.Event;

public class EndEvent extends Event  {

	public void execute() {

	}

	public double getTime() {

		return 0;
	}

	public Customer getCustomer(){
		return null;
	}
}
