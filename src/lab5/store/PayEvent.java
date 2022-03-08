package lab5.store;

import lab5.general.Event;

public class PayEvent extends Event  {
	private Customer customer;

	public void execute() {

	}

	public double getTime() {

		return 0;
	}

	public Customer getCustomer(){
		return customer;
	}
}
