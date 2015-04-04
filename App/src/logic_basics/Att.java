package logic_basics;

import java.util.ArrayList;
import exceptions.InvalidArgumentException;

public class Att {

	private ArrayList<AttackRelation> attacks;

	public Att(ArrayList<AttackRelation> attacks) {
		ArrayList<AttackRelation> tmp = new ArrayList<AttackRelation>();
		tmp.addAll(attacks);
		this.attacks = tmp;
	}

	public Att() {
		this.attacks = new ArrayList<AttackRelation>();
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

	public void add(AttackRelation rel,AR ar) throws InvalidArgumentException {
		if(rel.getA1() != null && rel.getA2() != null) {
			if(!ar.contains(rel.getA1()) || !ar.contains(rel.getA2())) {
				throw new InvalidArgumentException("One of those Arguments does not exist.");
			}
			else if(!this.contains(rel)) {
				this.attacks.add(rel);
			}
		} else {
			throw new InvalidArgumentException("One of those Arguments does not exist");
		}
	}

	public void addAll(Att att,AR ar) {
		for(AttackRelation a : att.getAttacks()) {
			try {
				this.add(a,ar);
			} catch (InvalidArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Att getRelationsOfArgument(Argument a) {
		ArrayList<AttackRelation> ret = new ArrayList<AttackRelation>();
		for(AttackRelation rel : this.attacks) {
			if(rel.getA1().equals(a) || rel.getA2().equals(a)) {
				ret.add(rel);
			}
		}
		return new Att(ret);
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
