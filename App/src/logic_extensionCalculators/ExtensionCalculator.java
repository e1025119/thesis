package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.AF;
import logic_basics.AR;
import logic_basics.Argument;
import logic_extensions.Extension;
import logic_extensions.ExtensionList;

public abstract class ExtensionCalculator<E extends Extension> { 
	
	public abstract ExtensionList<E> calculate(AF framework);
	public abstract ExtensionList<E> createSolution(AR pref,ExtensionList<E> rest,AF af);
	
	/** 
	 * @brief this method calculates the power set of the remaining, undecided arguments for later evaluation. 
	 */
	public void powerSet(int index,AR ar1,AR ar2,ArrayList<AR> sol) {
		if(index < ar2.getArguments().size()){
			Argument a = ar2.getArguments().get(index);
			AR yes = new AR(), no = new AR();
			yes.addAll(ar1);
			yes.add(a);
			no.addAll(ar1);
			powerSet(index+1,yes,ar2,sol);
			powerSet(index+1,no,ar2,sol);
		} else {
			sol.add(ar1);
		}
	}

}
