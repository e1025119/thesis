package logic_extensionCalculators;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;

public class PreferredExtensionCalculator implements ExtensionCalculator {

	private AF framework;
	
	public PreferredExtensionCalculator(AF framework) {
		this.framework = framework;
	}
	
	@Override
	public Extension calculate(AF framework) {
		ArrayList<Argument> ar = new ArrayList<Argument>();
		
		/* calculate preferred extension (maximal admissible set of arg) */
		
		
		return new PreferredExtension(ar,framework);
	}
}
