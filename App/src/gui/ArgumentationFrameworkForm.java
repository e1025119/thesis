package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exceptions.*;
import logic_basics.*;

@SuppressWarnings("serial")
public class ArgumentationFrameworkForm extends JPanel implements ActionListener,KeyListener,ListSelectionListener {

	private AF framework;
	private GraphDisplayPanel dp;
	private JTextField newArgumentRefField = new JTextField(),newArgumentTextField = new JTextField(),newA1Field = new JTextField(),newA2Field = new JTextField();
	private JLabel newArgumentRefLabel = new JLabel("Ref:"),newArgumentTextLabel = new JLabel("Text:"),newA1Label = new JLabel("A1"),newA2Label = new JLabel("A2");
	private JLabel argumentsLabel = new JLabel("AR:"),attacksLabel = new JLabel("Att:");
	private JLabel errorLabel = new JLabel();
	private JButton submitArgument = new JButton("submit argument"),submitAttack = new JButton("submit attack"),clearAll = new JButton("clear all"),deleteSelection = new JButton("delete selection");
	private DefaultListModel<Argument> argumentModel = new DefaultListModel<Argument>();
	private DefaultListModel<AttackRelation> attackModel = new DefaultListModel<AttackRelation>();
	private JList<Argument> argumentsList = new JList<Argument>(argumentModel);
	private JList<AttackRelation> attacksList = new JList<AttackRelation>(attackModel);
	private JPanel clearBox = new JPanel(new FlowLayout());
	
	public ArgumentationFrameworkForm(GraphDisplayPanel dp) {
		this.dp = dp;
		framework = new AF(new AR(),new Att());
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		errorLabel.setForeground(Color.RED);
		clearAll.setVisible(true);
		deleteSelection.setVisible(false);

		/* action listener */
		submitArgument.addActionListener(this);
		submitAttack.addActionListener(this);
		submitArgument.addKeyListener(this);
		submitAttack.addKeyListener(this);
		argumentsList.addListSelectionListener(this);
		attacksList.addListSelectionListener(this);
		clearAll.addActionListener(this);
		clearAll.addKeyListener(this);
		deleteSelection.addActionListener(this);
		deleteSelection.addKeyListener(this);

		/* ScrollPanes for both Lists */
		JScrollPane scrollArguments = new JScrollPane(argumentsList);
		scrollArguments.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollArguments.setPreferredSize(new Dimension(230,360));
		JScrollPane scrollAttacks = new JScrollPane(attacksList);
		scrollAttacks.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollAttacks.setPreferredSize(new Dimension(230,360));

		/* FlowLayout, that holds both BoxLayouts (arguments label/list, attacks label/list) */
		JPanel textAreas = new JPanel();
		JPanel argumentsPanel = new JPanel(),attacksPanel = new JPanel();
		argumentsPanel.setLayout(new BoxLayout(argumentsPanel,BoxLayout.Y_AXIS));
		attacksPanel.setLayout(new BoxLayout(attacksPanel,BoxLayout.Y_AXIS));
		argumentsPanel.add(argumentsLabel);
		argumentsPanel.add(scrollArguments);
		attacksPanel.add(attacksLabel);
		attacksPanel.add(scrollAttacks);
		argumentsLabel.setAlignmentX(LEFT_ALIGNMENT);
		scrollArguments.setAlignmentX(LEFT_ALIGNMENT);
		attacksLabel.setAlignmentX(LEFT_ALIGNMENT);
		scrollAttacks.setAlignmentX(LEFT_ALIGNMENT);
		textAreas.setLayout(new FlowLayout());
		textAreas.add(argumentsPanel);
		textAreas.add(Box.createHorizontalStrut(10));
		textAreas.add(attacksPanel);

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

		/* clearAll/deleteSelection button */
		clearBox.add(clearAll);
		clearBox.add(deleteSelection);
		
		/* errorLabel test */
		JPanel errorBox = new JPanel(new BorderLayout());
		errorBox.add(errorLabel,BorderLayout.PAGE_END);

		this.add(textAreas);
		this.add(Box.createVerticalStrut(10));
		this.add(textFields);
		this.add(Box.createVerticalStrut(40));
		this.add(clearBox);
		this.add(Box.createVerticalStrut(10));
		this.add(errorBox);
	}

