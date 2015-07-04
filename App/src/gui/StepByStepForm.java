package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import logic_basics.AF;
import logic_basics.AR;
import logic_basics.Argument;
import logic_extensions.AdmissibleExtension;
import logic_extensions.Extension;

@SuppressWarnings("serial")
public class StepByStepForm extends JPanel implements ActionListener,KeyListener,ItemListener {

	private ExtensionsForm ext;
	private Extension extension;
	private AF framework;
	private GraphDisplayPanel dp;
	private Component infoVert = Box.createVerticalStrut(300),infoHoriz = Box.createHorizontalStrut(10);
	private JTextArea extensionArea;
	private JComboBox<String> argumentDropDown = new JComboBox<String>();
	private JButton next = new JButton(),startSBS = new JButton("start \"Step by Step\"");
	private JLabel infoLabel = new JLabel("<html><font color=red>To use this function, please select one of the calculated admissible extensions"
			+ " in the tab \"Extensions\".</font></html>");

	/* list for StepByStep algorithm */
	private AR arguments,attackers;
	private int cntSteps = 0,cntAtt = 0,cntArg = 0;

	public StepByStepForm(ExtensionsForm ext,GraphDisplayPanel dp) {
		this.ext = ext;
		this.dp = dp;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		/* listeners */
		next.addActionListener(this);
		next.addKeyListener(this);
		startSBS.addActionListener(this);
		startSBS.addKeyListener(this);
		argumentDropDown.addItemListener(this);

		/* no extension selected */
		this.add(infoVert);
		this.add(infoLabel);
		this.add(infoHoriz);
		infoVert.setVisible(false);
		infoLabel.setVisible(false);
		infoHoriz.setVisible(false);

		/* normal layout */
		JLabel extensionLabel = new JLabel("Chosen Extension:");
		extensionArea = new JTextArea();
		extensionArea.setEditable(false);
		JPanel extensionBox = new JPanel();
		JPanel extensionFlow = new JPanel(new FlowLayout());
		JScrollPane extensionScroll = new JScrollPane(extensionArea);
		extensionScroll.setPreferredSize(new Dimension(500,200));
		extensionBox.setLayout(new BorderLayout());
		extensionBox.add(extensionLabel,BorderLayout.NORTH);
		extensionBox.add(Box.createVerticalGlue());
		extensionBox.add(extensionScroll,BorderLayout.WEST);
		extensionFlow.add(extensionBox);
		extensionFlow.add(Box.createHorizontalStrut(100));

		JLabel argumentLabel = new JLabel("Select Argument:");
		JPanel argumentBox1 = new JPanel(new BorderLayout());
		JPanel argumentFlow = new JPanel(new FlowLayout());
		JPanel argumentBox2 = new JPanel(new BorderLayout());
		argumentBox1.add(argumentLabel,BorderLayout.NORTH);
		argumentDropDown.setPreferredSize(new Dimension(150,20));
		argumentDropDown.setEditable(false);
		argumentBox1.add(argumentDropDown,BorderLayout.WEST);
		next.setText("\u25B6");
		next.setPreferredSize(new Dimension(25,25));
		argumentBox2.add(startSBS,BorderLayout.NORTH);
		argumentBox2.add(next,BorderLayout.CENTER);
		argumentFlow.add(argumentBox1);
		argumentFlow.add(Box.createHorizontalStrut(100));
		argumentFlow.add(argumentBox2);
		argumentFlow.add(Box.createHorizontalStrut(100));

		this.add(extensionFlow);
		this.add(argumentFlow);
		this.add(Box.createVerticalStrut(300));
	}

	/** 
	 * @brief this method is the first method that gets called every time the state in MainFrame is changed 
	 * 			because StepByStepTab was selected. Therefore it is valid to set framework/extension here and not in constructor.
	 */
	public void refresh() {
		ext.refresh();
		framework = ext.getFramework();
		extension = ext.getExtensionList().getSelectedValue();
		if(extension == null || !(extension instanceof AdmissibleExtension)) {
			for(Component c : this.getComponents()) {
				c.setVisible(false);
			}
			infoVert.setVisible(true);
			infoLabel.setVisible(true);
			infoHoriz.setVisible(true);
		}
		else {
			dp.setGraph(framework);
			dp.revalidate();
			for(Component c : this.getComponents()) {
				c.setVisible(true);
			}
			DefaultComboBoxModel<String> argumentModel = new DefaultComboBoxModel<String>();
			argumentModel.addElement("all");
			for(Argument a : extension.getArguments().getArguments()) {
				argumentModel.addElement(a.getRef());
			}
			argumentDropDown.setModel(argumentModel);
			extensionArea.setText(extension.toString());

			infoVert.setVisible(false);
			infoLabel.setVisible(false);
			infoHoriz.setVisible(false);
		}
	}

	public void initStepByStepAll() {
		cntArg = 0;
		cntAtt = 0;
		cntSteps = 0;
		framework.getAr().setPaintSbsfAllFalse();
		arguments = new AR();
		attackers = new AR();
		for(Argument a : extension.getArguments().getArguments()) {
			a.setPaintSBSF_green(true);
			arguments.add(a);
		}
		dp.setGraph(framework);
		dp.revalidate();
	}

	public void initStepByStep(Argument chosen) {
		cntSteps = 0;
		cntAtt = 0;
		framework.getAr().setPaintSbsfAllFalse();
		attackers = new AR();
		chosen.setPaintSBSF_blue(true);
		attackers.addAll(framework.getAtt().getAttacker(chosen));
		dp.setGraph(framework);
		dp.revalidate();
	}

