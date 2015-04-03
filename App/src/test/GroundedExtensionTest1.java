package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import logic_basics.*;
import logic_extensionCalculators.GroundedExtensionCalculator;
import logic_extensions.*;

import org.junit.Before;
import org.junit.Test;

public class GroundedExtensionTest1 {

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
		
		GroundedExtensionCalculator pc1 = new GroundedExtensionCalculator();
		
		GroundedExtensionList p1 = pc1.calculate(af);

		assertFalse("Passt nicht..",p1 == null);		
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
		
		GroundedExtensionCalculator pc1 = new GroundedExtensionCalculator();
		
		GroundedExtensionList sol = new GroundedExtensionList(new ArrayList<GroundedExtension>());
		GroundedExtensionList p1 = pc1.calculate(af);

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
		
		GroundedExtensionCalculator pc1 = new GroundedExtensionCalculator();
		ArrayList<Argument> sol3 = new ArrayList<Argument>(Arrays.asList(a));
		
		GroundedExtension pa = new GroundedExtension(new AR(sol3),af);
		GroundedExtensionList sol = new GroundedExtensionList(new ArrayList<GroundedExtension>(Arrays.asList(pa)));
		GroundedExtensionList p1 = pc1.calculate(af);

		assertTrue("Passt nicht..",p1.equals(sol));		
	}

}
