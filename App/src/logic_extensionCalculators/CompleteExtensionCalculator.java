package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;

public class CompleteExtensionCalculator extends ExtensionCalculator<CompleteExtension> {

	@Override
	public CompleteExtensionList calculate(AF framework) {
		AR pref = new AR(), conflicting = new AR();

		/* deterministic part */
		pref.addAll(framework.getUntouched());
		pref.addAll(framework.getUnattacked());
		conflicting.addAll(framework.getIndefendables());
		conflicting.addAll(framework.getSelfies());

		/* non-deterministic part */
		AR rest = new AR();
		for(Argument a : framework.getAr().getArguments()) {
			if(!conflicting.contains(a) && !pref.contains(a)) {
				rest.add(a);
			}
		}

		ArrayList<AR> partSol = new ArrayList<AR>();
		powerSet(0,new AR(),rest,partSol);

		/* create all valid solutions by building the cross product of pref and partSol*/
		CompleteExtensionList accRest = acceptable(pref,partSol,framework);
		return createSolution(pref,accRest,framework);
	}

	@Override
	public CompleteExtensionList createSolution(AR pref,ExtensionList<CompleteExtension> accRest, AF af) {
		CompleteExtensionList ret = new CompleteExtensionList();
		if(pref.getArguments().isEmpty() && accRest.getExtensions().isEmpty()) {
			return ret;
		}
		for(CompleteExtension ce : accRest.getExtensions()) {
			AR tmp = new AR();
			tmp.addAll(pref);
			tmp.addAll(ce.getArguments());
			ret.add(new CompleteExtension(tmp,af));
		}
		return ret;
	}

	public CompleteExtensionList acceptable(AR pref,ArrayList<AR> partSol,AF framework) {
		CompleteExtensionList ret = new CompleteExtensionList();
		for(AR ar : partSol) {
			AR tmp = new AR(pref.getArguments());
			tmp.addAll(ar);
			if(framework.isAdmissibleSubset(tmp)) {
				ret.add(new CompleteExtension(tmp,framework));
			}
		}
		return ret;
	}

}
