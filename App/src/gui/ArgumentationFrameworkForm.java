package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import exceptions.*;
import logic_basics.*;

@SuppressWarnings("serial")
public class ArgumentationFrameworkForm extends JPanel implements ActionListener {

	private AF framework;
	private JTextField newArgumentRefField = new JTextField(),newArgumentTextField = new JTextField(),newA1Field = new JTextField(),newA2Field = new JTextField();
	private JLabel newArgumentRefLabel = new JLabel("Ref:"),newArgumentTextLabel = new JLabel("Text:"),newA1Label = new JLabel("A1"),newA2Label = new JLabel("A2");
	private JLabel argumentsLabel = new JLabel("AR:"),attacksLabel = new JLabel("Att:");
	private JLabel errorLabel = new JLabel();
	private JButton submitArgument = new JButton("submit argument"),submitAttack = new JButton("submit attack");
	private JTextArea argumentsArea = new JTextArea(),attacksArea = new JTextArea();

	public ArgumentationFrameworkForm() {
		framework = new AF(new AR(),new Att());
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		argumentsArea.setEditable(false);
		attacksArea.setEditable(false);
		errorLabel.setForeground(Color.RED);

		/* action listener */
		submitArgument.addActionListener(this);
		submitAttack.addActionListener(this);

		JScrollPane scrollArguments = new JScrollPane(argumentsArea);
		scrollArguments.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollArguments.setPreferredSize(new Dimension(230,360));
		JScrollPane scrollAttacks = new JScrollPane(attacksArea);
		scrollAttacks.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollAttacks.setPreferredSize(new Dimension(230,360));
		
		/* FlowLayout, that holds both BoxLayouts (arguments label/area, attacks label/area) */
		JPanel textAreas = new JPanel();
		JPanel argumentsPanel = new JPanel(),attacksPanel = new JPanel();
		argumentsPanel.setLayout(new BoxLayout(argumentsPanel,BoxLayout.Y_AXIS));
		attacksPanel.setLayout(new BoxLayout(attacksPanel,BoxLayout.Y_AXIS));
		argumentsPanel.add(argumentsLabel,Box.LEFT_ALIGNMENT);
		argumentsPanel.add(scrollArguments,Box.LEFT_ALIGNMENT);
		attacksPanel.add(attacksLabel,Box.LEFT_ALIGNMENT);
		attacksPanel.add(scrollAttacks,Box.LEFT_ALIGNMENT);
		textAreas.setLayout(new FlowLayout());
		textAreas.add(argumentsPanel);
		textAreas.add(Box.createHorizontalStrut(10));
		textAreas.add(attacksPanel);

		//argumentsArea.setPreferredSize(new Dimension(230,50));
		//attacksArea.setPreferredSize(new Dimension(230,360));		

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
		argumentBox.add(argumentFlow);
		argumentBox.add(submitArgument);
		attackBox.add(attackFlow);
		attackBox.add(submitAttack);

		/* final flowLayout for the textFields general area.. */
		JPanel textFields = new JPanel(new FlowLayout());
		textFields.add(argumentBox);
		textFields.add(attackBox);
		
		/* errorLabel test */
		JPanel errorBox = new JPanel(new BorderLayout());
		errorBox.add(errorLabel,BorderLayout.PAGE_END);
		
		this.add(textAreas);
		this.add(Box.createVerticalStrut(10));
		this.add(textFields);
		this.add(Box.createVerticalStrut(10));
		this.add(errorBox);
	}

	public AF getAF() {
		return framework;
	}

	public void setAF(AF framework) {
		this.framework = framework;
	}

	//TODO Reset der Argument/Att listen bzw. löschen ausgewählter argumente!
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == submitArgument) {
			String ref = newArgumentRefField.getText();
			String text = newArgumentTextField.getText();
			if(!ref.equals("") && !text.equals("")) {
				Argument tmp = new Argument(ref,text);
				try {
					framework.getAr().add(tmp);
					argumentsArea.setText(this.framework.getAr().toString());
					errorLabel.setVisible(false);
				} catch (DuplicateArgumentException e) {
					errorLabel.setText(e.getMessage());
					errorLabel.setVisible(true);
				}
			}
		}
		else if(ae.getSource() == submitAttack) {
			String a1 = newA1Field.getText();
			String a2 = newA2Field.getText();
			if(!a1.equals("") && !a2.equals("")) {
				Argument arg1 = framework.getAr().get(a1),arg2 = framework.getAr().get(a2);
				AttackRelation tmp = new AttackRelation(arg1,arg2);
				try {
					framework.getAtt().add(tmp,framework.getAr());
					attacksArea.setText(framework.getAtt().toString());
					errorLabel.setVisible(false);
				} catch (InvalidArgumentException e) {
					errorLabel.setText(e.getMessage());
					errorLabel.setVisible(true);
				}
			}
		}
	}
}
