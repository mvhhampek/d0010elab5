package lab5.store;

import lab5.general.Event;

public class EndEvent extends Event  {

	void execute() {

	}

	double getTime() {

		return 0;
	}

	Customer getCustomer(){
		return null;
	}
}
