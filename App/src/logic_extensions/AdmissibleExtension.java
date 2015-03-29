package logic_extensions;

import logic_basics.AF;
import logic_basics.AR;

public class AdmissibleExtension extends Extension {

	public AdmissibleExtension(AR arguments,AF framework) {
		super(arguments,framework);
	}

	public String toString() {
		String ret = "---Admissible Extension---"+"\n";
		ret += super.toString();
		return ret;
	}

}
