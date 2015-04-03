package logic_basics;

import java.util.ArrayList;

import exceptions.DuplicateArgumentException;

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
	
	public void add(Argument arg) throws DuplicateArgumentException {
		if(!this.contains(arg)) {
			this.arguments.add(arg);
		} else {
			throw new DuplicateArgumentException("An argument with the same \"ref\" already exists in this AR");
		}
	}
	
	public void addAll(AR ar) {
		for(Argument a : ar.getArguments()) {
			try {
				this.add(a);
			} catch (DuplicateArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public int size() {
		return this.arguments.size();
	}
	
	public String toString() {
		String ret = "";
		for(Argument a : arguments) {
			ret += a.toString()+"\n";
		}
		return ret;
	}
}
