package bentleyhoang.com.model;

import bentleyhoang.com.model.abstracts.BaseEdge;

public class Edge implements BaseEdge<Behavior> {
	private Behavior element;
	private Vertex tail;
	private Vertex head;
	private int weight;
	
	public Edge(Vertex u, Vertex v, Behavior e) {
		element = e;
		tail = u;
		head = v;
		weight = 1; // default weight
	}
	
	@Override
	public Behavior getElement() {
		return element;
	}
	
	@Override
	public String toString() {
		return String.format("%s%s%s", getTail(), getElement(), getHead());
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Edge)) {
			return false;
		}
		return getElement().equals(((Edge) o).getElement())
				&& getTail().equals(((Edge) o).getTail())
				&& getHead().equals(((Edge) o).getHead());
	}
	
	public Vertex getTail() {
		return tail;
	}
	
	public Vertex getHead() {
		return head;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getDelta() {
		return getWeight() + getHead().getDistance() - getTail().getDistance();
	}
	
	public boolean isSidetrackOf(Vertex v) {
		return getTail().equals(v) && !this.equals(v.getEdgeToPath()) && getWeight() >= 0;
	}
}
