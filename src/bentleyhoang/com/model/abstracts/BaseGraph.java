package bentleyhoang.com.model.abstracts;

import bentleyhoang.com.model.Edge;
import bentleyhoang.com.model.Vertex;

public interface BaseGraph {
	int getNumOfVertices();
	int getNumOfEdges();
	Iterable<Vertex> getVertices();
	Iterable<Edge> getEdges();
	void insertVertex(Vertex v);
	void insertEdge(Edge e);
}
