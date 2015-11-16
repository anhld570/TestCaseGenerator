package bentleyhoang.com.model;

public class Behavior {
	private final String name;
	
	public Behavior(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format(", %s-> ", getName());
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Behavior)) {
			return false;
		}
		return name.equals(((Behavior) o).getName());
	}
}
