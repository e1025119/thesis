package logic_basics;

import java.util.ArrayList;

public class AF {

	private AR ar;
	private Att att;

	public AF(AR ar,Att att) {
		this.ar = ar;
		this.att = att;
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
		boolean add = true;
		ArrayList<Argument> arg = new ArrayList<Argument>();
		for(Argument a : ar.getArguments()) {
			add = true;
			for(AttackRelation rel : att.getAttacks()) {
				if(a.equals(rel.getA2()) || a.equals(rel.getA1())) {
					add = false;
					break;
				}
			}
			if(!arg.contains(a) && add) {
				arg.add(a);
			}
		}
		return new AR(arg);
	}

	/** 
	 * @brief this method returns all arguments that only attack while being unattacked itself.
	 */
	public AR getUnattacked() {
		AR arg = new AR();
		for(Argument a : this.ar.getArguments()){
			for(AttackRelation rel : this.att.getAttacks()) {
				if(rel.getA2().equals(a)) {
					break;
				}
				arg.add(a);
			}
		}
		return arg;
	}
	
	//TODO nochmal überprüfen :)
	
	/** 
	 * @brief this method returns all the arguments, that cannot be part of any admissible set.
	 * 			in other words all the arguments, that are attacked by the output of getUnattacked().
	 * */
	public AR getIndefendables() {
		AR unatt = getUnattacked();
		AR ret = new AR();
		for(AttackRelation rel : this.att.getAttacks()) {
			if(unatt.contains(rel.getA1())) {
				ret.add(rel.getA2());
			}
 		}
		return ret;
	}

	/** 
	 * @brief returns all the arguments in the framework, that are attacking itself.
	 */
	public AR getSelfies() {
		ArrayList<Argument> ret = new ArrayList<Argument>();
		for(Argument a : this.ar.getArguments()) {
			for(AttackRelation rel : this.att.getAttacks()) {
				if(rel.equals(new AttackRelation(a,a))) {
					ret.add(a);
				}
			}
		}
		return new AR(ret);
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
