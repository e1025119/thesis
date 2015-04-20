package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class StepByStepForm extends JPanel implements ActionListener,
		ListSelectionListener, KeyListener {

	private ExtensionsForm ext;
	private GraphDisplayPanel dp;
	
	public StepByStepForm(ExtensionsForm ext,GraphDisplayPanel dp) {
		this.ext = ext;
		this.dp = dp;
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
