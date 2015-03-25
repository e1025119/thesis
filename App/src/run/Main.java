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
		Argument f = new Argument("f","");
		
		ArrayList<Argument> al = new ArrayList<Argument>();
		al.add(a);
		al.add(b);
		al.add(c);
		al.add(d);
		al.add(e);
		al.add(f);
		AR ar = new AR(al);
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(a,c);
		AttackRelation r3 = new AttackRelation(b,e);
		AttackRelation r4 = new AttackRelation(c,e);
		AttackRelation r5 = new AttackRelation(e,f);
		AttackRelation r6 = new AttackRelation(f,e);
		AttackRelation r7 = new AttackRelation(b,d);
		AttackRelation r8 = new AttackRelation(c,d);
		
		ArrayList<AttackRelation> rl1 = new ArrayList<AttackRelation>();
		rl1.add(r1);
		rl1.add(r2);
		rl1.add(r3);
		rl1.add(r4);
		rl1.add(r5);
		rl1.add(r6);
		rl1.add(r7);
		rl1.add(r8);
		Att att = new Att(rl1);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		PreferredExtension ep1 = pc1.calculate(af);
		System.out.println(ep1);
	}
}