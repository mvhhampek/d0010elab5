package lab5.store;

import lab5.general.Event;

public class PayEvent extends Event  {
	private Customer customer;

	void execute() {

	}

	double getTime() {

		return 0;
	}

	Customer getCustomer(){
		return customer;
	}
}
