package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;

public class AdmissibleExtensionCalculator extends ExtensionCalculator<AdmissibleExtension> {

	@Override
	public AdmissibleExtensionList calculate(AF framework) {
		AdmissibleExtensionList admRest = new AdmissibleExtensionList();
		ArrayList<AR> Rest = new ArrayList<AR>();
		AR rest = new AR();
		rest.addAll(framework.getAr());
		powerSet(0,new AR(),rest,Rest);
		for(AR ar : Rest) {
			if(framework.isAdmissibleSubset(ar)) {
				admRest.add(new AdmissibleExtension(ar,framework));
			}
		}
		return createSolution(null,admRest,framework);
	}

	@Override
	public AdmissibleExtensionList createSolution(AR pref, ExtensionList<AdmissibleExtension> admRest, AF af) {
		AdmissibleExtensionList ret = new AdmissibleExtensionList();
		for(AdmissibleExtension ae : admRest.getExtensions()) {
			ret.add(ae);
		}
		return ret;
	}

}
