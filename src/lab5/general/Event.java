package lab5.general;

public abstract class Event {
	public Event(State state, EventQueue eventQueue, double time){}
	public Event(State state, EventQueue eventQueue){}
	public abstract void execute();
	public abstract double getTime();
	public abstract String getName();
}
