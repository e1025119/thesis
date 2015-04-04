package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import logic_basics.*;
import logic_extensionCalculators.*;
import logic_extensions.*;

@SuppressWarnings("serial")
public class ExtensionsForm extends JPanel implements ActionListener {

	private final ArgumentationFrameworkForm aff;
	private final String[] extensionTypes = {"Admissible Extension","Preferred Extension","Stable Extension","Complete Extension","Grounded Extension"};
	private AF framework;
	private JLabel dropDownLabel = new JLabel("Choose Extension:");
	private JComboBox<String> dropDownCB;
	private JButton calculateExtension = new JButton("calculate");
	private DefaultListModel<Extension> model = new DefaultListModel<Extension>();
	private JList<Extension> extensionList = new JList<Extension>(model);

	public ExtensionsForm(ArgumentationFrameworkForm aff) {
		this.aff = aff;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		/* action listener */
		calculateExtension.addActionListener(this);

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
		JScrollPane listScroll = new JScrollPane();
		extensionList.setSelectedIndex(ListSelectionModel.SINGLE_SELECTION);
		listScroll.getViewport().add(extensionList);
		listScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(dropDownFL);
		this.add(listScroll);
	}

	public AF getFramework() {
		return framework;
	}

	public void setFramework(AF framework) {
		this.framework = framework;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == calculateExtension) {
			framework = aff.getAF();
			switch(dropDownCB.getSelectedItem().toString()) {
				case("Admissible Extension"):
					AdmissibleExtensionCalculator ac1 = new AdmissibleExtensionCalculator();
					AdmissibleExtensionList al1 = ac1.calculate(framework);
					for(Extension ext : al1.getExtensions()) {
						model.clear();
						model.addElement(ext);
					}
					extensionList = new JList<Extension>(model);
					break;
				case("Preferred Extension"):
					PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
					PreferredExtensionList pl1 = pc1.calculate(framework);
					for(Extension ext : pl1.getExtensions()) {
						model.clear();
						model.addElement(ext);
					}
					extensionList = new JList<Extension>(model);
					break;
				case("Stable Extension"):
					StableExtensionCalculator sc1 = new StableExtensionCalculator();
					StableExtensionList sl1 = sc1.calculate(framework);
					for(Extension ext : sl1.getExtensions()) {
						model.clear();
						model.addElement(ext);
					}
					extensionList = new JList<Extension>(model);
					break;
				case("Complete Extension"):
					CompleteExtensionCalculator cc1 = new CompleteExtensionCalculator();
					CompleteExtensionList cl1 = cc1.calculate(framework);
					for(Extension ext : cl1.getExtensions()) {
						model.clear();
						model.addElement(ext);
					}
					extensionList = new JList<Extension>(model);
					break;
				case("Grounded Extension"):
					GroundedExtensionCalculator gc1 = new GroundedExtensionCalculator();
					GroundedExtensionList gl1 = gc1.calculate(framework);
					for(Extension ext : gl1.getExtensions()) {
						model.clear();
						model.addElement(ext);
					}
					extensionList = new JList<Extension>(model);
					break;
				default:
					break;
				}
		}
	}
}
