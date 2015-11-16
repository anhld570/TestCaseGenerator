package bentleyhoang.com.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bentleyhoang.com.model.abstracts.BaseGraph;

public class Graph implements BaseGraph {
	private List<Vertex> vertices;
	private List<Edge> edges;
	
	public Graph() {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}
	
	public Graph(final String dataFileName) {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(dataFileName))) {
			String sCurrentLine;
			Vertex source = new Vertex(new State("", Integer.MIN_VALUE, Integer.MIN_VALUE));
			
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.isEmpty()) {
					// Do nothing here
				} else {
					if (!sCurrentLine.startsWith("\t")) {
						source = new Vertex(readState(sCurrentLine));
						this.insertVertex(source);
					}
					if (sCurrentLine.startsWith("\t")) {
						String[] parts = sCurrentLine.split("->", 2);
						Behavior behavior = new Behavior(parts[0].trim().toLowerCase());
						
						Vertex end = new Vertex(readState(parts[1]));
						insertVertex(end);
						
						Edge edge = new Edge(source, end, behavior);
						this.insertEdge(edge);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private State readState(String s) {
		String tmp = s.trim();
		String[] parts = tmp.split(":", 2);
		String id  = parts[0];
		
		parts[1] = parts[1].replaceAll("[^-?0-9]+", " ");
		String[] tParts = parts[1].trim().split(" ");
		int card = Integer.parseInt(tParts[0]);
		int credit = Integer.parseInt(tParts[1]);
		
		return new State(id, card, credit);
	}

	@Override
	public int getNumOfVertices() {
		return vertices.size();
	}

	@Override
	public int getNumOfEdges() {
		return edges.size();
	}

	@Override
	public Iterable<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public Iterable<Edge> getEdges() {
		return edges;
	}

	@Override
	public void insertVertex(Vertex v) {
		if (!hasVertex(v)) {
			Vertex vert = new Vertex(v.getElement());
			vertices.add(vert);
		}
	}

	@Override
	public void insertEdge(Edge e) {
		Vertex tail = e.getTail();
		Vertex head = e.getHead();
		if (!hasEdge(e) && hasVertex(tail) && hasVertex(head)) {
			head = vertices.get(vertices.indexOf(head));
			tail = vertices.get(vertices.indexOf(tail));
			Edge edge = new Edge(tail, head, e.getElement());
			edges.add(edge);
			edge.getTail().addRelatedEdge(edge);
			if (!edge.getHead().equals(edge.getTail())) {
				edge.getHead().addRelatedEdge(edge);
			}
		}
	}
	
	private boolean hasVertex(Vertex v) {
		if (vertices.contains(v)) {
			return true;
		}
		return false;
	}
	
	private boolean hasEdge(Edge e) {
		if (edges.contains(e)) {
			return true;
		}
		return false;
	}
}
