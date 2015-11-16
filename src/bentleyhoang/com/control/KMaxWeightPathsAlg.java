package bentleyhoang.com.control;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import bentleyhoang.com.model.Edge;
import bentleyhoang.com.model.Graph;
import bentleyhoang.com.model.Path;
import bentleyhoang.com.model.PriorityQueue;
import bentleyhoang.com.model.ShortestPathNode;
import bentleyhoang.com.model.SidetracksNode;
import bentleyhoang.com.model.Vertex;

public class KMaxWeightPathsAlg {
	private final static int TIME_BOUND = 1000000;
	private List<Vertex> vertices;
	private PriorityQueue<SidetracksNode> pathsHeap;
	private Vertex sourceVertex;
	private Vertex endVertex;
	private boolean isReady;
	
	public KMaxWeightPathsAlg(Graph g) {
		vertices = new ArrayList<>();
		for (Vertex v : g.getVertices()) {
			vertices.add(v);
		}
		pathsHeap = new PriorityQueue<>();
		isReady = false;
	}
	
	public void findPathsHaveKMaxLength(Vertex s, Vertex t, int k, final String dataFileName) {
		List<Path> paths = findPathsHaveKMaxLength(s, t, k);
		
		try {
			FileWriter fileWriter = new FileWriter(dataFileName);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			int index = 0;
			for (Path p : paths) {
				index++;
				printWriter.println(index + ". " + p);
			}
			
			printWriter.close();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Path> findPathsHaveKMaxLength(Vertex s, Vertex t, int k) {
		List<Path> paths = new ArrayList<>();
		Path path = findShortestPath(s, t, k);
		paths.add(path);
		for (int i = 0; i < TIME_BOUND; i++) {
			Path p = findNextShortestPath();
			if (p.getWeight() > 0 && p.getWeight() <= k) {
				paths.add(p);
			}
		}
		return paths;
	}
	
	private Path findShortestPath(Vertex s, Vertex t, int k) {
		isReady = false;
		
		if (vertices.contains(s) && vertices.contains(t)) {
			sourceVertex = vertices.get(vertices.indexOf(s));
			endVertex = vertices.get(vertices.indexOf(t));
		} else {
			throw new IllegalArgumentException("Invalid source or end vertex");
		}
		
		if (sourceVertex == null || endVertex == null) {
			return new Path();
		}
		
		buildShortestPathTree();
		buildSidetracksHeap(k);
		isReady = true;
		
		return findNextShortestPath();
	}
	
	private Path findNextShortestPath() {
		if(!isReady)
			return new Path();
		
		SidetracksNode node = pathsHeap.dequeue();
		if (node == null)
			return new Path();
		return rebuildPath(node.getSidetracks());
	}
	
	private void resetGraphState() {
		for (Vertex v : vertices) {
			v.setEdgeToPath(null);
			v.setDistance(Integer.MIN_VALUE);
		}
	}
	
	private void buildShortestPathTree() {
		resetGraphState();
		
		Vertex v = vertices.get(vertices.indexOf(endVertex));
		v.setDistance(0);
		
		PriorityQueue<ShortestPathNode> queue = new PriorityQueue<>();
		
		do {
			if (v != null) {
				for (Edge e : v.getRelatedEdges()) {
					if (e.getHead().equals(v) && e.getWeight() >= 0) {
						queue.enqueue(new ShortestPathNode(e, 
								e.getWeight() + e.getHead().getDistance()));
					}
				}
			}
			
			ShortestPathNode node = queue.dequeue();
			if (node == null)
				break;
			
			Edge e = node.getEdge();
			v = vertices.get(vertices.indexOf(e.getTail()));
			if (v.getDistance() == Integer.MIN_VALUE) { 
				v.setDistance(e.getWeight() + e.getHead().getDistance());
				v.setEdgeToPath(e);
			} else
				v= null;
		} while(true);
	}
	
	private void buildSidetracksHeap(int k) {
		pathsHeap = new PriorityQueue<SidetracksNode>();
		Path empty = new Path();
		pathsHeap.enqueue(new SidetracksNode(empty));
		addSidetracks(empty, sourceVertex, k);
	}
	
	private void addSidetracks(Path p, Vertex vert, int k) {
		if (k > 0) {
			Vertex v = vertices.get(vertices.indexOf(vert));

			for (Edge e : v.getRelatedEdges()) {
				if (e.isSidetrackOf(v) 
						&& (e.getHead().getEdgeToPath() != null || e.getHead().equals(endVertex))) {
					k--;
					Path path = new Path(p);
					path.add(e);
					pathsHeap.enqueue(new SidetracksNode(path));
					
					if (!e.getHead().equals(v)) {
						addSidetracks(path, e.getHead(), k);
					}
				}
			}
			if (v.nextVertex() != null) {
				addSidetracks(p, v.nextVertex(), k);
			}
		}	
	}
	
	private Path rebuildPath(Path sidetracks) {
		Path path = new Path();
		Vertex v = sourceVertex;
		int i = 0;
		
		while (v != null) {
			if (i < sidetracks.size() && sidetracks.get(i).getTail().equals(v)) {
				path.add(sidetracks.get(i));
				v = sidetracks.get(i++).getHead();
			} else {
				if (v.getEdgeToPath() == null)
					break;
				path.add(v.getEdgeToPath());
				v = v.nextVertex();
			}
		}
		return path;
	}
}
