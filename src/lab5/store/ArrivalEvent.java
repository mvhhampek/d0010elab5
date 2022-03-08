package lab5.store;

import lab5.general.Event;


public class ArrivalEvent extends Event  {
	private Customer customer;


	// skapar ett nytt pick event och arrivalevent
	void execute() {

		if(StoreState.isOpen() && StoreState.Space()){
			PickEvent pick = new PickEvent()
			ArrivalEvent arrive = new ArrivalEvent()
		}
		if(StoreState.isOpen() && !StoreState.Space()){
			ArrivalEvent arrive = new ArrivalEvent()
			//Missadkund = Missadkund + 1;
			// new.missar.kund.event?? sug en hårig, fittnylle!
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
