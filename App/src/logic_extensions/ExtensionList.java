package logic_extensions;

import java.util.ArrayList;

public abstract class ExtensionList<E extends Extension> {

	protected ArrayList<E> extensions;

	public ExtensionList(ArrayList<E> extensions) {
		this.extensions = new ArrayList<E>();
		this.extensions.addAll(extensions);
	}

	public ExtensionList() {
		this.extensions = new ArrayList<E>();
	}

	public ArrayList<E> getExtensions() {
		return extensions;
	}

	public void setExtensions(ArrayList<E> extensions) {
		this.extensions = extensions;
	}

	public boolean equals(ExtensionList<?> el) {
		if(el == null) {
			return false;
		}
		else if(this.getExtensions().size() != el.getExtensions().size()) {
			return false;
		}
		for(E e : this.getExtensions()) {
			if(!el.contains(e)) {
				return false;
			}
		}
		return true;
	} 

	public boolean contains(Extension e) {
		if(e == null) {
			return false;
		}
		for(E e1 : this.extensions) {
			if(e1.equals(e)) {
				return true;
			}
		}
		return false;
	}

	public void add(E e) {
		if(e == null) {
			return;
		}
		if(!this.contains(e)) {
			this.getExtensions().add(e);
		}
	}

	public String toString() {
		return extensions.toString();
	}
}
