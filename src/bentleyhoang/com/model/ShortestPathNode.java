package bentleyhoang.com.model;

public class ShortestPathNode implements Comparable<ShortestPathNode> {
	private Edge edge;
	private int weight;
	
	public ShortestPathNode(Edge edge, int weight) {
		this.edge = edge;
		this.weight = weight;
	}
	
	public Edge getEdge() {
		return edge;
	}
	
	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(ShortestPathNode o) {
		int tmp = this.getWeight() - o.getWeight();
		if (tmp > 0) {
			return 1;
		} else if (tmp == 0) {
			return 0;
		} else {
			return -1;
		}
	}
}
