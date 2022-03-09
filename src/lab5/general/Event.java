package lab5.general;
import lab5.store.Customer;
public abstract class Event {
	public abstract void execute();
	public abstract double getTime();
	public abstract Customer getCustomer();
}
