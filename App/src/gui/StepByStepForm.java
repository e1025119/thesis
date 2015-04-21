package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JComboBox<String> argumentDropDown = new JComboBox<String>();
	private JButton prev = new JButton(),next = new JButton();
	private JLabel infoLabel = new JLabel("<html><font color=red>To use this function, please select one of the calculated extensions<br>"
			+ "in the tab \"Extensions\".</font></html>");

	
	public StepByStepForm(ExtensionsForm ext,GraphDisplayPanel dp) {
		this.ext = ext;
		this.dp = dp;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		/* listeners */
		prev.addActionListener(this);
		prev.addKeyListener(this);
		next.addActionListener(this);
		next.addKeyListener(this);
		
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
		JPanel argumentBox2 = new JPanel(new FlowLayout());
		argumentBox1.add(argumentLabel,BorderLayout.NORTH);
		argumentDropDown.setPreferredSize(new Dimension(150,20));
		argumentBox1.add(argumentDropDown,BorderLayout.WEST);
		prev.setIcon(new ImageIcon("left.png"));
		prev.setPreferredSize(new Dimension(25,25));
		next.setIcon(new ImageIcon("right.png"));
		next.setPreferredSize(new Dimension(25,25));
		argumentBox2.add(prev);
		argumentBox2.add(Box.createHorizontalStrut(10));
		argumentBox2.add(next);
		argumentFlow.add(argumentBox1);
		argumentFlow.add(Box.createHorizontalStrut(100));
		argumentFlow.add(argumentBox2);
		argumentFlow.add(Box.createHorizontalStrut(100));
		
		this.add(extensionFlow);
		this.add(argumentFlow);
		this.add(Box.createVerticalStrut(300));
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
			DefaultComboBoxModel<String> argumentModel = new DefaultComboBoxModel<String>();
			argumentModel.addElement("all");
			for(Argument a : ext.getExtensionList().getSelectedValue().getArguments().getArguments()) {
				argumentModel.addElement(a.getRef());
			}
			argumentDropDown.setModel(argumentModel);
			extensionArea.setText(ext.getExtensionList().getSelectedValue().toString());
			
			infoVert.setVisible(false);
			infoLabel.setVisible(false);
			infoHoriz.setVisible(false);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource() == prev && e.getKeyCode() == KeyEvent.VK_ENTER) {
			ActionEvent ae = new ActionEvent(prev,0,"");
		}
		else if(e.getSource() == next && e.getKeyCode() == KeyEvent.VK_ENTER) {
			ActionEvent ae = new ActionEvent(next,0,"");
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
		if(e.getSource() == next) {
			//TODO
			System.out.println("next");
		}
		else if(e.getSource() == prev) {
			//TODO
			System.out.println("prev");
		}
	}

}