	public void resetStepByStep() {
		cntSteps = 0;
		cntAtt = 0;
		arguments = new AR();
		attackers = new AR();
		framework.getAr().setPaintSbsfAllFalse();
		dp.setGraph(framework);
		dp.revalidate();
	}

	/** 
	 * @param all represents the mode that is used by StepByStep at the moment:
	 * 				false -> specific argument mode: one argument is selected and defended against all attackers (if possible).
	 * 				true  -> all arguments mode: every argument in the admissible extension is defended if possible. 
	 * @brief this method lets the user take the next step in the StepByStep visualization of admissible extension calculation.
	 * 			depending on counters and lists, this method takes each (or a specified, single) argument and defends it against all
	 * 			attackers.
	 * */
	public void nextStep(boolean all) throws NullPointerException {
		/* specific mode */
		if(!all && cntAtt < attackers.size()) {
			switch(cntSteps % 2){
			case 0:
				if(cntAtt != 0) {
					for(Argument a : extension.getArguments().getArguments()) {
						if(a.isPaintSBSF_cyan()) {
							a.setPaintSBSF_cyan(false);
						}
					}
					attackers.getArguments().get(cntAtt-1).setPaintSBSF_orange(false);
				}
				attackers.getArguments().get(cntAtt).setPaintSBSF_orange(true);
				dp.setGraph(framework);
				dp.revalidate();
				break;
			case 1:
				for(Argument a : extension.getArguments().getArguments()) {
					if(a.isPaintSBSF_cyan()) {
						a.setPaintSBSF_cyan(false);
					}
					if(framework.getAtt().getAttacker(attackers.getArguments().get(cntAtt)).contains(a)) {
						a.setPaintSBSF_cyan(true);
					}
				}
				cntAtt++;
				dp.setGraph(framework);
				dp.revalidate();
				break;
			default:
				//shouldn't happen
			}
			cntSteps++;
		}
		/* all arguments mode */
		else if(all && cntArg < arguments.size()) {
			Argument chosen = arguments.getArguments().get(cntArg);
			attackers = new AR();
			attackers.addAll(framework.getAtt().getAttacker(chosen));

			if(cntAtt >= attackers.size() && attackers.size() != 0) {
				cntAtt = 0;
				cntSteps = 0;
				cntArg++;
				for(Argument a : attackers.getArguments()) {
					a.setPaintSBSF_orange(false);
				}
				for(Argument a : arguments.getArguments()) {
					if(a.equals(chosen)) {
						a.setPaintSBSF_green(false);
						a.setPaintSBSF_blue(true);
						a.setPaintSBSF_cyan(false);
					}
					else {
						a.setPaintSBSF_blue(false);
						a.setPaintSBSF_green(true);
						a.setPaintSBSF_cyan(false);
					}
				}
				nextStep(true);
				return;
			}
			else if(cntAtt < attackers.size() && attackers.size() != 0) {
				switch(cntSteps % 2){
				case 0:
					for(Argument a : arguments.getArguments()) {
						if(!a.equals(chosen)) {
							a.setPaintSBSF_cyan(false);
							a.setPaintSBSF_green(true);
							a.setPaintSBSF_blue(false);						
						}
						else if(a.equals(chosen)) {
							a.setPaintSBSF_cyan(false);
							a.setPaintSBSF_green(false);
							a.setPaintSBSF_blue(true);
						}
					}
					if(cntAtt != 0) {
						attackers.getArguments().get(cntAtt-1).setPaintSBSF_orange(false);
					}
					attackers.getArguments().get(cntAtt).setPaintSBSF_orange(true);
					dp.setGraph(framework);
					dp.revalidate();
					break;
				case 1:
					for(Argument a : extension.getArguments().getArguments()) {
						if(framework.getAtt().getAttacker(attackers.getArguments().get(cntAtt)).contains(a) && !a.equals(chosen)) {
							a.setPaintSBSF_cyan(true);
							a.setPaintSBSF_green(false);
						}
						else if(framework.getAtt().getAttacker(attackers.getArguments().get(cntAtt)).contains(a) && a.equals(chosen)) {
							a.setPaintSBSF_cyan(true);
							a.setPaintSBSF_blue(false);
						}
					}
					cntAtt++;
					dp.setGraph(framework);
					dp.revalidate();
					break;
				default:
					//shouldn't happen
				}
				cntSteps++;
			}
			else if(attackers.size() == 0) {
				for(Argument a : arguments.getArguments()) {
					if(a.equals(chosen)) {
						a.setPaintSBSF_green(false);
						a.setPaintSBSF_blue(true);
						a.setPaintSBSF_cyan(false);
					}
					else {
						a.setPaintSBSF_blue(false);
						a.setPaintSBSF_green(true);
						a.setPaintSBSF_cyan(false);
					}
				}
				dp.setGraph(framework);
				dp.revalidate();
				cntArg++;
			}
		}
		else {
			resetStepByStep();
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
	public void keyReleased(KeyEvent arg0) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//do nothing
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tmp = (String)argumentDropDown.getSelectedItem();
		if(e.getSource() == next) {
			try {
				if(tmp.equals("all")) {
					nextStep(true);
				}
				else {
					nextStep(false);
				}
			} catch(NullPointerException n) {
				//do nothing
			}
		}
		else if(e.getSource() == startSBS) {
			if(tmp.equals("all")) {
				initStepByStepAll();
			}
			else {
				initStepByStep(extension.getArguments().get(tmp));
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == argumentDropDown) {
			resetStepByStep();
		}
	}
}
