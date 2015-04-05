package logic_extensions;

import logic_basics.AF;
import logic_basics.AR;

public class PreferredExtension extends Extension {

	public PreferredExtension(AR arguments,AF framework) {
		super(arguments,framework);
	}
	
	public String toString() {
		return super.toString();
	}
	
	public String toStringDeveloper() {
		String ret = "---Preferred Extension---"+"\n";
		ret += super.toString();
		return ret;
	}
}
