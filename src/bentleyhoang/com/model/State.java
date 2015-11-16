package bentleyhoang.com.model;

public class State {
	private final String id;
	private final int card;
	private final int credit;
	
	public State(String id, int card, int credit) {
		this.id = id;
		this.card = card;
		this.credit = credit;
	}
	
	@Override
	public String toString() {
		return String.format("%s:(card=%d,credit=%d)", getId(), getCard(), getCredit());
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof State)) {
			return false;
		}
		return id.equals(((State) o).getId()) && card == ((State) o).getCard() && credit == ((State) o).getCredit();
	}
	
	public String getId() {
		return id;
	}
	
	public int getCard() {
		return card;
	}
	
	public int getCredit() {
		return credit;
	}
}
