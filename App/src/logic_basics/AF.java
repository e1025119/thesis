package logic_basics;

import java.util.ArrayList;

import logic_extensionCalculators.CompleteExtensionCalculator;
import logic_extensionCalculators.GroundedExtensionCalculator;
import logic_extensionCalculators.PreferredExtensionCalculator;
import logic_extensionCalculators.StableExtensionCalculator;
import logic_extensions.CompleteExtensionList;
import logic_extensions.GroundedExtensionList;
import logic_extensions.PreferredExtensionList;
import logic_extensions.StableExtensionList;

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

	/** 
	 * @brief this method returns all arguments that only attack themselves and are completely 
	 * 			isolated otherwise.
	 */
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
	
	/** 
	 * @brief this method checks if an argument framework is well-founded
	 *			(preferred, complete, grounded and stable extension coincide).
	 */
	public boolean isWellFounded() {
		CompleteExtensionCalculator c1 = new CompleteExtensionCalculator();
		PreferredExtensionCalculator p1 = new PreferredExtensionCalculator();
		GroundedExtensionCalculator g1 = new GroundedExtensionCalculator();
		StableExtensionCalculator s1 = new StableExtensionCalculator();
		
		CompleteExtensionList cl1 = c1.calculate(this);
		PreferredExtensionList pl1 = p1.calculate(this);
		GroundedExtensionList gl1 = g1.calculate(this);
		StableExtensionList sl1 = s1.calculate(this);
		
		if(cl1.equals(pl1) && pl1.equals(gl1) && gl1.equals(sl1)) {
			return true;
		}
		return false;
	}
	
	/** 
	 * @brief this method checks if an argument framework is coherent
	 * 			(its preferred and stable extension coincide). 
	 */
	public boolean isCoherent() throws NullPointerException {
		PreferredExtensionCalculator p1 = new PreferredExtensionCalculator();
		StableExtensionCalculator s1 = new StableExtensionCalculator();
		
		PreferredExtensionList pl1 = p1.calculate(this);
		StableExtensionList sl1 = s1.calculate(this);
		
		if(sl1.equals(pl1)) {
			return true;
		}
		return false;
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
