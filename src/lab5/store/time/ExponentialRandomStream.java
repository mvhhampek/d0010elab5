package lab5.store.time;

import java.util.Random;

/**
 * Exponentially distributed random numbers
 */
public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;
	  /**
	   * Constructor
	   * @param lambda 
	   * @param seed, random number generators seed
	   */
	public ExponentialRandomStream(double lambda, long seed) {
	  	rand = new Random(seed);
	  	this.lambda = lambda;
	}
	  /**
	   * Constructor
	   * @param lambda
	   */
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
	    this.lambda = lambda;
	}
	  
	/**
	 * Returns a random number
	 * @return random number
	 */
	public double next() {
	  	return -Math.log(rand.nextDouble())/lambda;
	}
}

