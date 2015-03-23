package logic;

public class AttackRelation {

	private Argument a1,a2;
	
	public AttackRelation(Argument a1, Argument a2) {
		this.a1 = a1;
		this.a2 = a2;
	}

	public Argument getA1() {
		return a1;
	}

	public void setA1(Argument a1) {
		this.a1 = a1;
	}

	public Argument getA2() {
		return a2;
	}

	public void setA2(Argument a2) {
		this.a2 = a2;
	}
	
	public String toString() {
		return a1.toString()+ " attacks " + a2.toString();
	}
}
