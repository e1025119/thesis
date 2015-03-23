package logic_basics;

import java.util.ArrayList;

public class Att {

	private ArrayList<AttackRelation> attacks;
	
	public Att(ArrayList<AttackRelation> attacks) {
		this.attacks = attacks;
	}

	public ArrayList<AttackRelation> getAttacks() {
		return attacks;
	}

	public void setAttacks(ArrayList<AttackRelation> attacks) {
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
