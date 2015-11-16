package bentleyhoang.com.model;

public class SidetracksNode implements Comparable<SidetracksNode> {
	private Path sidetracks;
	private int weight;
	
	public SidetracksNode(Path sidetracks) {
		this.sidetracks = new Path(sidetracks);
		weight = this.sidetracks.getDeltaWeight();
	}
	
	public Path getSidetracks() {
		return sidetracks;
	}
	
	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(SidetracksNode o) {
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
