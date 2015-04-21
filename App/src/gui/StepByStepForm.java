package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import logic_basics.Argument;
import logic_extensions.AdmissibleExtension;
import logic_extensions.Extension;

@SuppressWarnings("serial")
public class StepByStepForm extends JPanel implements ActionListener,KeyListener {

	private Extension.ExtensionTypes extensionType;
	private ExtensionsForm ext;
	private GraphDisplayPanel dp;
	private Component infoVert = Box.createVerticalStrut(300),infoHoriz = Box.createHorizontalStrut(10);
	private JTextArea extensionArea;
	private JComboBox<Argument> argumentDropDown = new JComboBox<Argument>();
	private JLabel infoLabel = new JLabel("<html><font color=red>To use this function, please select one of the calculated extensions<br>"
			+ "in the tab \"Extensions\".</font></html>");

	
	public StepByStepForm(ExtensionsForm ext,GraphDisplayPanel dp) {
		this.ext = ext;
		this.dp = dp;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
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
		extensionBox.setLayout(new BorderLayout());
		extensionBox.add(extensionLabel,BorderLayout.NORTH);
		extensionBox.add(Box.createVerticalGlue());
		extensionBox.add(extensionArea,BorderLayout.WEST);
		JPanel extensionFlow = new JPanel(new FlowLayout());
		extensionFlow.add(Box.createGlue());
		extensionFlow.add(extensionBox);
		extensionFlow.add(Box.createGlue());
		
		JLabel argumentLabel = new JLabel("Select Argument:");
		JPanel argumentBox = new JPanel(new BorderLayout());
		JPanel argumentFlow = new JPanel(new FlowLayout());
		argumentBox.add(argumentLabel);
		argumentBox.add(argumentDropDown);
		argumentFlow.add(argumentBox);
		
		this.add(extensionFlow);
		this.add(argumentFlow);
	}
	
	public void refresh() {
		ext.refresh();
		if(ext.getExtensionList().isSelectionEmpty()) {
			for(Component c : this.getComponents()) {
				c.setVisible(false);
			}
			infoVert.setVisible(true);
			infoLabel.setVisible(true);
			infoHoriz.setVisible(true);
		}
		else {
			if(ext.getExtensionList().getSelectedValue() instanceof AdmissibleExtension){
				extensionType = Extension.ExtensionTypes.Admissible;
			}
			
			dp.setGraph(ext.getFramework());
			dp.revalidate();
			for(Component c : this.getComponents()) {
				c.setVisible(true);
			}
			
			DefaultComboBoxModel<Argument> argumentModel = new DefaultComboBoxModel<Argument>();
			for(Argument a : ext.getExtensionList().getSelectedValue().getArguments().getArguments()) {
				argumentModel.addElement(a);
			}
			argumentDropDown.setModel(argumentModel);
			extensionArea.setText(ext.getExtensionList().getSelectedValue().toString());
			
			infoVert.setVisible(false);
			infoLabel.setVisible(false);
			infoHoriz.setVisible(false);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

}
