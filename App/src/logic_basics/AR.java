package logic_basics;

import java.util.ArrayList;

public class AR {

	private ArrayList<Argument> arguments;
	
	public AR(ArrayList<Argument> arguments) {
		ArrayList<Argument> tmp = new ArrayList<Argument>();
		tmp.addAll(arguments);
		this.arguments = tmp;
	}

	public AR() {
		this.arguments = new ArrayList<Argument>();
	}

	public ArrayList<Argument> getArguments() {
		return arguments;
	}

	public void setArguments(ArrayList<Argument> arguments) {
		this.arguments = arguments;
	}

	public boolean equals(AR ar) {
		if(ar.getArguments().size() != this.arguments.size()) {
			return false;
		}
		for(Argument a : this.arguments) {
			if(!ar.getArguments().contains(a)) {
				return false;
			}
		}
		return true;
	}

	public boolean contains(Argument a) {
		for(Argument arg : this.arguments) {
			if(arg.equals(a)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean trueSubsetOf(AR ar) {
		for(Argument arg : this.arguments) {
			if(!ar.contains(arg)) {
				return false;
			}
		}
		if(this.equals(ar)) {
			return false;
		}
		return true;
	}

	public void add(Argument arg) {
		if(!this.contains(arg)) {
			this.arguments.add(arg);
		}
	}

	public void addAll(AR ar) {
		for(Argument a : ar.getArguments()) {
			this.add(a);
		}
	}

	public Argument get(String ref) {
		for(Argument a : this.arguments) {
			if(a.getRef().equals(ref)) {
				return a;
			}
		}
		return null;
	}

	public void remove(Argument arg) {
		this.getArguments().remove(arg);
	}

	public boolean isConflictFree(Att att) {
		for(AttackRelation rel : att.getAttacks()) {
			if(this.arguments.contains(rel.getA1()) && this.arguments.contains(rel.getA2())) {
				return false;
			}
		}
		return true;
	}

	public boolean attacksArgument(Argument a,Att att) {
		for(Argument b : this.arguments) {
			if(att.contains(new AttackRelation(b,a))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean defends(Argument a,Att att) {
		AR attackers = att.getAttacker(a);
		for(Argument arg : attackers.getArguments()) {
			if(!this.attacksArgument(arg,att)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean disjunctiveSets(AR b) {
		for(Argument a1 : this.getArguments()) {
			if(b.contains(a1)) {
				return false;
			}
		}
		return true;
	}

	public int size() {
		return this.arguments.size();
	}
	
	public void setPaintAffAllFalse() {
		for(Argument a : arguments) {
			a.setPaintAFF(false);
		}
	}
	
	public void setPaintSbsfAllFalse() {
		for(Argument a : arguments) {
			a.setPaintSBSF_green(false);
			a.setPaintSBSF_blue(false);
			a.setPaintSBSF_orange(false);
			a.setPaintSBSF_cyan(false);
		}
	}
	
	public void setPaintEfAllFalse() {
		for(Argument a : arguments) {
			a.setPaintEF(false);
		}
	}

	public String toString() {
		String ret = "{";
		for(Argument a : arguments) {
			ret += a.toString()+"\n";
		}
		ret += "}";
		return ret;
	}
}
