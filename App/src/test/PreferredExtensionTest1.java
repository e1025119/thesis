package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import logic_basics.*;
import logic_extensionCalculators.PreferredExtensionCalculator;
import logic_extensions.*;

public class PreferredExtensionTest1 {

	ArrayList<Argument> l1;
	ArrayList<AttackRelation> l2;
	
	@Before
	public void setUp() {
		l1  = new ArrayList<Argument>();
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
		PreferredExtensionList p1 = pc1.calculate(af);
		
		assertFalse("Passt nicht..",p1 == null);		
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
		PreferredExtensionList p1 = pc1.calculate(af);
		
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
		PreferredExtensionList p1 = pc1.calculate(af);
		
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
		PreferredExtensionList p1 = pc1.calculate(af);
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
		PreferredExtensionList p1 = pc1.calculate(af);
		
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
		PreferredExtensionList p1 = pc1.calculate(af);
		
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
		PreferredExtensionList p1 = pc1.calculate(af);
		
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
		PreferredExtensionList p1 = pc1.calculate(af);
		
		assertTrue("Passt nicht..",p1.equals(sol));		
	}
	
	@Test
	public void testCalculateTrueABC1() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		
		AttackRelation r2 = new AttackRelation(b,c);
		AttackRelation r3 = new AttackRelation(c,b);
		
		l1.add(a);
		l1.add(b);
		l1.add(c);
		AR ar = new AR(l1);
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
		PreferredExtensionList p1 = pc1.calculate(af);

		assertTrue("Passt nicht..",p1.equals(sol));		
	}
	
	@Test
	public void testCalculateTrueABC2() {
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
		
		PreferredExtension pc = new PreferredExtension(new AR(sol1),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pc)));
		PreferredExtensionList p1 = pc1.calculate(af);
		
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
		PreferredExtensionList p1 = pc1.calculate(af);
		
		assertTrue("Passt nicht..",p1.equals(sol));		
	}
	
	@Test
	public void testCalculateFalseABCDEFG1() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		Argument d = new Argument("d","test d");
		Argument e = new Argument("e","test e");
		Argument f = new Argument("f","test f");
		Argument g = new Argument("g","test g");
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(b,a);
		AttackRelation r3 = new AttackRelation(c,c);
		AttackRelation r4 = new AttackRelation(c,a);
		AttackRelation r5 = new AttackRelation(b,e);
		AttackRelation r6 = new AttackRelation(a,f);
		AttackRelation r7 = new AttackRelation(e,f);
		AttackRelation r8 = new AttackRelation(f,g);
		AttackRelation r9 = new AttackRelation(g,f);
		AttackRelation r10 = new AttackRelation(e,g);
		
		l1.add(a);
		l1.add(b);
		l1.add(c);
		l1.add(d);
		l1.add(e);
		l1.add(f);
		l1.add(g);
		AR ar = new AR(l1);
		l2.add(r1);
		l2.add(r2);
		l2.add(r3);
		l2.add(r4);
		l2.add(r5);
		l2.add(r6);
		l2.add(r7);
		l2.add(r8);
		l2.add(r9);
		l2.add(r10);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(d,b,f));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(d,b,g));
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtension pb = new PreferredExtension(new AR(sol2),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa,pb)));
		PreferredExtensionList p1 = pc1.calculate(af);

		assertTrue("Passt nicht..",p1.equals(sol));		
	}

	@Test
	public void testCalculateTrueABC3() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(b,a);
		AttackRelation r3 = new AttackRelation(c,a);
		AttackRelation r4 = new AttackRelation(a,c);
		AttackRelation r5 = new AttackRelation(c,b);
		AttackRelation r6 = new AttackRelation(b,c);
		
		l1.add(a);
		l1.add(b);
		l1.add(c);
		AR ar = new AR(l1);
		l2.add(r1);
		l2.add(r2);
		l2.add(r3);
		l2.add(r4);
		l2.add(r5);
		l2.add(r6);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b));
		ArrayList<Argument> sol3 = new ArrayList<Argument>(Arrays.asList(c));
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtension pb = new PreferredExtension(new AR(sol2),af);
		PreferredExtension pc = new PreferredExtension(new AR(sol3),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa,pb,pc)));
		PreferredExtensionList p1 = pc1.calculate(af);

		assertTrue("Passt nicht..",p1.equals(sol));		
	}
	
	@Test
	public void testCalculateTrueABCD4() {
		Argument a = new Argument("a","test a");
		Argument b = new Argument("b","test b");
		Argument c = new Argument("c","test c");
		Argument d = new Argument("d","test d");
		
		AttackRelation r1 = new AttackRelation(a,b);
		AttackRelation r2 = new AttackRelation(b,a);
		AttackRelation r3 = new AttackRelation(c,a);
		AttackRelation r4 = new AttackRelation(a,c);
		AttackRelation r7 = new AttackRelation(b,c);
		AttackRelation r8 = new AttackRelation(c,b);
		
		l1.add(a);
		l1.add(b);
		l1.add(c);
		l1.add(d);
		AR ar = new AR(l1);
		l2.add(r1);
		l2.add(r2);
		l2.add(r3);
		l2.add(r4);
		l2.add(r7);
		l2.add(r8);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a,d));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(d,b));
		ArrayList<Argument> sol3 = new ArrayList<Argument>(Arrays.asList(d,c));
		
		PreferredExtension pa = new PreferredExtension(new AR(sol1),af);
		PreferredExtension pc = new PreferredExtension(new AR(sol3),af);
		PreferredExtension pb = new PreferredExtension(new AR(sol2),af);
		PreferredExtensionList sol = new PreferredExtensionList(new ArrayList<PreferredExtension>(Arrays.asList(pa,pb,pc)));
		PreferredExtensionList p1 = pc1.calculate(af);

		assertTrue("Passt nicht..",p1.equals(sol));		
	}	
}







