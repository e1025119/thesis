package logic_extensionCalculators;

import java.util.ArrayList;

import exceptions.DuplicateArgumentException;
import logic_basics.*;
import logic_extensions.*;

public class StableExtensionCalculator extends ExtensionCalculator<StableExtension> {

	@Override
	public StableExtensionList calculate(AF framework) {
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
				try {
					rest.add(a);
				} catch (DuplicateArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		ArrayList<AR> partSol = new ArrayList<AR>(); 
		powerSet(0,new AR(),rest,partSol);
		
		StableExtensionList stableRest = stable(partSol,framework);
		return createSolution(pref,stableRest,framework);
	}
	
	/** 
	 * @param pref deterministic part of the solution, this is the same for all extensions
	 * 
	 * @param maxRest these are the partial solutions from the non-deterministic part of the algorithm.
	 * 			every single one gets combined with the {@code pref}.
	 * 
	 * @brief this method adds every partial solution to the deterministic solution and
	 * 			by doing so, creates the full solution. If both {@code pref} and {@code stableRest} are empty,
	 * 			then there is no solution (= null).
	 */
	@Override
	public StableExtensionList createSolution(AR pref,ExtensionList<StableExtension> stableRest,AF af) {
		StableExtensionList ret = new StableExtensionList();
		if(pref.getArguments().isEmpty() && stableRest.getExtensions().isEmpty()) {
			return null;
		} else if(stableRest.getExtensions().isEmpty()) {
			ret.add(new StableExtension(pref,af));
			return ret;
		}
		for(StableExtension se : stableRest.getExtensions()) {
			AR tmp = new AR();
			tmp.addAll(pref);
			tmp.addAll(se.getArguments());
			ret.add(new StableExtension(tmp,af));
		}
		return ret;
	}
	
	public StableExtensionList stable(ArrayList<AR> args,AF framework) {
		StableExtensionList ret = new StableExtensionList();
		for(AR ar : args) {
			if(ar.isConflictFree(framework.getAtt()) && framework.isStableSubset(ar)) {
				ret.add(new StableExtension(ar,framework));
			}
		}
		return ret;
	}
}