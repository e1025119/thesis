package logic_extensions;

import java.util.ArrayList;

public class PreferredExtensionList implements ExtensionList {

	private ArrayList<PreferredExtension> extensions;
	
	public PreferredExtensionList(ArrayList<PreferredExtension> extensions) {
		this.extensions = extensions;
	}
	
	public ArrayList<PreferredExtension> getExtensions() {
		return extensions;
	}

	public void setExtensions(ArrayList<PreferredExtension> extensions) {
		this.extensions = extensions;
	}
	
	public boolean equals(PreferredExtensionList p) {
		if(this.getExtensions().size() != p.getExtensions().size()) {
			return false;
		}
		for(PreferredExtension pe : this.getExtensions()) {
			if(!p.contains(pe)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean contains(PreferredExtension pe) {
		for(PreferredExtension p : this.extensions) {
			if(p.equals(pe)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return extensions.toString();
	}
}
