package logic_basics;

import java.util.ArrayList;

public class AR {

	private ArrayList<Argument> arguments;
	
	public AR(ArrayList<Argument> arguments) {
		this.arguments = arguments;
	}

	public ArrayList<Argument> getArguments() {
		return arguments;
	}

	public void setArguments(ArrayList<Argument> arguments) {
		this.arguments = arguments;
	}
	
	public String toString() {
		String ret = "";
		for(Argument a : arguments) {
			ret += a.toString()+"\n";
		}
		return ret;
	}
}
