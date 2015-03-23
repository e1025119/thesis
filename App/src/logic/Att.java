package logic;

public class Att {

	private AttackRelation[] attacks;
	
	public Att(AttackRelation[] attacks) {
		this.attacks = attacks;
	}

	public AttackRelation[] getAttacks() {
		return attacks;
	}

	public void setAttacks(AttackRelation[] attacks) {
		this.attacks = attacks;
	}
	
	public String toString() {
		String ret = "";
		for(AttackRelation a : attacks) {
			ret += a.toString()+"\n";
		}
		return ret;
	}
}
