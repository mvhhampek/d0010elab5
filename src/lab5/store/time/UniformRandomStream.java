package lab5.store.time;

import java.util.Random;

/**
 * Random numbers within a given intervall
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class UniformRandomStream {

	private Random rand;
	private double lower, width;

	/**
	 * Constructor
	 * 
	 * @param lower Lower bound of the intervall
	 * @param upper Upper bound of the intervall
	 * @param seed  Random number generators seed
	 */
	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper - lower;
	}

	/**
	 * Constructor
	 * 
	 * @param lower Lower bound of the intervall
	 * @param upper Upper bound of the intervall
	 */
	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
		this.lower = lower;
		this.width = upper - lower;
	}

	/**
	 * Returns random number within intervall
	 * 
	 * @return random number
	 */
	public double next() {
		return lower + rand.nextDouble() * width;
	}
}
