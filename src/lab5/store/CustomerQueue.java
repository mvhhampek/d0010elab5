package lab5.store;

import java.util.NoSuchElementException;

import java.util.ArrayList;

public class CustomerQueue {

	private ArrayList<Object> queue;
	private int maxSize = 0;

    public CustomerQueue(){
	    queue = new ArrayList<Object>();
    }
	public void add(Object arg0) {
		queue.add(arg0);
		if (queue.size() < maxSize) {
			maxSize = queue.size();
		}
	}

	public Object first() throws NoSuchElementException {
		if (queue.size() == 0) {
			throw new NoSuchElementException();
		}
		return queue.get(0);
	}


	public boolean isEmpty() {
		return size() == 0;
	}

	public int maxSize() {
		return maxSize;
	}

	public void removeFirst() throws NoSuchElementException {

		if (queue.isEmpty()) {
			throw new NoSuchElementException();
		}
		queue.remove(0);

	}

	public int size() {
		return queue.size();
	}


}

