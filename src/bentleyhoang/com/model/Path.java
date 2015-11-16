package bentleyhoang.com.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Path implements List<Edge>, Comparable<Path> {
	private List<Edge> edges;
	
	public Path() {
		edges = new ArrayList<>();
	}
	
	public Path (Path p) {
		edges = new ArrayList<>(p);
	}
	
	public int getWeight() {
		int total = 0;
		for (Edge edge : edges) {
			total += edge.getWeight();
		}
		return total;
	}
	
	public int getDeltaWeight() {
		int total = 0;
		for(Edge edge : edges) {
			total += edge.getDelta();
		}
		return total;
	}
	
	@Override
	public String toString() {
		String tmp = "";
		for (Edge e : edges) {
			tmp += e.getTail().toString() + e.getElement().toString();
		}
		if (size() > 0)
			tmp += edges.get(size() - 1).getHead().toString();
		return tmp;
	}

	@Override
	public boolean add(Edge e) {
		return edges.add(e);
	}

	@Override
	public void add(int index, Edge element) {
		edges.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends Edge> c) {
		return edges.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Edge> c) {
		return edges.addAll(index, c);
	}

	@Override
	public void clear() {
		edges.clear();
	}

	@Override
	public boolean contains(Object o) {
		return edges.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return edges.containsAll(c);
	}

	@Override
	public Edge get(int index) {
		return edges.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return edges.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return edges.isEmpty();
	}

	@Override
	public Iterator<Edge> iterator() {
		return edges.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return edges.lastIndexOf(o);
	}

	@Override
	public ListIterator<Edge> listIterator() {
		return edges.listIterator();
	}

	@Override
	public ListIterator<Edge> listIterator(int index) {
		return edges.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return edges.remove(o);
	}

	@Override
	public Edge remove(int index) {
		return edges.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return edges.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return edges.removeAll(c);
	}

	@Override
	public Edge set(int index, Edge element) {
		return edges.set(index, element);
	}

	@Override
	public int size() {
		return edges.size();
	}

	@Override
	public List<Edge> subList(int fromIndex, int toIndex) {
		return edges.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return edges.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return edges.toArray(a);
	}

	@Override
	public int compareTo(Path o) {
		int tmp = getWeight() - o.getWeight();
		if (tmp > 0) {
			return 1;
		} else if (tmp == 0) {
			return 0;
		} else {
			return -1;
		}
	}
}
