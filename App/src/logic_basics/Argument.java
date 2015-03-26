package logic_basics;

public class Argument {

	private String text,ref;
	private int status; // 1 = chosen, 0 = unmarked, -1 = kicked
	private int usedInCalculation;

	public int getUsedInCalculation() {
		return usedInCalculation;
	}

	public void setUsedInCalculation(int usedInCalculation) {
		this.usedInCalculation = usedInCalculation;
	}

	public Argument(String ref,String text) {
		this.ref = ref;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
		if(ref.equals(a.getRef()) && text.equals(a.getText())) {
			return true;
		}
		return false;
	}

	/** 
	 * @brief this method checks, if a single argument is defendable by a given set
	 * 			and the attack relations of the framework. 
	 */
	public boolean isDefendable(AR ar1,AR ar2,AF af) {
		boolean ret = false;
		AttackRelation itself = new AttackRelation(this,this);
		if(af.getAtt().getAttacks().contains(itself)) {
			return false;
		}
		for(Argument a : ar2.getArguments()) {
			for(AttackRelation rel : af.getAtt().getAttacks()) {
				/* a attacks one of the already confirmed arguments */
				if(ar1.getArguments().contains(rel.getA2()) && a.equals(rel.getA1())) {
					return false;
				}
				else if(rel.getA2().equals(this) && rel.getA1().equals(a)) {
					if(af.getAtt().getAttacker(a).contains(this)) {
						ret = true;
					}
					for(Argument b : ar1.getArguments()) {
						if(af.getAtt().getAttacker(a).contains(b)) {
							ret = true;
						}	
					}
				}
			}
		}
		return ret;
	}

	public String toString() {
		return ref+" (\""+text+"\")";
	}
	public String toStringDev() {
		return ref+" (\""+text+"\")"+" Status: "+status+", UsedInCalc: "+usedInCalculation;
	}
}
