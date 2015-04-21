package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic_basics.*;
import logic_extensionCalculators.*;
import logic_extensions.*;

@SuppressWarnings("serial")
public class ExtensionsForm extends JPanel implements ActionListener,KeyListener,ListSelectionListener {

	private final ArgumentationFrameworkForm aff;
	private final String[] extensionTypes = {"Admissible Extension","Preferred Extension","Stable Extension","Complete Extension","Grounded Extension"};
	private AF framework;
	private GraphDisplayPanel dp;
	private JLabel dropDownLabel = new JLabel("Choose Extension:"),listLabel = new JLabel(),cwfLabel = new JLabel();
	private JComboBox<String> dropDownCB;
	private JButton calculateExtension = new JButton("calculate");
	private DefaultListModel<Extension> model = new DefaultListModel<Extension>();
	private DefaultListModel<String> nullModel = new DefaultListModel<String>();
	private JList<Extension> extensionList = new JList<Extension>(model);
	private JList<String> nullList = new JList<String>(nullModel);
	private JScrollPane listScroll = new JScrollPane();
	private Component infoVert = Box.createVerticalStrut(300),infoHoriz = Box.createHorizontalStrut(10);
	private JLabel infoLabel = new JLabel("<html><font color=red>To use this function, please select one of the provided examples<br>"
			+ "or create your own framework in the tab \"Argumentation Framework\".</font></html>");

	public ExtensionsForm(ArgumentationFrameworkForm aff,GraphDisplayPanel dp) {
		this.aff = aff;
		this.dp = dp;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		/* action listener */
		calculateExtension.addActionListener(this);
		calculateExtension.addKeyListener(this);
		extensionList.addListSelectionListener(this);

		/* FlowLayout for the dropDown area */
		JPanel dropDownFL = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel dropDownBL = new JPanel();
		dropDownBL.setLayout(new BoxLayout(dropDownBL,BoxLayout.Y_AXIS));
		dropDownCB = new JComboBox<String>(extensionTypes);
		dropDownCB.setSelectedItem(null);
		dropDownLabel.setAlignmentX(LEFT_ALIGNMENT);
		dropDownCB.setAlignmentX(LEFT_ALIGNMENT);
		calculateExtension.setAlignmentX(LEFT_ALIGNMENT);
		dropDownBL.add(Box.createVerticalStrut(10));
		dropDownBL.add(dropDownLabel);
		dropDownBL.add(dropDownCB);
		dropDownBL.add(Box.createVerticalStrut(6));
		dropDownBL.add(calculateExtension);
		dropDownFL.add(Box.createHorizontalStrut(10));
		dropDownFL.add(dropDownBL);

		/* Layout for jlist area */
		extensionList.setSelectedIndex(ListSelectionModel.SINGLE_SELECTION);
		nullList.setSelectedIndex(ListSelectionModel.SINGLE_SELECTION);
		listScroll.getViewport().add(extensionList);
		listScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		this.add(dropDownFL);
		this.add(cwfLabel);
		this.add(listLabel);
		this.add(listScroll);
		this.add(infoVert);
		this.add(infoLabel);
		this.add(infoHoriz);
		dropDownFL.setAlignmentX(LEFT_ALIGNMENT);
		cwfLabel.setAlignmentX(LEFT_ALIGNMENT);
		cwfLabel.setVisible(false);
		infoVert.setVisible(false);
		infoHoriz.setVisible(false);
		infoLabel.setVisible(false);
		listLabel.setAlignmentX(LEFT_ALIGNMENT);
		listLabel.setVisible(false);
		listScroll.setAlignmentX(LEFT_ALIGNMENT);
	}

	public JList<Extension> getExtensionList() {
		return extensionList;
	}

	public AF getFramework() {
		return framework;
	}

	public void setFramework(AF framework) {
		this.framework = framework;
	}

