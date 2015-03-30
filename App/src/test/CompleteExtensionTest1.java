package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import logic_basics.*;
import logic_extensionCalculators.CompleteExtensionCalculator;
import logic_extensions.*;

import org.junit.Before;
import org.junit.Test;

public class CompleteExtensionTest1 {

	ArrayList<Argument> l1;
	ArrayList<AttackRelation> l2;
	
	@Before
	public void setUp() {
		l1  = new ArrayList<Argument>();
		l2 = new ArrayList<AttackRelation>();	
	}
	
	@Test
	public void testCalculateTrueA2() {
		Argument a = new Argument("a","test a");
		
		AttackRelation r1 = new AttackRelation(a,a);
		
		l1.add(a);
		AR ar = new AR(l1);
		l2.add(r1);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		ArrayList<Argument> sol1 = new ArrayList<Argument>();
		
		CompleteExtensionCalculator cc1 = new CompleteExtensionCalculator();
		CompleteExtension ca = new CompleteExtension(new AR(sol1),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(ca)));
		CompleteExtensionList c1 = cc1.calculate(af);

		assertTrue("Passt nicht..",c1.equals(sol));		
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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		
		CompleteExtensionList p1 = pc1.calculate(af);
		
		assertFalse("Passt nicht..",p1 == null);		
	}
	
	@Test
	public void testCalculateFalseA2() {
		Argument a = new Argument("a","test a");
		
		l1.add(a);
		AR ar = new AR(l1);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>();
		
		CompleteExtension pa = new CompleteExtension(new AR(sol1),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pa)));
		CompleteExtensionList p1 = pc1.calculate(af);
		
		assertFalse("Passt nicht..",p1.equals(sol));		
	}
	
	
	@Test
	public void testCalculateTrueA1() {
		Argument a = new Argument("a","test a");
		
		l1.add(a);
		AR ar = new AR(l1);
		Att att = new Att(l2);
		
		AF af = new AF(ar,att);
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		
		CompleteExtension pa = new CompleteExtension(new AR(sol1),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pa)));
		CompleteExtensionList p1 = pc1.calculate(af);
		
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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		
		CompleteExtension pa = new CompleteExtension(new AR(sol1),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pa)));
		CompleteExtensionList p1 = pc1.calculate(af);
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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b));
		
		CompleteExtension pa = new CompleteExtension(new AR(sol1),af);
		CompleteExtension pb = new CompleteExtension(new AR(sol2),af);
		CompleteExtension pl = new CompleteExtension(new AR(),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pa,pb,pl)));
		CompleteExtensionList p1 = pc1.calculate(af);
				
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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b));
		
		CompleteExtension pa = new CompleteExtension(new AR(sol1),af);
		CompleteExtension pb = new CompleteExtension(new AR(sol2),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pa,pb)));
		CompleteExtensionList p1 = pc1.calculate(af);
		
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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		
		ArrayList<Argument> sol0 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a,d));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(a,c));
		ArrayList<Argument> sol3 = new ArrayList<Argument>(Arrays.asList(a,c,d));
		
		CompleteExtension pa = new CompleteExtension(new AR(sol0),af);
		CompleteExtension pb = new CompleteExtension(new AR(sol1),af);
		CompleteExtension pc = new CompleteExtension(new AR(sol2),af);
		CompleteExtension pd = new CompleteExtension(new AR(sol3),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pa,pb,pc,pd)));
		CompleteExtensionList p1 = pc1.calculate(af);
				
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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol0 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a,c));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(a,d));
		
		CompleteExtension pc = new CompleteExtension(new AR(sol1),af);
		CompleteExtension pd = new CompleteExtension(new AR(sol2),af);
		CompleteExtension pa = new CompleteExtension(new AR(sol0),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pc,pd,pa)));
		CompleteExtensionList p1 = pc1.calculate(af);
				
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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a,c));
		ArrayList<Argument> sol3 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(a,b));
		
		CompleteExtension pc = new CompleteExtension(new AR(sol1),af);
		CompleteExtension pb = new CompleteExtension(new AR(sol2),af);
		CompleteExtension pa = new CompleteExtension(new AR(sol3),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pc,pb,pa)));
		CompleteExtensionList p1 = pc1.calculate(af);

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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a,c));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(a));
		
		CompleteExtension pc = new CompleteExtension(new AR(sol1),af);
		CompleteExtension pa = new CompleteExtension(new AR(sol2),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pc,pa)));
		CompleteExtensionList p1 = pc1.calculate(af);
				
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
		
		CompleteExtensionCalculator pc0 = new CompleteExtensionCalculator();
		
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b));
		ArrayList<Argument> sol3 = new ArrayList<Argument>(Arrays.asList(c));
		ArrayList<Argument> sol4 = new ArrayList<Argument>(Arrays.asList(d));
		ArrayList<Argument> sol5 = new ArrayList<Argument>(Arrays.asList(a,c));
		ArrayList<Argument> sol6 = new ArrayList<Argument>(Arrays.asList(b,d));
		ArrayList<Argument> sol7 = new ArrayList<Argument>();
		
		CompleteExtension p1 = new CompleteExtension(new AR(sol1),af);
		CompleteExtension p2 = new CompleteExtension(new AR(sol2),af);
		CompleteExtension p3 = new CompleteExtension(new AR(sol3),af);
		CompleteExtension p4 = new CompleteExtension(new AR(sol4),af);
		CompleteExtension p5 = new CompleteExtension(new AR(sol5),af);
		CompleteExtension p6 = new CompleteExtension(new AR(sol6),af);
		CompleteExtension p7 = new CompleteExtension(new AR(sol7),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(p1,p2,p3,p4,p5,p6,p7)));
		CompleteExtensionList p0 = pc0.calculate(af);
			
		assertTrue("Passt nicht..",p0.equals(sol));		
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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol0 = new ArrayList<Argument>(Arrays.asList(d,b));
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(d,b,f));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(d,b,g));
		ArrayList<Argument> sol3 = new ArrayList<Argument>(Arrays.asList(d));
		
		CompleteExtension pa = new CompleteExtension(new AR(sol1),af);
		CompleteExtension pb = new CompleteExtension(new AR(sol2),af);
		CompleteExtension pdb = new CompleteExtension(new AR(sol3),af);
		CompleteExtension pd = new CompleteExtension(new AR(sol0),af);
		
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pa,pb,pdb,pd)));
		CompleteExtensionList p1 = pc1.calculate(af);
		
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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(a));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(b));
		ArrayList<Argument> sol3 = new ArrayList<Argument>(Arrays.asList(c));
		
		CompleteExtension pl = new CompleteExtension(new AR(),af);
		CompleteExtension pa = new CompleteExtension(new AR(sol1),af);
		CompleteExtension pb = new CompleteExtension(new AR(sol2),af);
		CompleteExtension pc = new CompleteExtension(new AR(sol3),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pa,pb,pc,pl)));
		CompleteExtensionList p1 = pc1.calculate(af);

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
		
		CompleteExtensionCalculator pc1 = new CompleteExtensionCalculator();
		ArrayList<Argument> sol1 = new ArrayList<Argument>(Arrays.asList(d));
		ArrayList<Argument> sol2 = new ArrayList<Argument>(Arrays.asList(d,a));
		ArrayList<Argument> sol3 = new ArrayList<Argument>(Arrays.asList(d,b));
		ArrayList<Argument> sol4 = new ArrayList<Argument>(Arrays.asList(d,c));
		
		CompleteExtension pa = new CompleteExtension(new AR(sol1),af);
		CompleteExtension pb = new CompleteExtension(new AR(sol2),af);
		CompleteExtension pc = new CompleteExtension(new AR(sol3),af);
		CompleteExtension pd = new CompleteExtension(new AR(sol4),af);
		CompleteExtensionList sol = new CompleteExtensionList(new ArrayList<CompleteExtension>(Arrays.asList(pa,pb,pc,pd)));
		CompleteExtensionList p1 = pc1.calculate(af);
		assertTrue("Passt nicht..",p1.equals(sol));		
	}
}
