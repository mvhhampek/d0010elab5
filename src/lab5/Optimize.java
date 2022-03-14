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
public class Optimize implements K {

	private int seed = SEED;
	private int maxCustomers = M;
	private double lambda = L;
	private double pickMin = LOW_COLLECTION_TIME;
	private double pickMax = HIGH_COLLECTION_TIME;
	private double payMin = LOW_PAYMENT_TIME;
	private double payMax = HIGH_PAYMENT_TIME;
	private double closingTime = END_TIME;

	/**
	 * Runs simulation with given parameters
	 * 
	 * @param maxCheckouts Max amount of checkouts in the store
	 * @param seed         seed for the random number generators
	 * @return the final state of the store
	 */
	public int metod1(int maxCheckouts, int maxCustomers, double lambda, double pickMin, double pickMax,
			double payMin, double payMax,
			int seed, double closingTime) {
		StoreState storeState = new StoreState(maxCheckouts, maxCustomers, lambda, pickMin, pickMax, payMin, payMax,
				seed, closingTime);
		State state = new State();
		EventQueue eventQueue = new EventQueue();
		eventQueue.push(new StartEvent(storeState, state, eventQueue));
		eventQueue.push(new EndEvent(storeState, state, eventQueue));
		Simulator simulator = new Simulator(state, eventQueue);
		simulator.run();
		return storeState.getMissedCostumers();
	}

	/**
	 * Method to find the optimal number of checkouts for the given seed
	 * 
	 * @param seed seed for random number generators
	 * @return the optimal number of checkouts for the given seed
	 */
	public int metod2(int seed) {
		int minCheckouts = maxCustomers;
		int missedCustomers = metod1(minCheckouts, maxCustomers, lambda, pickMin, pickMax, payMin, payMax, seed,
				closingTime);
		while (minCheckouts >= 1) {
			int currentMissedCustomers = metod1(minCheckouts, maxCustomers, lambda, pickMin, pickMax, payMin, payMax,
					seed, closingTime);
			//System.out.println("kassor: " + minCheckouts + "\t\tmissade kunder: " + currentMissedCustomers + "\t\tjämf. " + missedCustomers);
			if (missedCustomers != currentMissedCustomers) {
				return minCheckouts + 1;
			}
			minCheckouts--;
		}
		//System.out.println("==================");
		return maxCustomers;
	}

	public int metod3(int seed) {
		int minCheckouts = 0;
		Random ran = new Random(seed);
		for (int i = 0; i < 100; i++) {
			int currentCheckouts = metod2(ran.nextInt());
			System.out.println("Nuvarande kassor:" + currentCheckouts + "\t\tMin. kassor: " + minCheckouts);
			if (minCheckouts == Math.max(minCheckouts, currentCheckouts)) {
				i = 0;
			}
			minCheckouts = Math.max(minCheckouts, currentCheckouts);
		}
		return minCheckouts;
	}

	public static void main(String[] args) {
		Optimize o = new Optimize();
		System.out.println("metod 2: " + o.metod2(o.seed));
		System.out.println("--------------------");
		System.out.println("metod 3: " + o.metod3(o.seed));
	}
}