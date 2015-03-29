package logic_extensionCalculators;

import java.util.ArrayList;

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
				rest.add(a);
			}
		}

		ArrayList<AR> partSol = new ArrayList<AR>(); 
		powerSet(0,new AR(),rest,partSol);
		
		ArrayList<AR> stableRest = stable(partSol,framework);
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
	public StableExtensionList createSolution(AR pref,ArrayList<AR> stableRest,AF af) {
		StableExtensionList ret = new StableExtensionList();
		if(pref.getArguments().isEmpty() && stableRest.isEmpty()) {
			return null;
		}
		for(AR ar : stableRest) {
			AR tmp = new AR();
			tmp.addAll(pref);
			tmp.addAll(ar);
			ret.add(new StableExtension(tmp,af));
		}
		return ret;
	}
	
	public ArrayList<AR> stable(ArrayList<AR> args,AF framework) {
		ArrayList<AR> ret = new ArrayList<AR>();
		for(AR ar : args) {
			if(ar.isConflictFree(framework.getAtt()) && framework.isStableSubset(ar)) {
				ret.add(ar);
			}
		}
		return ret;
	}
}
