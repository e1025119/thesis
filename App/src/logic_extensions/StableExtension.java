package logic_extensions;

import java.util.ArrayList;

import logic_basics.AF;
import logic_basics.Argument;

public class StableExtension extends Extension {

	public StableExtension(ArrayList<Argument> arguments,AF framework) {
		super(arguments,framework);
	}

	public String toString() {
		String ret = "---Stable Extension---"+"\n";
		ret += super.toString();
		return ret;
	}
}
