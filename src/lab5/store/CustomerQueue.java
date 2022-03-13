package lab5.store;

/**
 * FIFO queue for customers
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class CustomerQueue {

	private Customer[] queue;

	/**
	 * Constructor
	 */
	public CustomerQueue() {
		queue = new Customer[0];
	}

	/**
	 * Adds a customer to the end of the queue
	 * @param item customer to add to the queue
	 */
	public void push(Customer item) {
		int n = this.size();
		Customer[] temp = new Customer[n + 1];
		for (int i = 0; i < n; i++) {
			temp[i] = queue[i];
		}
		temp[n] = item;
		queue = temp;
	}

	/**
	 * The customer at the front of the queue
	 * @return customer at the front of the queue
	 */
	public Customer pop() {
		Customer tempC = queue[0];
		int n = this.size();
		Customer[] temp = new Customer[n - 1];
		for (int i = 1; i < n; i++) {
			temp[i - 1] = queue[i];
		}
		queue = temp;
		return tempC;
	}

	/**
	 * Returns whether the queue is empty or not
	 * @return true if the queue is empty, false other wise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the size of the queue
	 * @return the size of the queue
	 */
	public int size() {
		return queue.length;
	}

	/**
	 * Returns a string with the customer id's of the customers in the queue
	 * @return string representation of the queue
	 */
	public String toString() {
		String s = "[";
		for (int i = 0; i < size(); i++) {
			s += queue[i].getId();
			s += i != size() - 1 ? ", " : "]";
		}
		s += size() == 0 ? "]" : "";
		return s;
	}

}
