package run;

import java.util.ArrayList;
import logic_basics.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Argument b = new Argument("b","batman is way cooler!");
		Argument s = new Argument("s","superman is the best hero.");
		
		AttackRelation a1 = new AttackRelation(b,s);
		ArrayList<AttackRelation> atts = new ArrayList<AttackRelation>();
		atts.add(a1);
		Att att1 = new Att(atts);
		
		ArrayList<Argument> arg = new ArrayList<Argument>();
		arg.add(b);
		arg.add(s);
		AR ar1 = new AR(arg);
		
		AF af1 = new AF(ar1,att1);
		System.out.println(af1.toString());
	}
}