
public class Piece {
	private int sideA;
	private int sideB;
	private int priority;
	
	public Piece(int sideA, int sideB) {
		this.sideA = sideA;
		this.sideB = sideB;
		this.priority = 0;
	}
	
	public boolean dual() {
		if(sideA == sideB) {
			return true;
		}
		return false;
	}
	
	public int getSideA() {
		return sideA;
	}
	
	public int getSideB() {
		return sideB;
	}
	
	public int getPrio() {
		return priority;
	}
	
	public int setPrio(int prio) {
		this.priority = prio;
		return priority;
	}
	
}
