package logic_extensions;

import java.util.ArrayList;

import logic_basics.AF;
import logic_basics.Argument;

public class CompleteExtension extends Extension {

	public CompleteExtension(ArrayList<Argument> arguments,AF framework) {
		super(arguments,framework);
	}
	
	public String toString() {
		String ret = "---Complete Extension---"+"\n";
		ret += super.toString();
		return ret;
	}
}
