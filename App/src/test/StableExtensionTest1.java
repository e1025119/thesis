package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import logic_basics.*;
import logic_extensionCalculators.StableExtensionCalculator;
import logic_extensions.*;

import org.junit.Before;
import org.junit.Test;

public class StableExtensionTest1 {

	ArrayList<Argument> l1;
	ArrayList<AttackRelation> l2;
	
	@Before
	public void setUp() {
		l1  = new ArrayList<Argument>();
		l2 = new ArrayList<AttackRelation>();	
	}
	
	@Test
	public void testCalculateTrueA1() {
		Argument a = new Argument("a","test a");
		
		AttackRelation r1 = new AttackRelation(a,a);
		
		l1.add(a);
		AR ar = new AR(l1);
		l2.add(r1);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		StableExtensionCalculator sc1 = new StableExtensionCalculator();
		
		StableExtensionList s1 = sc1.calculate(af);
		assertTrue("Passt nicht..",s1 == null);		
	}
	
	@Test
	public void testCalculateTrueAB1() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(b,a);
		
		l1.add(a);
		l1.add(b);
		AR ar = new AR(l1);
		l2.add(r1);
		l2.add(r2);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);

		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b));
		
		StableExtensionCalculator sc1 = new StableExtensionCalculator();
		
		StableExtension pa = new StableExtension(new AR(sol1),af);
		StableExtension pb = new StableExtension(new AR(sol2),af);
		StableExtensionList sol = new StableExtensionList(new ArrayList<StableExtension>(Arrays.asList(pa,pb)));
		StableExtensionList s1 = sc1.calculate(af);

		assertTrue("Passt nicht..",s1.equals(sol));		
	}
	
	@Test
	public void testCalculateTrueABCD1() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		Argument d = new Argument("d","test d");
		
		AttackRelation r1 = new AttackRelation(d,c);
		AttackRelation r2 = new AttackRelation(c,b);
		AttackRelation r3 = new AttackRelation(b,a);
		
		l1.add(a);
		l1.add(b);
		l1.add(c);
		l1.add(d);
		AR ar = new AR(l1);
		l2.add(r1);
		l2.add(r2);
		l2.add(r3);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);

		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b,d));
		
		StableExtensionCalculator sc1 = new StableExtensionCalculator();
		
		StableExtension pb = new StableExtension(new AR(sol2),af);
		StableExtensionList sol = new StableExtensionList(new ArrayList<StableExtension>(Arrays.asList(pb)));
		StableExtensionList s1 = sc1.calculate(af);
		System.out.println("s1: "+s1+", sol: "+sol);
		assertTrue("Passt nicht..",s1.equals(sol));		
	}
	
	@Test
	public void testCalculateTrueABCDE1() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		Argument d = new Argument("d","test d");
		Argument e = new Argument("e","test e");
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(b,c);
		AttackRelation r3 = new AttackRelation(c,d);
		AttackRelation r4 = new AttackRelation(d,e);
		AttackRelation r5 = new AttackRelation(e,a);
		
		l1.add(a);
		l1.add(b);
		l1.add(c);
		l1.add(d);
		l1.add(e);
		AR ar = new AR(l1);
		l2.add(r1);
		l2.add(r2);
		l2.add(r3);
		l2.add(r4);
		l2.add(r5);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);

		StableExtensionCalculator sc1 = new StableExtensionCalculator();
		
		StableExtensionList s1 = sc1.calculate(af);
		System.out.println("s1: "+s1);
		assertTrue("Passt nicht..",s1 == null);		
	}
}
