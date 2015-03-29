package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;

public class PreferredExtensionCalculator extends ExtensionCalculator<PreferredExtension> {

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

	/**
	 * @param pref deterministic part of the solution, this is the same for all extensions
	 * 
	 * @param maxRest these are the partial solutions from the non-deterministic part of the algorithm.
	 * 			every single one gets combined with the {@code pref}.
	 * 
	 * @brief this method adds every partial solution to the deterministic solution and
	 * 			by doing so, creates the full solution. If both parameters {@code pref} and {@code maxRest}
	 * 			are empty, then the solution defaults to the empty set.
	 */
	public PreferredExtensionList createSolution(AR pref,ArrayList<AR> maxRest,AF af) {
		PreferredExtensionList ret = new PreferredExtensionList();
		if(pref.getArguments().isEmpty() && maxRest.isEmpty()) {
			return ret;
		}
		for(AR ar : maxRest) {
			AR tmp = new AR();
			tmp.addAll(pref);
			tmp.addAll(ar);
			ret.add(new PreferredExtension(tmp,af));
		}
		return ret;
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






















