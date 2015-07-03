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
		powerSet(0,new AR(),rest,partSol);

		/* create all valid solutions by building the cross product of pref and partSol*/
		PreferredExtensionList maxRest = maxAdm(partSol,framework);
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
	public PreferredExtensionList createSolution(AR pref,ExtensionList<PreferredExtension> maxRest,AF af) {
		PreferredExtensionList ret = new PreferredExtensionList();
		if(pref.getArguments().isEmpty() && maxRest.getExtensions().isEmpty()) {
			return ret;
		}
		for(Extension e : maxRest.getExtensions()) {
			AR tmp = new AR();
			tmp.addAll(pref);
			tmp.addAll(e.getArguments());
			ret.add(new PreferredExtension(tmp,af));
		}
		return ret;
	}

	/** 
	 * @brief this method checks the power set for maximal admissible sets.
	 * */
	//TODO Error lies here: AR={a,b,c} Att={(a,b),(a,c),(c,a)} => prefExt = {{b,c},{a}}!!
	
	public PreferredExtensionList maxAdm(ArrayList<AR> args,AF framework) {
		PreferredExtensionList ret = new PreferredExtensionList();
		for(AR ar : args) {
			if(framework.isAdmissibleSubset(ar)) {
				ret.add(new PreferredExtension(ar,framework));
			}
		}
		int maxSize = 0;
		for(PreferredExtension pe : ret.getExtensions()) {
			if(pe.size() > maxSize) {
				maxSize = pe.size();
			}
		}
		PreferredExtensionList tmp = new PreferredExtensionList();
		for(PreferredExtension pe : ret.getExtensions()) {
			if(pe.size() == maxSize) {
				tmp.add(pe);
			}
		}
		return tmp;
	}


}