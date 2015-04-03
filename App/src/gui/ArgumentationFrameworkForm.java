package gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic_basics.AF;

@SuppressWarnings("serial")
public class ArgumentationFrameworkForm extends JPanel {

	private AF framework;
	private JTextField newArgumentLabel,newArgumentText;
	
	public ArgumentationFrameworkForm() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.newArgumentLabel = new JTextField();
		this.newArgumentText = new JTextField();
		
		this.add(Box.createVerticalStrut(50));
		this.add(newArgumentLabel);
		this.add(Box.createVerticalStrut(50));
		this.add(newArgumentText);
		this.add(Box.createVerticalStrut(50));
	}
}
