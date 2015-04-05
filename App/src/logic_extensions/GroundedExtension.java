package logic_extensions;

import logic_basics.AF;
import logic_basics.AR;

public class GroundedExtension extends Extension {

	public GroundedExtension(AR arguments,AF framework) {
		super(arguments,framework);
	}
	
	public String toString() {
		return super.toString();
	}
	
	public String toStringDeveloper() {
		String ret = "---Grounded Extension---"+"\n";
		ret += super.toString();
		return ret;
	}
}
