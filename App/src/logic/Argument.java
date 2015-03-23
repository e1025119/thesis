package logic;

public class Argument {

	private String text,ref;
	
	public Argument(String ref,String text) {
		this.ref = ref;
		this.text = text;
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
	
	public String toString() {
		return ref+" (\""+text+"\")";
	}
}
