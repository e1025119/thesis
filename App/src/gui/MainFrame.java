package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame {

	private JFrame frame;
	private JTabbedPane tabs;

	public MainFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080,720);
		frame.setTitle("KAFFEE");
		frame.setResizable(false);

		tabs = new JTabbedPane();
		JPanel definitionTab = new JPanel(new GridLayout(1,2));
		JPanel extensionTab = new JPanel(new GridLayout(1,2));
		JPanel displayPanel1 = new JPanel(),displayPanel2 = new JPanel();
		ArgumentationFrameworkForm definitionForm = new ArgumentationFrameworkForm();
		ExtensionsForm extensionForm = new ExtensionsForm(definitionForm);
		tabs.addTab("AF",definitionTab);
		tabs.addTab("Extensions",extensionTab);
		
		displayPanel1.setBorder(BorderFactory.createLineBorder(new Color((float)0.7,(float)0.79,1),2));
		displayPanel2.setBorder(BorderFactory.createLineBorder(new Color((float)0.7,(float)0.79,1),2));
		
		definitionTab.add(definitionForm);
		definitionTab.add(displayPanel1);
		extensionTab.add(extensionForm);
		extensionTab.add(displayPanel2);
		
		frame.setContentPane(tabs);
		frame.setVisible(true);
	}
}