	public void refresh() {
		framework = aff.getAF();
		dp.setGraph(framework);
		dp.revalidate();
		if(framework.getAr().getArguments().isEmpty()) {
			for(Component c : this.getComponents()) {
				c.setVisible(false);
			}
			infoVert.setVisible(true);
			infoHoriz.setVisible(true);
			infoLabel.setVisible(true);
		}
		else {
			for(Component c : this.getComponents()) {
				c.setVisible(true);
			}
			infoLabel.setVisible(false);
			infoVert.setVisible(false);
			infoHoriz.setVisible(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == calculateExtension) {
			framework = aff.getAF();
			String cwf = framework.getAFProp();
			cwfLabel.setText(cwf);
			cwfLabel.setVisible(true);
			
			switch(dropDownCB.getSelectedItem().toString()) {
			case("Admissible Extension"):
				listLabel.setText("All admissible extensions are listed below:");
				AdmissibleExtensionCalculator ac1 = new AdmissibleExtensionCalculator();
				AdmissibleExtensionList al1 = ac1.calculate(framework);
				model.clear();
				for(Extension ext : al1.getExtensions()) {
					model.addElement(ext);
					listScroll.getViewport().removeAll();
					listScroll.getViewport().add(extensionList);
				}
				dp.setGraph(framework);
				dp.revalidate();
				break;
			case("Preferred Extension"):
				listLabel.setText("All preferred extensions are listed below:");
				PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
				PreferredExtensionList pl1 = pc1.calculate(framework);
				model.clear();
				for(Extension ext : pl1.getExtensions()) {
					model.addElement(ext);
					listScroll.getViewport().removeAll();
					listScroll.getViewport().add(extensionList);
				}
				dp.setGraph(framework);
				dp.revalidate();
				break;
			case("Stable Extension"):
				listLabel.setText("All stable extensions are listed below:");
				StableExtensionCalculator sc1 = new StableExtensionCalculator();
				StableExtensionList sl1 = sc1.calculate(framework);
				model.clear();
				try {
					for(Extension ext : sl1.getExtensions()) {
						model.addElement(ext);
						listScroll.getViewport().removeAll();
						listScroll.getViewport().add(extensionList);
					}
				} catch(NullPointerException e) {
					nullModel.clear();
					nullModel.addElement("There exists no stable extension to this framework.");
					listScroll.getViewport().removeAll();
					listScroll.getViewport().add(nullList);
					dp.setGraph(null);
					dp.revalidate();
					break;
				}
				dp.setGraph(framework);
				dp.revalidate();
				break;
			case("Complete Extension"):
				listLabel.setText("All complete extensions are listed below:");
				CompleteExtensionCalculator cc1 = new CompleteExtensionCalculator();
				CompleteExtensionList cl1 = cc1.calculate(framework);
				model.clear();
				for(Extension ext : cl1.getExtensions()) {
					model.addElement(ext);
					listScroll.getViewport().removeAll();
					listScroll.getViewport().add(extensionList);
				}
				dp.setGraph(framework);
				dp.revalidate();
				break;
			case("Grounded Extension"):
				listLabel.setText("All grounded extensions are listed below:");
				GroundedExtensionCalculator gc1 = new GroundedExtensionCalculator();
				GroundedExtensionList gl1 = gc1.calculate(framework);
				model.clear();
				for(Extension ext : gl1.getExtensions()) {
					model.addElement(ext);
					listScroll.getViewport().removeAll();
					listScroll.getViewport().add(extensionList);
				}
				dp.setGraph(framework);
				dp.revalidate();
				break;
			default:
				break;
			}
			listLabel.setVisible(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			ActionEvent ae = new ActionEvent(calculateExtension,0,"");
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
		if(e.getSource() == extensionList && !e.getValueIsAdjusting() && !extensionList.isSelectionEmpty()) {
			Extension tmp = extensionList.getSelectedValue();
			for(Argument a: framework.getAr().getArguments()) {
				a.setPaintEF(false);
				if(tmp.getArguments().contains(a)) {
					a.setPaintEF(true);
				}
			}
		}
		dp.setGraph(framework);
		dp.revalidate();
	}
}