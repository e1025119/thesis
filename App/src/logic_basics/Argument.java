package logic_basics;

public class Argument {

	private String text,ref;
	private boolean paintEF = false,paintAFF = false,paintSBSF_green = false,paintSBSF_blue = false,paintSBSF_orange = false,paintSBSF_cyan = false;
	
	public Argument(String ref,String text) {
		this.ref = ref;
		this.text = text;
	}

	public boolean isPaintSBSF_cyan() {
		return paintSBSF_cyan;
	}

	public void setPaintSBSF_cyan(boolean paintSBSF_cyan) {
		this.paintSBSF_cyan = paintSBSF_cyan;
	}

	public boolean isPaintSBSF_orange() {
		return paintSBSF_orange;
	}

	public void setPaintSBSF_orange(boolean paintSBSF_orange) {
		this.paintSBSF_orange = paintSBSF_orange;
	}

	public boolean isPaintSBSF_green() {
		return paintSBSF_green;
	}

	public void setPaintSBSF_green(boolean paintSBSF_green) {
		this.paintSBSF_green = paintSBSF_green;
	}

	public boolean isPaintSBSF_blue() {
		return paintSBSF_blue;
	}

	public void setPaintSBSF_blue(boolean paintSBSF_blue) {
		this.paintSBSF_blue = paintSBSF_blue;
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