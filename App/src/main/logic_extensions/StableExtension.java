package logic_extensions;

import logic_basics.AF;
import logic_basics.AR;

public class StableExtension extends Extension {

	public StableExtension(AR arguments,AF framework) {
		super(arguments,framework);
	}

	public String toString() {
		String ret = "---Stable Extension---"+"\n";
		ret += super.toString();
		return ret;
	}
}
