package logic_basics;

import java.util.ArrayList;

public class AF {

	private AR ar;
	private Att att;

	public AF(AR ar,Att att) {
		this.ar = new AR(ar.getArguments());
		this.att = new Att(att.getAttacks());
	}

	public AR getAr() {
		return ar;
	}

	public void setAr(AR ar) {
		this.ar = ar;
	}

	public Att getAtt() {
		return att;
	}

	public void setAtt(Att att) {
		this.att = att;
	}

	/** 
	 * @brief this method returns the completely isolated arguments. 
	 */
	public AR getUntouched() {
		AR arg = new AR(this.ar.getArguments());
		for(AttackRelation rel : att.getAttacks()) {
			arg.remove(rel.getA1());
			arg.remove(rel.getA2());
		}
		return arg;
	}

	/** 
	 * @brief this method returns all arguments that only attack while being unattacked itself.
	 */
	public AR getUnattacked() {
		AR arg = new AR(this.ar.getArguments());
		for(AttackRelation rel : this.att.getAttacks()) {
			arg.remove(rel.getA2());
		}
		return arg;
	}

	/** 
	 * @brief this method returns all the arguments, that cannot be part of any admissible set.
	 * 			in other words all the arguments, that are attacked by the output of getUnattacked().
	 * */
	public AR getIndefendables() {
		AR unatt = getUnattacked();
		unatt.addAll(getUntouchedSelfies());
		AR ret = new AR();
		for(AttackRelation rel : this.att.getAttacks()) {
			if(unatt.contains(rel.getA1())) {
				ret.add(rel.getA2());
			}
		}
		return ret;
	}

	public AR getUntouchedSelfies() {
		AR tmp = new AR(), ret = new AR();
		tmp.addAll(getSelfies());
		for(Argument a : tmp.getArguments()) {
			if(this.att.getAttacker(a).getArguments().size() == 1) {
				ret.add(a);
			}
		}
		return ret;
	}
	
	/** 
	 * @brief returns all the arguments in the framework, that are attacking itself.
	 */
	public AR getSelfies() {
		ArrayList<Argument> ret = new ArrayList<Argument>();
		for(AttackRelation rel : this.att.getAttacks()) {
			if(rel.getA1().equals(rel.getA2())) {
				ret.add(rel.getA1());
			}
		}
		return new AR(ret);
	}
	
	/** 
	 * @brief this method checks if a given set of arguments is
	 * 			a stable subset (it attacks all arguments not in the set, conflict-free).
	 * */
	public boolean isStableSubset(AR sub) {
		AR others = new AR();
		others.addAll(this.ar);
		for(Argument a : sub.getArguments()) {
			others.remove(a);
		}
		
		for(Argument a : others.getArguments()) {
			if(!sub.attacksArgument(a,this.att)) {
				return false;
			}
		}
		return true;
	}
	
	/** 
	 * @brief this method checks if a given set of arguments is
	 * 			an admissible subset (it defends every argument it has, conflict-free).
	 * */
	public boolean isAdmissibleSubset(AR sub) {
		AR others = new AR();
		others.addAll(this.ar);
		for(Argument a : sub.getArguments()) {
			others.remove(a);
		}
		
		for(AttackRelation  rel : this.att.getAttacks()) {
			if(sub.contains(rel.getA1()) && sub.contains(rel.getA2())) {
				return false;
			}
			if(sub.contains(rel.getA2()) && others.contains(rel.getA1())) {
				if(!sub.attacksArgument(rel.getA1(),att)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean equals(AF af) {
		if(this.ar.equals(af.getAr()) && this.att.equals(af.getAtt())) {
			return true;
		}
		return false;
	}

	public String toString() {
		String ret = "";
		ret += "----------AF----------"+"\n";
		ret += ar.toString()+"\n";
		ret += att.toString()+"\n";
		ret += "----------------------"+"\n";
		return ret;
	}
}
