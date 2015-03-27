package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;

public class PreferredExtensionCalculator implements ExtensionCalculator {

	public PreferredExtensionCalculator() {
	}

	@Override
	public ArrayList<PreferredExtension> calculate(AF framework) {
		ArrayList<PreferredExtension> ret = new ArrayList<PreferredExtension>();
		AR pref = new AR(new ArrayList<Argument>()),conflicting = new AR(new ArrayList<Argument>());

		/* deterministic part */
		pref.addAll(framework.getUntouched());
		conflicting.addAll(framework.getSelfies());
		conflicting.addAll(framework.getIndefendables());
		//TODO ??
		
		/* non-deterministic part */
		AR rest = new AR(new ArrayList<Argument>());
		ArrayList<AR> restSolution = new ArrayList<AR>();

		for(Argument a : framework.getAr().getArguments()) {
			if(!conflicting.contains(a) && !pref.contains(a)) {
				rest.add(a);
			}
		}
		for(int i=0; i<rest.getArguments().size(); i++){
			rest.getArguments().get(i).setUsedInCalculation(1);
			restSolution.add(calculateRest(new AR(new ArrayList<Argument>()),rest,framework));
		}

		/* create all valid solutions by permuting the remaining (status = 1) arguments */
		ret = createSolution(pref,restSolution,framework);
		return ret;
	}

	/** 
	 * @param arg the deterministic part of the solution.
	 * 
	 * @param listArg the different brute-forced parts of the solution.
	 * 
	 * @brief creates a new PreferredExtension for each partial solution in listArg by adding arg.
	 * */
	public ArrayList<PreferredExtension> createSolution(AR arg, ArrayList<AR> listArg,AF af) {
		ArrayList<PreferredExtension> ret = new ArrayList<PreferredExtension>();
		if(listArg.isEmpty()) {
			ret.add(new PreferredExtension(arg,af));
		} else {
			for(AR a : listArg) {
				AR tmp = new AR(new ArrayList<Argument>());
				tmp.addAll(arg);
				tmp.addAll(a);
				ret.add(new PreferredExtension(tmp,af));
			}
		}
		return ret;
	}

	/** 
	 * @param ar1 this set of arguments comes in empty and gets filled up, the deeper the recursion goes.
	 * 			in the end, this is the partial solution.
	 * 
	 * @param ar2 this is the starting set of arguments. gets smaller as the recursion goes deeper.
	 * 
	 * @param framework framework we are working with, contains all attack relations!
	 * 
	 * @brief this method is an entry point for the recursive calculateRest method. Only defines the starting point.
	 */
	public AR calculateRest(AR ar1,AR ar2,AF af) {
		ArrayList<Argument> ret = new ArrayList<Argument>();

		for(int i=0; i<ar2.getArguments().size(); i++) {
			Argument a = ar2.getArguments().get(i);
			if(a.getUsedInCalculation() == 1) {
				if(a.isDefendable(ar1,ar2,af)) {
					ar1.add(a);
					ar2.remove(a);
				} else {
					//TODO nur entfernen wenn a wirklich was "anstellt", nicht nur entfernen weil es nicht defendable is.
					ar2.remove(a);
				}
				ret = calculateRestRecursive(ar1,ar2,af);
			}
			a.setUsedInCalculation(0);
		}
		return new AR(ret);
	}

	/**
	 * @param ar1 this set of arguments comes in empty and gets filled up, the deeper the recursion goes.
	 * 			in the end, this is the partial solution.
	 * 
	 * @param ar2 this is the starting set of arguments. gets smaller as the recursion goes deeper.
	 * 
	 * @param framework framework we are working with, contains all attack relations!
	 * 
	 * @brief this method calculates a partial solution to the preferred extension for every different argument
	 * 			in the ar2 set as a starting point. This way, all possible solutions get brute-forced.
	 * 			Every argument is checked for its acceptability to the status quo solution (ar1) and can then be denied or accepted.
	 * 			It then is shifted to ar1 and a new recursion is called.
	 */
	public ArrayList<Argument> calculateRestRecursive(AR ar1,AR ar2,AF af) {
		ArrayList<Argument> ret = new ArrayList<Argument>();

		for(int i=0; i<ar2.getArguments().size(); i++) {
			Argument a = ar2.getArguments().get(i);
			if(a.isDefendable(ar1,ar2,af)) {
				ar1.add(a);
				ar2.remove(a);
				calculateRestRecursive(ar1,ar2,af);
			}
			else{
				ar2.remove(a);
			}
		}
		ret = ar1.getArguments();
		return ret;
	}
	
}






















