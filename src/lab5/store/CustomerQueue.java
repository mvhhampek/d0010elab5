package lab5.store;

import java.util.NoSuchElementException;

public class CustomerQueue {
	
	private Object[] queue;
	private int mSize;

	public CustomerQueue() {
		queue = new Object[0];
		mSize = 0;
	}

	public void add(Object item) {
		if (mSize == this.size()) {
			mSize++;
		}
		int n = this.size();
		Object[] temp = new Object[n + 1];
		for (int i = 0; i < n; i++) {
			temp[i] = queue[i];
		}
		temp[n] = item;
		queue = temp;
	}

	public Object first() throws NoSuchElementException {
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
			Object[] temp = new Object[n - 1];
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


}

