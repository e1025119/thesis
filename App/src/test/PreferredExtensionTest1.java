package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import logic_basics.*;
import logic_extensionCalculators.PreferredExtensionCalculator;
import logic_extensions.PreferredExtension;
import logic_extensions.PreferredExtensionList;

public class PreferredExtensionTest1 {

	ArrayList<Argument> l1,l3;
	ArrayList<AttackRelation> l2;
	
	@Before
	public void setUp() {
		l1  = new ArrayList<Argument>();
		l3  = new ArrayList<Argument>();
		l2 = new ArrayList<AttackRelation>();	
	}
	
	@Test
	public void testCalculateFalseA1() {
		Argument a = new Argument("a","test a");
		
		AttackRelation r1 = new AttackRelation(a,a);
		
		l1.add(a);
		AR ar = new AR(l1);
		l2.add(r1);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		
		assertFalse("Passt nicht..",p1.equals(sol));		
	}
	
	@Test
	public void testCalculateFalseA2() {
		Argument a = new Argument("a","test a");
		
		l1.add(a);
		AR ar = new AR(l1);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>();
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		
		assertFalse("Passt nicht..",p1.equals(sol));		
	}
	
	
	@Test
	public void testCalculateTrueA1() {
		Argument a = new Argument("a","test a");
		
		l1.add(a);
		AR ar = new AR(l1);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		
		assertTrue("Passt nicht..",p1.equals(sol));		
	}
	
	@Test
	public void testCalculateTrueAB1() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		
		AttackRelation r1 = new AttackRelation(a,b);
		
		l1.add(a);
		l1.add(b);
		AR ar = new AR(l1);
		l2.add(r1);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		System.out.println("AB1: "+p1);
		assertTrue("Passt nicht..",p1.equals(sol));		
	}
	
	@Test
	public void testCalculateTrueAB2() {
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
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b));
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtension pb = new PreferredExtension(new AR(sol2),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa,pb)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		
		assertTrue("Passt nicht..",p1.equals(sol));		
	}
	@Test
	public void testCalculateFalseAB1() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		
		AttackRelation r1 = new AttackRelation(a,b);
		
		l1.add(a);
		l1.add(b);
		AR ar = new AR(l1);
		l2.add(r1);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b));
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtension pb = new PreferredExtension(new AR(sol2),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa,pb)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		
		assertFalse("Passt nicht..",p1.equals(sol));		
	}

	@Test
	public void testCalculateTrueABCD1() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		Argument d = new Argument("d","test d");
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(b,c);
		AttackRelation r3 = new AttackRelation(b,d);
		
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
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a,c,d));
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		
		assertTrue("Passt nicht..",p1.equals(sol));		
	}

	@Test
	public void testCalculateTrueABCD2() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		Argument d = new Argument("d","test d");
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(b,c);
		AttackRelation r3 = new AttackRelation(c,d);
		AttackRelation r4 = new AttackRelation(d,c);
		
		l1.add(a);
		l1.add(b);
		l1.add(c);
		l1.add(d);
		AR ar = new AR(l1);
		l2.add(r1);
		l2.add(r2);
		l2.add(r3);
		l2.add(r4);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a,c));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(a,d));
		
		PreferredExtension pc = new PreferredExtension(new AR(sol1),af);
		PreferredExtension pd = new PreferredExtension(new AR(sol2),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pc,pd)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		
		assertTrue("Passt nicht..",p1.equals(sol));		
	}
	
	@Test
	public void testCalculateTrueABC1() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(b,c);
		AttackRelation r3 = new AttackRelation(c,b);
		
		l1.add(a);
		l1.add(b);
		l1.add(c);
		AR ar = new AR(l1);
		l2.add(r1);
		l2.add(r2);
		l2.add(r3);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a,c));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(a,b));
		
		PreferredExtension pc = new PreferredExtension(new AR(sol1),af);
		PreferredExtension pd = new PreferredExtension(new AR(sol2),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pc,pd)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		
		System.out.println("TEST: "+p1);
		assertTrue("Passt nicht..",p1.equals(sol));		
	}
	
	@Test
	public void testCalculateTrueABCD3() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		Argument d = new Argument("d","test d");
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(b,a);
		AttackRelation r3 = new AttackRelation(c,b);
		AttackRelation r4 = new AttackRelation(b,c);
		AttackRelation r5 = new AttackRelation(a,d);
		AttackRelation r6 = new AttackRelation(d,a);
		AttackRelation r7 = new AttackRelation(c,d);
		AttackRelation r8 = new AttackRelation(d,c);
		
		l1.add(a);
		l1.add(b);
		l1.add(c);
		l1.add(d);
		AR ar = new AR(l1);
		l2.add(r1);
		l2.add(r2);
		l2.add(r3);
		l2.add(r4);
		l2.add(r5);
		l2.add(r6);
		l2.add(r7);
		l2.add(r8);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a,c));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b,b));
		
		PreferredExtension pc = new PreferredExtension(new AR(sol1),af);
		PreferredExtension pd = new PreferredExtension(new AR(sol2),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pc,pd)));
		PreferredExtensionList p1 = new PreferredExtensionList(pc1.calculate(af));
		
		//System.out.println("TEST: "+p1);
		assertTrue("Passt nicht..",p1.equals(sol));		
	}
}







