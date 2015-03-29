package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;

public class AdmissibleExtensionCalculator extends ExtensionCalculator<AdmissibleExtension> {

	@Override
	public AdmissibleExtensionList calculate(AF framework) {
		ArrayList<AR> admRest = new ArrayList<AR>();
		ArrayList<AR> Rest = new ArrayList<AR>();
		AR rest = new AR();
		rest.addAll(framework.getAr());
		powerSet(0,new AR(),rest,Rest);
		for(AR ar : Rest) {
			if(framework.isAdmissibleSubset(ar)) {
				admRest.add(ar);
			}
		}
		return createSolution(null,admRest,framework);
	}

	@Override
	public AdmissibleExtensionList createSolution(AR pref, ArrayList<AR> admRest, AF af) {
		AdmissibleExtensionList ret = new AdmissibleExtensionList();
		for(AR ar : admRest) {
			AdmissibleExtension ae = new AdmissibleExtension(ar,af);
			ret.add(ae);
		}
		return ret;
	}

}
