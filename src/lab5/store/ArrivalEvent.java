package lab5.store;

import lab5.general.Event;

public class ArrivalEvent extends Event  {
	private Customer customer;

	void execute() {
		if(StoreState.isOpen() && StoreState.Space()){
			//ny kund
		}
		if(StoreState.isOpen() && !StoreState.Space()){
			//missar kund
		}
		if(!StoreState.isOpen){
			//inte missad kund
		}

	}

	double getTime() {

		return 0;
	}

	Customer getCustomer(){
		return customer;
	}
	
}
