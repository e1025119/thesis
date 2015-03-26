package logic_extensions;

import logic_basics.AF;
import logic_basics.AR;

public class CompleteExtension extends Extension {

	public CompleteExtension(AR arguments,AF framework) {
		super(arguments,framework);
	}
	
	public String toString() {
		String ret = "---Complete Extension---"+"\n";
		ret += super.toString();
		return ret;
	}
}
