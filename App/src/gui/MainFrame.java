package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exceptions.InvalidArgumentException;
import logic_basics.*;
import gui.GraphDisplayPanel.ColorTab;

public class MainFrame implements ActionListener,KeyListener,ChangeListener {

	private final JMenuBar menu = new JMenuBar();
	private final JMenuItem help1,help2,exmpl0,exmpl1,exmpl2,exmpl3,exmpl4;
	private JFrame frame;
	private ArgumentationFrameworkForm definitionForm;
	private ExtensionsForm extensionsForm;
	private StepByStepForm stepByStepForm;
	private JTabbedPane tabs;
	private JPanel extensionsTab,definitionTab,stepByStepTab;
	private GraphDisplayPanel displayPanel1,displayPanel2,displayPanel3;

	public MainFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080,720);
		frame.setTitle("KAFFEE");
		frame.setResizable(false);

		tabs = new JTabbedPane();

		/* menu bar */
		JMenu help = new JMenu("HELP");
		help.setMnemonic('h');
		help1 = new JMenuItem("Github - Project/Paper",'g');
		help2 = new JMenuItem("Contact",'c');
		JMenu exmpl = new JMenu("EXAMPLES");
		exmpl.setMnemonic('e');
		exmpl0 = new JMenuItem("Example 1.1",'1');
		exmpl1 = new JMenuItem("Example 1.4",'4');
		exmpl2 = new JMenuItem("Example 1.5",'5');
		exmpl3 = new JMenuItem("Example 1.6",'6');
		exmpl4 = new JMenuItem("Example StepByStep",'s');
		help.add(help1);
		help.add(help2);
		exmpl.add(exmpl0);
		exmpl.add(exmpl1);
		exmpl.add(exmpl2);
		exmpl.add(exmpl3);
		exmpl.add(exmpl4);
		menu.add(help);
		menu.add(exmpl);
		menu.setFocusable(false);

		/* listeners */
		help1.addActionListener(this);
		help2.addActionListener(this);
		exmpl0.addActionListener(this);
		exmpl1.addActionListener(this);
		exmpl2.addActionListener(this);
		exmpl3.addActionListener(this);
		exmpl4.addActionListener(this);
		help1.addKeyListener(this);
		help2.addKeyListener(this);
		exmpl0.addKeyListener(this);
		exmpl1.addKeyListener(this);
		exmpl2.addKeyListener(this);
		exmpl3.addKeyListener(this);
		exmpl4.addKeyListener(this);
		tabs.addChangeListener(this);

		/* tabbed Pane that holds the relevant part of the gui */
		JPanel definitionTab = new JPanel(new GridLayout(1,2));
		JPanel extensionTab = new JPanel(new GridLayout(1,2));
		JPanel stepByStepTab = new JPanel(new GridLayout(1,2));
		displayPanel1 = new GraphDisplayPanel(ColorTab.AFF);
		displayPanel2 = new GraphDisplayPanel(ColorTab.EF);
		displayPanel3 = new GraphDisplayPanel(ColorTab.SBSF);
		definitionForm = new ArgumentationFrameworkForm(displayPanel1);
		extensionsForm = new ExtensionsForm(definitionForm,displayPanel2);
		stepByStepForm = new StepByStepForm(extensionsForm,displayPanel3);
		tabs.addTab("Argumentation Framework",definitionTab);
		tabs.addTab("Extensions",extensionTab);
		tabs.addTab("Step By Step",stepByStepTab);
		tabs.getComponent(0).setName("definitionTab");
		tabs.getComponent(1).setName("extensionTab");
		tabs.getComponent(2).setName("stepByStepTab");

		displayPanel1.setBorder(BorderFactory.createLineBorder(new Color((float)0.7,(float)0.79,1),2));
		displayPanel2.setBorder(BorderFactory.createLineBorder(new Color((float)0.7,(float)0.79,1),2));
		displayPanel3.setBorder(BorderFactory.createLineBorder(new Color((float)0.7,(float)0.79,1),2));

		definitionTab.add(definitionForm);
		definitionTab.add(displayPanel1);
		extensionTab.add(extensionsForm);
		extensionTab.add(displayPanel2);
		stepByStepTab.add(stepByStepForm);
		stepByStepTab.add(displayPanel3);

		/* finishing up */
		frame.setJMenuBar(menu);
		frame.setContentPane(tabs);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == help1) {
			JOptionPane.showInternalMessageDialog(tabs,"https://github.com/e1025119/thesis","Github - Project/Paper",JOptionPane.PLAIN_MESSAGE);
		}
		else if(ae.getSource() == help2) {
			String mail = "e1025119@student.tuwien.ac.at";
			JOptionPane.showInternalMessageDialog(tabs,"If you have any questions, ideas etc.,"+"\n"+"make sure to mail "+mail,"Contact",JOptionPane.PLAIN_MESSAGE);
		}
		else if(ae.getSource() == exmpl0) {
			Argument s = new Argument("s","Superman is the best hero!");
			Argument b = new Argument("b","Batman is way cooler..");
			Argument a = new Argument("a","Only Aquaman rules over the seas.");
			Argument r = new Argument("r","Robin is Batman's sidekick.");
			AR ar = new AR();
			ar.add(s);
			ar.add(b);
			ar.add(a);
			ar.add(r);
			
			AttackRelation r1 = new AttackRelation(b,a);
			AttackRelation r2 = new AttackRelation(b,s);
			AttackRelation r3 = new AttackRelation(b,r);
			AttackRelation r4 = new AttackRelation(a,b);
			AttackRelation r5 = new AttackRelation(a,s);
			AttackRelation r6 = new AttackRelation(s,a);
			AttackRelation r7 = new AttackRelation(s,b);
			Att att = new Att();
			try {
				att.add(r1,ar);
				att.add(r2,ar);
				att.add(r3,ar);
				att.add(r4,ar);
				att.add(r5,ar);
				att.add(r6,ar);
				att.add(r7,ar);
			} catch(InvalidArgumentException iae) {
				//not gonna happen..
			}

			AF af = new AF(ar,att);
			definitionForm.update(af);
		}
		else if(ae.getSource() == exmpl1) {
			Argument s1 = new Argument("s1","Superman is the best hero!");
			Argument b1 = new Argument("b1","Batman is way cooler..");
			Argument s2 = new Argument("s2","Superman is stronger.");
			Argument b2 = new Argument("b2","Batman possesses genius-level intellect.");
			AR ar = new AR();
			ar.add(s1);
			ar.add(b1);
			ar.add(s2);
			ar.add(b2);

			AttackRelation r1 = new AttackRelation(b1,s1);
			AttackRelation r2 = new AttackRelation(s2,b1);
			AttackRelation r3 = new AttackRelation(b2,s2);
			Att att = new Att();
			try {
				att.add(r1,ar);
				att.add(r2,ar);
				att.add(r3,ar);
			} catch(InvalidArgumentException iae) {
				//not gonna happen..
			}

			AF af = new AF(ar,att);
			definitionForm.update(af);
		}
		else if(ae.getSource() == exmpl2) {
			Argument s = new Argument("s","Superman was born a hero.");
			Argument b = new Argument("b","Batman trained hard to become a hero.");
			AttackRelation r1 = new AttackRelation(s,b);
			AttackRelation r2 = new AttackRelation(b,s);
			AR ar = new AR();
			ar.add(s);
			ar.add(b);
			Att att = new Att();
			try {
				att.add(r1,ar);
				att.add(r2,ar);
			} catch(InvalidArgumentException iae) {
				//not gonna happen..
			}

			AF af = new AF(ar,att);
			definitionForm.update(af);
		}
		else if(ae.getSource() == exmpl3) {
			Argument b = new Argument("b","Batman is Clark Kent.");
			AttackRelation r1 = new AttackRelation(b,b);
			AR ar = new AR();
			ar.add(b);
			Att att = new Att();
			try {
				att.add(r1,ar);
			} catch(InvalidArgumentException iae) {
				//not gonna happen..
			}

			AF af = new AF(ar,att);
			definitionForm.update(af);
		}
		else if(ae.getSource() == exmpl4) {
			Argument a = new Argument("a","");
			Argument b = new Argument("b","");
			Argument c = new Argument("c","");
			Argument d = new Argument("d","");
			Argument e = new Argument("e","");
			Argument f = new Argument("f","");
			AR ar = new AR();
			ar.add(a);
			ar.add(b);
			ar.add(c);
			ar.add(d);
			ar.add(e);
			ar.add(f);

			AttackRelation r1 = new AttackRelation(b,a);
			AttackRelation r2 = new AttackRelation(a,f);
			AttackRelation r3 = new AttackRelation(f,e);
			AttackRelation r4 = new AttackRelation(e,d);
			AttackRelation r5 = new AttackRelation(d,c);
			AttackRelation r6 = new AttackRelation(c,b);
			AttackRelation r7 = new AttackRelation(b,e);
			AttackRelation r8 = new AttackRelation(f,c);
			AttackRelation r9 = new AttackRelation(d,a);
			
			Att att = new Att();
			try {
				att.add(r1,ar);
				att.add(r2,ar);
				att.add(r3,ar);
				att.add(r4,ar);
				att.add(r5,ar);
				att.add(r6,ar);
				att.add(r7,ar);
				att.add(r8,ar);
				att.add(r9,ar);
			} catch(InvalidArgumentException iae) {
				//not gonna happen..
			}

			AF af = new AF(ar,att);
			definitionForm.update(af);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			ActionEvent ae = new ActionEvent(e.getSource(),0,"");
			actionPerformed(ae);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		try {
			if(tabs.getSelectedComponent().getName().equals("extensionTab")) {
				extensionsForm.refresh();
			}
			else if(tabs.getSelectedComponent().getName().equals("stepByStepTab")) {
				stepByStepForm.refresh();
			}
		} catch(NullPointerException n) {
			//do nothing
		}
	}
}