package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic_basics.AF;

@SuppressWarnings("serial")
public class ArgumentationFrameworkForm extends JPanel {

	private AF framework;
	private JTextField newArgumentRefField,newArgumentTextField,newA1Field,newA2Field;
	private JLabel newArgumentRefLabel,newArgumentTextLabel,newA1Label,newA2Label;
	private JLabel argumentsLabel,attacksLabel;
	private JButton submitArgument,submitAttack;
	private JTextArea argumentsArea,attacksArea;
	
	public ArgumentationFrameworkForm() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.newArgumentRefField = new JTextField();
		this.newArgumentTextField = new JTextField();
		this.newA1Field = new JTextField();
		this.newA2Field = new JTextField();
		this.argumentsArea = new JTextArea();
		this.attacksArea = new JTextArea();
		this.newArgumentRefLabel = new JLabel("Ref:");
		this.newArgumentTextLabel = new JLabel("Text:");
		this.newA1Label = new JLabel("A1:");
		this.newA2Label = new JLabel("A2:");
		this.argumentsArea.setEditable(false);
		this.attacksArea.setEditable(false);
		this.argumentsLabel = new JLabel("AR:");
		this.attacksLabel = new JLabel("Att:");
		this.submitArgument = new JButton("submit argument");
		this.submitAttack = new JButton("submit attack");
		
		/* FlowLayout, that holds both BoxLayouts (arguments label/area, attacks label/area) */
		JPanel textAreas = new JPanel();
		JPanel argumentsPanel = new JPanel(),attacksPanel = new JPanel();
		argumentsPanel.setLayout(new BoxLayout(argumentsPanel,BoxLayout.Y_AXIS));
		attacksPanel.setLayout(new BoxLayout(attacksPanel,BoxLayout.Y_AXIS));
		argumentsPanel.add(argumentsLabel,Box.LEFT_ALIGNMENT);
		argumentsPanel.add(argumentsArea,Box.LEFT_ALIGNMENT);
		attacksPanel.add(attacksLabel,Box.LEFT_ALIGNMENT);
		attacksPanel.add(attacksArea,Box.LEFT_ALIGNMENT);
		textAreas.setLayout(new FlowLayout());
		textAreas.add(argumentsPanel);
		textAreas.add(Box.createHorizontalStrut(10));
		textAreas.add(attacksPanel);
		
		argumentsArea.setPreferredSize(new Dimension(240,360));
		attacksArea.setPreferredSize(new Dimension(240,360));		

		/* BoxLayouts for each Label/TextField pair */
		JPanel argumentsRefPanel = new JPanel();
		argumentsRefPanel.setLayout(new BoxLayout(argumentsRefPanel,BoxLayout.Y_AXIS));
		argumentsRefPanel.add(newArgumentRefLabel,Box.LEFT_ALIGNMENT);
		argumentsRefPanel.add(newArgumentRefField,Box.LEFT_ALIGNMENT);
		
		JPanel argumentsTextPanel = new JPanel();
		argumentsTextPanel.setLayout(new BoxLayout(argumentsTextPanel,BoxLayout.Y_AXIS));
		argumentsTextPanel.add(newArgumentTextLabel,Box.LEFT_ALIGNMENT);
		argumentsTextPanel.add(newArgumentTextField,Box.LEFT_ALIGNMENT);
		
		JPanel a1Panel = new JPanel();
		a1Panel.setLayout(new BoxLayout(a1Panel,BoxLayout.Y_AXIS));
		a1Panel.add(newA1Label,Box.LEFT_ALIGNMENT);
		a1Panel.add(newA1Field,Box.LEFT_ALIGNMENT);
		
		JPanel a2Panel = new JPanel();
		a2Panel.setLayout(new BoxLayout(a2Panel,BoxLayout.Y_AXIS));
		a2Panel.add(newA2Label,Box.LEFT_ALIGNMENT);
		a2Panel.add(newA2Field,Box.LEFT_ALIGNMENT);
		
		newArgumentRefField.setPreferredSize(new Dimension(50,25));
		newArgumentTextField.setPreferredSize(new Dimension(150,25));
		newA1Field.setPreferredSize(new Dimension(100,25));
		newA2Field.setPreferredSize(new Dimension(100,25));
		
		/* 2 BoxLayouts: FlowLayout (2 pairs) and Button each -> goes into final FlowLayout */
		JPanel argumentFlow = new JPanel(new FlowLayout());
		argumentFlow.add(argumentsRefPanel);
		argumentFlow.add(argumentsTextPanel);
		
		JPanel attackFlow = new JPanel(new FlowLayout());
		attackFlow.add(a1Panel);
		attackFlow.add(a2Panel);
		
		JPanel attackBox = new JPanel(),argumentBox = new JPanel();
		argumentBox.setLayout(new BoxLayout(argumentBox,BoxLayout.Y_AXIS));
		attackBox.setLayout(new BoxLayout(attackBox,BoxLayout.Y_AXIS));
		argumentBox.add(argumentFlow,Box.LEFT_ALIGNMENT);
		argumentBox.add(submitArgument,Box.LEFT_ALIGNMENT);
		attackBox.add(attackFlow,Box.LEFT_ALIGNMENT);
		attackBox.add(submitAttack,Box.LEFT_ALIGNMENT);
		
		/* final flowLayout for the textFields general area.. */
		JPanel textFields = new JPanel(new FlowLayout());
		textFields.add(argumentBox);
		textFields.add(attackBox);
		
		this.add(textAreas);
		this.add(Box.createVerticalStrut(10));
		this.add(textFields);
		this.add(Box.createVerticalStrut(170));
	}
}
