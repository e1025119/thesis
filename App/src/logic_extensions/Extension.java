package logic_extensions;

import java.util.ArrayList;

import logic_basics.AF;
import logic_basics.AR;
import logic_basics.Argument;

public abstract class Extension {

	private AR arguments;
	private AF framework;
	public enum ExtensionTypes{Admissible,Preferred,Stable,Grounded,Complete}
	
	public Extension(AR arguments,AF framework) {
		this.arguments = arguments;
		this.framework = framework;
	}
	
	public AF getFramework() {
		return framework;
	}

	public void setFramework(AF framework) {
		this.framework = framework;
	}

	public void setArguments(AR arguments) {
		this.arguments = arguments;
	}

	public AR getArguments() {
		return arguments;
	}

	public void setArguments(ArrayList<Argument> arguments) {
		this.arguments = new AR(arguments);
	}

	public boolean equals(Extension e) {
		if(this.arguments.equals(e.arguments) && this.framework.equals(framework)) {
			return true;
		}
		return false;
	}
	
	public int size() {
		return this.arguments.size();
	}
	
	public String toString() {
		return arguments.toString();
	}
	
}