	public AF getAF() {
		return framework;
	}

	public void setAF(AF framework) {
		this.framework = framework;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == submitArgument) {
			String ref = newArgumentRefField.getText();
			String text = newArgumentTextField.getText();
			if(!ref.equals("")) {
				Argument tmp = new Argument(ref,text);
				framework.getAr().add(tmp);
			}
			update(framework);
		}
		else if(ae.getSource() == submitAttack) {
			String a1 = newA1Field.getText();
			String a2 = newA2Field.getText();
			if(!a1.equals("") && !a2.equals("")) {
				Argument arg1 = framework.getAr().get(a1),arg2 = framework.getAr().get(a2);
				AttackRelation tmp = new AttackRelation(arg1,arg2);
				try {
					framework.getAtt().add(tmp,framework.getAr());
					errorLabel.setVisible(false);
				} catch (InvalidArgumentException e) {
					errorLabel.setText(e.getMessage());
					errorLabel.setVisible(true);
				}
			}
			update(framework);
		}
		else if(ae.getSource() == clearAll) {
			framework.setAr(new AR());
			framework.setAtt(new Att());
			update(framework);
		}
		else if(ae.getSource() == deleteSelection) {
			if(argumentsList.isSelectionEmpty()) {
				framework.getAtt().remove(attacksList.getSelectedValue());
				update(framework);
			}
			else if(attacksList.isSelectionEmpty()) {
				framework.getAr().remove(argumentsList.getSelectedValue());
				framework.getAtt().removeArgument(argumentsList.getSelectedValue());
				update(framework);
			}
			deleteSelection.setVisible(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource() == submitArgument && e.getKeyCode() == KeyEvent.VK_ENTER) {
			ActionEvent ae = new ActionEvent(submitArgument,0,"");
			actionPerformed(ae);
		}
		else if(e.getSource() == submitAttack && e.getKeyCode() == KeyEvent.VK_ENTER) {
			ActionEvent ae = new ActionEvent(submitAttack,0,"");
			actionPerformed(ae);
		}
		else if(e.getSource() == clearAll && e.getKeyCode() == KeyEvent.VK_ENTER) {
			ActionEvent ae = new ActionEvent(clearAll,0,"");
			actionPerformed(ae);	
		}
		else if(e.getSource() == deleteSelection && e.getKeyCode() == KeyEvent.VK_ENTER) {
			ActionEvent ae = new ActionEvent(deleteSelection,0,"");
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
	public void valueChanged(ListSelectionEvent e) {
		framework.getAr().setPaintAffAllFalse();
		if(!argumentsList.isSelectionEmpty() || !attacksList.isSelectionEmpty()) {
			deleteSelection.setVisible(true);
		}
		if(e.getSource() == argumentsList && !e.getValueIsAdjusting() && !argumentsList.isSelectionEmpty()) {
			attacksList.clearSelection();
			argumentsList.getSelectedValue().setPaintAFF(true);
			dp.setGraph(framework);
			dp.revalidate();
		}
		else if(e.getSource() == attacksList && !e.getValueIsAdjusting() && !attacksList.isSelectionEmpty()) {
			argumentsList.clearSelection();
			attacksList.getSelectedValue().getA1().setPaintAFF(true);
			attacksList.getSelectedValue().getA2().setPaintAFF(true);
			dp.setGraph(framework);
			dp.revalidate();
		}
	}
	
	public void update(AF framework) {
		this.framework = framework;
		argumentModel.clear();
		attackModel.clear();
		for(Argument a : framework.getAr().getArguments()) {
			argumentModel.addElement(a);
		}
		for(AttackRelation rel : framework.getAtt().getAttacks()) {
			attackModel.addElement(rel);
		}
		dp.setGraph(framework);
		dp.revalidate();
	}
}