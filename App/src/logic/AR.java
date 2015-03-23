package logic;

public class AR {

	private Argument[] arguments;
	
	public AR(Argument[] arguments) {
		this.arguments = arguments;
	}

	public Argument[] getArguments() {
		return arguments;
	}

	public void setArguments(Argument[] arguments) {
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
