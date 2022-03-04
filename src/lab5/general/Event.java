package lab5.general;

public abstract class Event {
	
	//EventQueue eventQueue;
	//State state;

	protected abstract void execute();
	protected abstract double getTime();
}
