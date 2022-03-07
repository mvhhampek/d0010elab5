package lab5.store;

import lab5.general.Event;

public class PayEvent extends Event  {
	private Customer customer;

	void execute() {

		if(StoreState.CustomerInLine || !StoreState.FreeCheckout){
			CustomerQueue(customer);
			//Om det redan finnns en i kön eller ingen ledig kassa ställer sig kunden i kön
		}
		else{
			PayEvent pay = new PayEvent();
		}


	}

	double getTime() {

		return 0;
	}

	Customer getCustomer(){
		return customer;
	}
}
