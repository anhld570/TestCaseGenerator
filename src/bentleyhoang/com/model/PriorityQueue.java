package bentleyhoang.com.model;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable<? super T>> {
	private List<T> queue;

	public PriorityQueue() {
		queue = new ArrayList<>();
	}
	
	public void enqueue(T elem) {
		queue.add(elem);
		siftUp(size() - 1);
	}
	
	public T dequeue() {
		if (isEmpty()) return null;
		T tmp = queue.get(0);
		queue.set(0, queue.get(size() - 1));
		queue.remove(queue.size() - 1);
		siftDown(0);
		return tmp;
	}
	
	public T peek() {
		return queue.get(0);
	}
	
	public int size() {
		return queue.size();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public void clear() {
		queue.clear();
	}
	
	private void swap(T firstElem, int firstIndex, T sencondElem, int secondIndex) {
		queue.set(firstIndex, sencondElem);
		queue.set(secondIndex, firstElem);
	}
	
	private void siftUp(int hole) {
		int pos = hole;
		while (pos > 0) {
			int parent = (pos - 1) / 2;
			if (queue.get(pos).compareTo(queue.get(parent)) < 0) {
				swap (queue.get(pos), pos, queue.get(parent), parent);
				pos = parent;
			} else {
				break;
			}
		}
	}
	
	private void siftDown(int hole) {
		int pos = hole;
		while (pos < (size() - 1)) {
			int left = 2 * pos + 1;
			int right = 2 * pos + 2;
			int cur = left;
			if (right <= (size() - 1) && queue.get(left).compareTo(queue.get(right)) > 0) {
				cur = right;
			}
			if (cur < size() && queue.get(pos).compareTo(queue.get(cur)) > 0) {
				swap (queue.get(pos), pos, queue.get(cur), cur);
				pos = cur;
			} else {
				break;
			}
		}
	}
}
