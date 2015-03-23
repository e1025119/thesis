package logic_extensions;

import java.util.ArrayList;

import logic_basics.AF;
import logic_basics.Argument;

public class PreferredExtension extends Extension {

	public PreferredExtension(ArrayList<Argument> arguments,AF framework) {
		super(arguments,framework);
	}
	
	public String toString() {
		String ret = "---Preferred Extension---"+"\n";
		ret += super.toString();
		return ret;
	}
}
