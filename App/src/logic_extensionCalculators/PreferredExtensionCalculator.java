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
		ArrayList<Argument> tmp1,tmp2 = new ArrayList<Argument>();

		ar.addAll(framework.getUnattacked(null));
		for(Argument a : ar) {
			tmp1 = framework.getAtt().getAttacked(a);
			for(Argument b : tmp1) {
				tmp2.addAll(framework.getUnattacked(b));
			}
		}

		for(Argument a : tmp2) {
			if(!ar.contains(a)) {
				ar.add(a);
			}
		}

		return new PreferredExtension(ar,framework);
	}
}
