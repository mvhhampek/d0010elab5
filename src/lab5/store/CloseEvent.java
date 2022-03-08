package lab5.store;

import lab5.general.Event;

public class CloseEvent extends Event  {
	private StoreState storeState;
	private StoreState time;

	public void execute() {
		storeState.close();
	}

	public double getTime() {
		return time.getTime();
	}

	public Customer getCustomer(){
		return null;
	}

}
