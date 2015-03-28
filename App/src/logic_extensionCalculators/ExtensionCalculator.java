package logic_extensionCalculators;

import logic_basics.AF;
import logic_extensions.Extension;
import logic_extensions.ExtensionList;

public interface ExtensionCalculator<E extends Extension> { 
	
	public abstract ExtensionList<E> calculate(AF framework);
}
