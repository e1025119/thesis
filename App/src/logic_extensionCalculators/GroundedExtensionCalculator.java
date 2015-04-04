package logic_extensionCalculators;

import java.util.ArrayList;

import exceptions.DuplicateArgumentException;
import logic_basics.*;
import logic_extensions.*;

public class GroundedExtensionCalculator extends ExtensionCalculator<GroundedExtension> {

	@Override
	public GroundedExtensionList calculate(AF framework) {
		return createSolution(null,null,framework);
	}

	@Override
	public GroundedExtensionList createSolution(AR pref,ExtensionList<GroundedExtension> rest,AF af) {
		CompleteExtensionCalculator cc1 = new CompleteExtensionCalculator();
		CompleteExtensionList cl1 = cc1.calculate(af);

		GroundedExtensionList ret = new GroundedExtensionList();
		if(cl1.contains(new CompleteExtension(new AR(new ArrayList<Argument>()),af))) {
			GroundedExtension empty = new GroundedExtension(new AR(),af);
			GroundedExtensionList emptyList = new GroundedExtensionList();
			emptyList.add(empty);
			return emptyList;
		}
		
		AR tmp = new AR(), retAR = new AR();
		for(CompleteExtension ce : cl1.getExtensions()) {
			tmp.addAll(ce.getArguments());
		}
		for(Argument a : tmp.getArguments()) {
			boolean cont = true;
			for(CompleteExtension ce : cl1.getExtensions()) {
				if(!ce.getArguments().contains(a)) {
					cont = false;
				}
			}
			if(cont) {
				try {
					retAR.add(a);
				} catch (DuplicateArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		GroundedExtension retGE = new GroundedExtension(retAR,af);
		ret.add(retGE);
		return ret;
	}
}
