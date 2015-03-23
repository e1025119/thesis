package logic_extensions;

import java.util.ArrayList;

import logic_basics.AF;
import logic_basics.Argument;

public class GroundedExtension extends Extension {

	public GroundedExtension(ArrayList<Argument> arguments,AF framework) {
		super(arguments,framework);
	}
	
	public String toString() {
		String ret = "---Grounded Extension---"+"\n";
		ret += super.toString();
		return ret;
	}
}
