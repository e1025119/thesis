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

		ArrayList<Argument> pref = new ArrayList<Argument>(),conflicting = new ArrayList<Argument>();
		ArrayList<Argument> tmp1 = new ArrayList<Argument>(),tmp2 = new ArrayList<Argument>();
		int count = 0;

		/* deterministic part */
		pref.addAll(framework.getUnattacked(tmp1));
		do {
			count = pref.size();
			for(Argument a : pref) {
				tmp1 = framework.getAtt().getAttacked(a);
				for(Argument b : tmp1) {
					if(!conflicting.contains(b)) {
						conflicting.add(b);
					}
				}
				tmp2.addAll(framework.getUnattacked(tmp1));
			}

			for(Argument a : tmp2) {
				if(!pref.contains(a)) {
					pref.add(a);
				}
			}
		} while(count != pref.size());
	
		/* non-deterministic part */
		ArrayList<Argument> rest = new ArrayList<Argument>();
		ArrayList<ArrayList<Argument>> restSolution = new ArrayList<ArrayList<Argument>>();
		for(Argument a : framework.getAr().getArguments()) {
			if(!conflicting.contains(a) && !pref.contains(a)) {
				rest.add(a);
			}
		}
		for(int i=0; i<rest.size(); i++){
			for(Argument a: rest) {
				a.setStatus(0);
			}
			rest.get(i).setUsedInCalculation(1);
			restSolution.add(calculateRest(new AR(new ArrayList<Argument>()),new AR(rest),framework));
		}

		/* create all valid solutions by permutating the remaining (status = 1) arguments */
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
	public ArrayList<PreferredExtension> createSolution(ArrayList<Argument> arg, ArrayList<ArrayList<Argument>> listArg,AF af) {
		ArrayList<PreferredExtension> ret = new ArrayList<PreferredExtension>();
		for(ArrayList<Argument> a : listArg) {
			ArrayList<Argument> tmp = new ArrayList<Argument>();
			tmp.addAll(arg);
			tmp.addAll(a);
			ret.add(new PreferredExtension(new AR(tmp),af));
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
	public ArrayList<Argument> calculateRest(AR ar1,AR ar2,AF af) {
		ArrayList<Argument> ret = new ArrayList<Argument>();

		for(int i=0; i<ar2.getArguments().size(); i++) {
			Argument a = ar2.getArguments().get(i);
			if(a.getUsedInCalculation() == 1) {
				if(a.isDefendable(ar1,ar2,af)) {
					a.setStatus(1);
					ar1.getArguments().add(a);
				} else {
					a.setStatus(-1);
				}
				ret = calculateRestRecursive(ar1,ar2,af);
			}
			a.setUsedInCalculation(0);
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
	 * @brief this method calculates a partial solution to the preferred extension for every different argument
	 * 			in the ar2 set as a starting point. This way, all possible solutions get brute-forced.
	 * 			Every argument is checked for its acceptability to the status quo solution (ar1) and can then be denied or accepted.
	 * 			It then is shifted to ar1 and a new recursion is called.
	 */
	public ArrayList<Argument> calculateRestRecursive(AR ar1,AR ar2,AF af) {
		ArrayList<Argument> ret = new ArrayList<Argument>();

		for(int i=0; i<ar2.getArguments().size(); i++) {
			Argument a = ar2.getArguments().get(i);
			if(a.getStatus() == 0) {
				if(a.isDefendable(ar1,ar2,af)) {
					ar1.getArguments().add(a);
					a.setStatus(1);
					calculateRestRecursive(ar1,ar2,af);
				}
				else{
					a.setStatus(-1);
				}
			}
		}
		ret = ar1.getArguments();
		return ret;
	}


}






















