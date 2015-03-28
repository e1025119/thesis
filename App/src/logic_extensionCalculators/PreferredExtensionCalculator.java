package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;

public class PreferredExtensionCalculator implements ExtensionCalculator<PreferredExtension> {

	@Override
	public PreferredExtensionList calculate(AF framework) {
		AR pref = new AR(),conflicting = new AR();

		/* deterministic part */
		pref.addAll(framework.getUntouched());
		conflicting.addAll(framework.getSelfies());
		conflicting.addAll(framework.getIndefendables());

		/* non-deterministic part */
		AR rest = new AR();
		for(Argument a : framework.getAr().getArguments()) {
			if(!conflicting.contains(a) && !pref.contains(a)) {
				rest.add(a);
			}
		}

		ArrayList<AR> partSol = new ArrayList<AR>();
		powerRest(0,new AR(),rest,partSol);

		/* create all valid solutions by building the cross product of pref and partSol*/
		ArrayList<AR> maxRest = maxAdm(partSol,framework);
		return createSolution(pref,maxRest,framework);
	}

	public PreferredExtensionList createSolution(AR pref,ArrayList<AR> maxRest,AF af) {
		PreferredExtensionList ret = new PreferredExtensionList();
		for(AR ar : maxRest) {
			AR tmp = new AR();
			tmp.addAll(pref);
			tmp.addAll(ar);
			ret.add(new PreferredExtension(tmp,af));
		}
		System.out.println("RET: "+ret);
		return ret;
	}
	
	/** 
	 * @brief this method calculates the power set of the remaining, undecided arguments for later evaluation. 
	 */
	public void powerRest(int index,AR ar1,AR ar2,ArrayList<AR> sol) {
		if(index < ar2.getArguments().size()){
			Argument a = ar2.getArguments().get(index);
			AR yes = new AR(), no = new AR();
			yes.addAll(ar1);
			yes.add(a);
			no.addAll(ar1);
			powerRest(index+1,yes,ar2,sol);
			powerRest(index+1,no,ar2,sol);
		} else {
			sol.add(ar1);
		}
	}

	/** 
	 * @brief this method checks the power set for maximal admissible sets.
	 * */
	public ArrayList<AR> maxAdm(ArrayList<AR> args,AF framework) {
		ArrayList<AR> ret = new ArrayList<AR>();
		for(AR ar : args) {
			if(framework.isAdmissibleSubset(ar)) {
				ret.add(ar);
			}
		}
		int maxSize = 0;
		for(AR ar : ret) {
			if(ar.getArguments().size() > maxSize) {
				maxSize = ar.getArguments().size();
			}
		}
		ArrayList<AR> tmp = new ArrayList<AR>();
		for(AR ar : ret) {
			if(ar.getArguments().size() == maxSize) {
				tmp.add(ar);
			}
		}
		return tmp;
	}
	

}






















