package lab5;

import lab5.general.EventQueue;
import lab5.general.Simulator;
import lab5.general.State;
import lab5.store.EndEvent;
import lab5.store.StartEvent;
import lab5.store.StoreState;

import java.util.Random;

/**
 * Class to optimize the number of checkouts for a set of given parameters
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class Optimize {
	
	public Optimize() {
		System.out.println("ttt");
	}
	
    private int maxCustomers = 5;
    private int lambda = 1;
    private double pickMin = 0.5;
    private double pickMax = 1.0;
    private double payMin = 2.0;
    private double payMax = 3.0;
    private double closingTime = 10.0;

	/**
	 * Runs simulation with given parameters 
	 * @param maxCheckouts Max amount of checkouts in the store
	 * @param seed seed for the random number generators
	 * @return the final state of the store
	 */
	public StoreState metod1(int maxCheckouts, long seed) {
		StoreState storeState = new StoreState(maxCheckouts, maxCustomers, lambda, pickMin, pickMax, payMin, payMax,
				seed, closingTime);
		State state = new State();
		EventQueue eventQueue = new EventQueue();
		eventQueue.push(new StartEvent(storeState, state, eventQueue));
		eventQueue.push(new EndEvent(storeState, state, eventQueue));
		Simulator simulator = new Simulator(state, eventQueue);
		simulator.run();
		return storeState;
	}

	/**
	 * Method to find the optimal number of checkouts for the given seed
	 * @param seed seed for random number generators
	 * @return the optimal number of checkouts for the given seed
	 */
	public int metod2(long seed) {
		int antalCustomer = metod1(1, seed).getMaxCustomers();
		int antalKassor = 0;
		int antalMissade = antalCustomer;
		for (int i = 1; i <= antalCustomer; i++) {
			if (metod1(i, seed).getMissedCostumers() < antalMissade) {
				antalMissade = metod1(i, seed).getMissedCostumers();
				antalKassor = i;
			}
			if (antalMissade == 0) {
				return antalKassor;
			}
		}
		return antalKassor;
	}

	/**
	 * metod3 kör metod2 som kör metod1. metod3 letar efter den mest rimmliga antal
	 * kassor, för metod2 kan ge olika värden. Efter 100 varv av samma svar avbryts
	 * metod 3
	 * 
	 * @param f f är ett random heltal	
	 * @return returnerar det högstMinsta antal kassor.
	 */
	public int metod3(long f) {
		Random ran = new Random(f);
		boolean run = true;
		int högstaMinsta = metod2(ran.nextLong());
		int loop = 100;
		while (run) {
			if (loop == 0) {
				run = false;
			}
			if (metod2(ran.nextLong()) > högstaMinsta) {
				högstaMinsta = metod2(ran.nextLong());
				loop = 100;
			}
			if (metod2(ran.nextLong()) == högstaMinsta) {
				loop--;
			}
		}
		return högstaMinsta;
	}

	public static void main(String[] args) {
		Optimize o = new Optimize();
		System.out.println(o.metod3(123));
	}
}
