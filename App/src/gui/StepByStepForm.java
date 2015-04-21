package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class StepByStepForm extends JPanel implements ActionListener,
		ListSelectionListener, KeyListener {

	private ExtensionsForm ext;
	private GraphDisplayPanel dp;
	private Component infoVert = Box.createVerticalStrut(300),infoHoriz = Box.createHorizontalStrut(10);
	private JLabel infoLabel = new JLabel("<html><font color=red>To use this function, please select one of the calculated extensions<br>"
			+ "in the tab \"Extensions\".</font></html>");

	
	public StepByStepForm(ExtensionsForm ext,GraphDisplayPanel dp) {
		this.ext = ext;
		this.dp = dp;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		this.add(infoVert);
		this.add(infoLabel);
		this.add(infoHoriz);
		infoVert.setVisible(false);
		infoLabel.setVisible(false);
		infoHoriz.setVisible(false);
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
			dp.setGraph(ext.getFramework());
			dp.revalidate();
			for(Component c : this.getComponents()) {
				c.setVisible(true);
			}
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
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
