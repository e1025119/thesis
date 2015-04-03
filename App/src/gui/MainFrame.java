package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame {

	private JFrame frame;
	private JTabbedPane tabs;

	public MainFrame() {
		this.frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080,720);
		frame.setTitle("KAFFEE");

		this.tabs = new JTabbedPane();
		JPanel definitionTab = new JPanel(new GridLayout());
		JPanel extensionTab = new JPanel(new GridLayout());
		JPanel displayPanel1 = new JPanel(),displayPanel2 = new JPanel();
		JPanel definitionForm = new ArgumentationFrameworkForm();
		JPanel extensionForm = new JPanel();
		tabs.addTab("AF",definitionTab);
		tabs.addTab("Extensions",extensionTab);
		
		displayPanel1.setBackground(Color.CYAN);
		displayPanel2.setBackground(Color.MAGENTA);
		
		definitionTab.add(definitionForm);
		definitionTab.add(displayPanel1);
		extensionTab.add(extensionForm);
		extensionTab.add(displayPanel2);
		
		frame.setContentPane(tabs);
		frame.setVisible(true);
	}
}
