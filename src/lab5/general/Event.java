package lab5.general;
import lab5.store.Customer;
public abstract class Event {
	
	EventQueue eventQueue;
	State state;

	public abstract void execute();
	public abstract double getTime();
	public abstract Customer getCustomer();

}
