package bentleyhoang.com.model;

import java.util.ArrayList;
import java.util.List;

import bentleyhoang.com.model.abstracts.BaseVertex;

public class Vertex implements BaseVertex<State> {
	private State element;
	private Edge edgeToPath;
	private int distance;
	private List<Edge> relatedEdges;
	
	public Vertex(State e) {
		element = e;
		distance = Integer.MIN_VALUE;
		relatedEdges = new ArrayList<>();
	}
	
	@Override
	public State getElement() {
		return element;
	}
	
	@Override
	public String toString() {
		return String.format("[%s]", getElement().toString());
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Vertex)) {
			return false;
		}
		return getElement().equals(((Vertex) o).getElement());
	}
	
	public Vertex nextVertex() {
    	if (edgeToPath == null) {
    		return null;
    	} else {
    		return edgeToPath.getHead();
    	}
    }
	
	public Edge getEdgeToPath() {
		return edgeToPath;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public List<Edge> getRelatedEdges() {
		return relatedEdges;
	}
	
	public void setEdgeToPath(Edge e) {
		edgeToPath = e;
	}
	
	public void setDistance(int d) {
		distance = d;
	}
	
	public void addRelatedEdge(Edge e) {
		relatedEdges.add(e);
	}
}
