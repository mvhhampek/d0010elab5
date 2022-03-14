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
	 * 
	 * @param maxCheckouts Amount of checkouts
	 * @param maxCustomers Max amount of customers in the store
	 * @param lambda       Customer arrival speed
	 * @param pickMin      Lower bound for pick time
	 * @param pickMax      Upper bound for pick time
	 * @param payMin       Upper bound for pay time
	 * @param payMax       Lower bound for pay time
	 * @param seed         Seed for random number generators
	 * @param closingTime  Closing time for the store
	 * @return Amount of missed custoemrs
	 */
	public int runOnce(int maxCheckouts, int maxCustomers, double lambda, double pickMin, double pickMax,
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
	 * Method to find the optimal number of checkouts for a given seed
	 * 
	 * @param seed seed for random number generators
	 * @return the optimal number of checkouts for the given seed
	 */
	public int optCheckoutsGivenSeed(int seed) {
		int minCheckouts = maxCustomers;
		int missedCustomers = runOnce(minCheckouts, maxCustomers, lambda, pickMin, pickMax, payMin, payMax, seed,
				closingTime);
		while (minCheckouts >= 1) {
			int currentMissedCustomers = runOnce(minCheckouts, maxCustomers, lambda, pickMin, pickMax, payMin, payMax,
					seed, closingTime);
			if (missedCustomers != currentMissedCustomers) {
				return minCheckouts + 1;
			}
			minCheckouts--;
		}
		return maxCustomers;
	}

	/**
	 * Method to find optimal amount of checkouts independent of the random number
	 * generators seed
	 * 
	 * @param seed seed for random number generator
	 * @return Optimal amount of checkouts
	 */
	public int optCheckoutsRandomSeed(int seed) {
		int compare = Integer.MAX_VALUE;
		Random ran = new Random(seed);
		int i = 0;
		while (i < 100) {
			int checkouts = optCheckoutsGivenSeed(ran.nextInt());
			if (checkouts < compare) {
				compare = checkouts;
				i = 0;
			}
			i++;
		}
		return compare;
	}

	/**
	 * Main
	 * 
	 * @param args runtime arguments
	 */
	public static void main(String[] args) {
		Optimize o = new Optimize();
		System.out.println("metod 2: " + o.optCheckoutsGivenSeed(o.seed));
		System.out.println("metod 3: " + o.optCheckoutsRandomSeed(o.seed));
	}
}