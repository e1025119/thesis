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
	public AR getAttacked(Argument a) {
		ArrayList<Argument> arg = new ArrayList<Argument>();
		for(AttackRelation rel : attacks) {
			if(rel.getA1().equals(a)) {
				arg.add(rel.getA2());
			}
		}
		return new AR(arg);
	}
	
	/** 
	 * @brief returns the arguments that attack a
	 */
	public AR getAttacker(Argument a) {
		ArrayList<Argument> arg = new ArrayList<Argument>();
		for(AttackRelation rel : attacks) {
			if(rel.getA2().equals(a)) {
				arg.add(rel.getA1());
			}
		}
		return new AR(arg);
	}

	public boolean contains(AttackRelation rel) {
		for(AttackRelation r : this.attacks) {
			if(r.equals(rel)) {
				return true;
			}
		}
		return false;
	}
	
	public void add(AttackRelation ar) {
		if(!this.contains(ar)) {
			this.attacks.add(ar);
		}
	}
	
	public void addAll(Att att) {
		for(AttackRelation a : att.getAttacks()) {
			this.add(a);
		}
	}

	
	public boolean equals(Att att) {
		if(this.attacks.size() != att.getAttacks().size()) {
			return false;
		}
		for(AttackRelation at : this.attacks) {
			if(!att.getAttacks().contains(at)) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String ret = "";
		for(AttackRelation a : attacks) {
			ret += a.toString()+"\n";
		}
		return ret;
	}
}
