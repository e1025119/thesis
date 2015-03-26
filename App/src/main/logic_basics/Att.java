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

	
	/** 
	 * @brief returns the arguments that are attacked by a
	 */
	public ArrayList<Argument> getAttacked(Argument a) {
		ArrayList<Argument> arg = new ArrayList<Argument>();
		for(AttackRelation rel : attacks) {
			if(rel.getA1().equals(a)) {
				arg.add(rel.getA2());
			}
		}
		return arg;
	}
	
	/** 
	 * @brief returns the arguments that attack a
	 */
	public ArrayList<Argument> getAttacker(Argument a) {
		ArrayList<Argument> arg = new ArrayList<Argument>();
		for(AttackRelation rel : attacks) {
			if(rel.getA2().equals(a)) {
				arg.add(rel.getA1());
			}
		}
		return arg;
	}
	
	public String toString() {
		String ret = "";
		for(AttackRelation a : attacks) {
			ret += a.toString()+"\n";
		}
		return ret;
	}
}
