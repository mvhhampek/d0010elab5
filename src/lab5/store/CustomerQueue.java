package lab5.store;

import java.util.NoSuchElementException;

public class CustomerQueue {

	private Customer[] queue;
	private int mSize;

	public CustomerQueue() {
		queue = new Customer[0];
		mSize = 0;
	}

	public void add(Customer item) {
		if (mSize == this.size()) {
			mSize++;
		}
		int n = this.size();
		Customer[] temp = new Customer[n + 1];
		for (int i = 0; i < n; i++) {
			temp[i] = queue[i];
		}
		temp[n] = item;
		queue = temp;
	}

	public Customer pop() {
		Customer temp = first();
		removeFirst();
		return temp;
	}

	public Customer first() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return queue[0];
		}
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int maxSize() {
		return mSize;
	}

	public void removeFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			int n = this.size();
			Customer[] temp = new Customer[n - 1];
			for (int i = 1; i < n; i++) {
				// Skippar första elementet i queue
				temp[i - 1] = queue[i];
			}
			queue = temp;
		}
	}

	public int size() {
		return queue.length;
	}

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
