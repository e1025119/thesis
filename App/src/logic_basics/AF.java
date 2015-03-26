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
	 * @brief this method returns the unattacked (while ignoring all the relations in the given set) 
	 * 			arguments of an AF. 
	 */
	public AR getUnattacked(AR ign) {
		boolean add = true;
		ArrayList<Argument> arg = new ArrayList<Argument>();
		for(Argument a : ar.getArguments()) {
			add = true;
			for(AttackRelation rel : att.getAttacks()) {
				if(!ign.getArguments().isEmpty()) {
					if(a.equals(rel.getA2()) && !ign.contains(rel.getA1())) {
						add = false;
						break;
					}
				}
				else {
					if(a.equals(rel.getA2())) {
						add = false;
						break;
					}
				}
			}
			if(!arg.contains(a) && add) {
				arg.add(a);
			}
		}
		return new AR(arg);
	}

	public AR getUndecided() {
		ArrayList<Argument> ret = new ArrayList<Argument>();
		for(Argument a : ar.getArguments()) {
			if(a.getStatus() == 0) {
				ret.add(a);
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
