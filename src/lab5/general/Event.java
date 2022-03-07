package lab5.general;

public abstract class Event {
	
	EventQueue eventQueue;
	State state;

	abstract void execute();
	abstract double getTime();
	abstract Customer getCustomer();

}
