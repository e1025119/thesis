package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;

public class StableExtensionCalculator implements ExtensionCalculator<StableExtension> {

	@Override
	public StableExtensionList calculate(AF framework) {
		//alle die nur angreifen/alleine stehen sind fix drinnen
		AR pref = new AR(), conflicting = new AR();
		
		/* deterministic part */
		pref.addAll(framework.getUntouched());
		pref.addAll(framework.getUnattacked());
		conflicting.addAll(framework.getIndefendables());
		conflicting.addAll(framework.getSelfies());
		
		/* non-deterministic part */
		ArrayList<AR> partSol = new ArrayList<AR>(); 
		
		
		return null; //einfach createSolution returnen..
	}
}
