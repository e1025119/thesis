package run;

import java.util.ArrayList;

import logic_basics.*;
import logic_extensions.*;
import logic_extensionCalculators.*;

public class Main {

	public static void main(String[] args) {
		Argument a = new Argument("a","");
		Argument b = new Argument("b","");
		Argument c = new Argument("c","");
		Argument d = new Argument("d","");
		Argument e = new Argument("e","");
		
		ArrayList<Argument> al = new ArrayList<Argument>();
		al.add(a);
		al.add(b);
		al.add(c);
		al.add(d);
		al.add(e);
		
		AR ar = new AR(al);
		
		AttackRelation r1 = new AttackRelation(a,b);
		//AttackRelation r2 = new AttackRelation(a,c);
		AttackRelation r3 = new AttackRelation(c,d);
		AttackRelation r4 = new AttackRelation(d,c);
		
		ArrayList<AttackRelation> rl1 = new ArrayList<AttackRelation>();
		rl1.add(r1);
		//rl1.add(r2);
		rl1.add(r3);
		rl1.add(r4);
		Att att = new Att(rl1);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<PreferredExtension> ep1 = pc1.calculate(af);
		System.out.println(ep1);
	}
}