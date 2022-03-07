package lab5.store;

import java.util.NoSuchElementException;

import java.util.ArrayList;

public class CustomerQueue implements Queue {
	private ArrayList<Object> queue = new ArrayList<Object>();
	private int maxSize = 0;

	@Override
	public void add(Object arg0) {
		queue.add(arg0);
		if (queue.size() < maxSize) {
			maxSize = queue.size();
		}
	}

	@Override
	public Object first() throws NoSuchElementException {
		if (queue.size() == 0) {
			throw new NoSuchElementException();
		}
		return queue.get(0);
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public int maxSize() {
		return maxSize;
	}

	@Override
	public void removeFirst() throws NoSuchElementException {

		if (queue.size() == 0) {
			throw new NoSuchElementException();
		}
		queue.remove(0);

	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public String toString() {
		String reString = "Queue: ";
		for (int i = 0; i < queue.size(); i++) {
			reString += "(" + String.valueOf(queue.get(i)) + ") ";
		}
		return reString;
	}

	@Override
	public boolean equals(Object f) throws ClassCastException {
		if (this.getClass() != f.getClass()) {
			throw new ClassCastException();
		}
		if (queue.size() != ((CustomerQueue) f).queue.size()) {
			return false;
		}
		for (int i = 0; i < queue.size(); i++) {
			if (queue.get(i) == null) {
				if (((CustomerQueue) f).queue.get(i) != null) {
					return false;
				}
			} else if (((CustomerQueue) f).queue.get(i) == null) {
				return false;
			} else if (!queue.get(i).equals(((CustomerQueue) f).queue.get(i))) {
				return false;
			}

		}
		return true;
	}

}

