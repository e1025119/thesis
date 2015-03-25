package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;

public class PreferredExtensionCalculator implements ExtensionCalculator {

	public PreferredExtensionCalculator() {
	}

	@Override
	public PreferredExtension calculate(AF framework) {
		ArrayList<Argument> ar = new ArrayList<Argument>();
		ArrayList<Argument> tmp1 = new ArrayList<Argument>(),tmp2 = new ArrayList<Argument>();

		ar.addAll(framework.getUnattacked(tmp1));
		for(Argument a : ar) {
			tmp1 = framework.getAtt().getAttacked(a);
			tmp2.addAll(framework.getUnattacked(tmp1));
		}

		for(Argument a : tmp2) {
			if(!ar.contains(a)) {
				ar.add(a);
			}
		}

		return new PreferredExtension(ar,framework);
	}
}
