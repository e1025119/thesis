package logic;

public class AF {

	private AR ar;
	private Att att;
	
	public AF(AR ar,Att att) {
		this.ar = ar;
		this.att = att;
	}

	public AR getAr() {
		return ar;
	}

	public void setAr(AR ar) {
		this.ar = ar;
	}

	public Att getAtt() {
		return att;
	}

	public void setAtt(Att att) {
		this.att = att;
	}
	
	public String toString() {
		String ret = "";
		ret += "----------AF----------"+"\n";
		ret += ar.toString()+"\n";
		ret += att.toString()+"\n";
		ret += "----------------------"+"\n";
		return ret;
	}
}
