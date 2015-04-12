package logic_basics;

public class Argument {

	private String text,ref;
	private boolean paintEF = false,paintAFF = false;
	
	public Argument(String ref,String text) {
		this.ref = ref;
		this.text = text;
	}

	public boolean isPaintEF() {
		return paintEF;
	}

	public void setPaintEF(boolean paintEF) {
		this.paintEF = paintEF;
	}

	public boolean isPaintAFF() {
		return paintAFF;
	}

	public void setPaintAFF(boolean paintAFF) {
		this.paintAFF = paintAFF;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public boolean equals(Argument a) {
		if(this.ref.equals(a.getRef())) {
			return true;
		}
		return false;
	}

	public String toString() {
		return ref+" (\""+text+"\")";
	}
}
