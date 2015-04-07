package gui;

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
	private JLabel dropDownLabel = new JLabel("Choose Extension:");
	private JComboBox<String> dropDownCB;
	private JButton calculateExtension = new JButton("calculate");
	private DefaultListModel<Extension> model = new DefaultListModel<Extension>();
	private DefaultListModel<String> nullModel = new DefaultListModel<String>();
	private JList<Extension> extensionList = new JList<Extension>(model);
	private JList<String> nullList = new JList<String>(nullModel);
	private JScrollPane listScroll = new JScrollPane();
	
	public ExtensionsForm(ArgumentationFrameworkForm aff) {
		this.aff = aff;
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
		this.add(listScroll);
	}

	public AF getFramework() {
		return framework;
	}

	public void setFramework(AF framework) {
		this.framework = framework;
	}

	//TODO Label oder sowas für AF eigenschaften coherence/well-foundedness einführen..
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == calculateExtension) {
			framework = aff.getAF();
			switch(dropDownCB.getSelectedItem().toString()) {
				case("Admissible Extension"):
					AdmissibleExtensionCalculator ac1 = new AdmissibleExtensionCalculator();
					AdmissibleExtensionList al1 = ac1.calculate(framework);
					model.clear();
					for(Extension ext : al1.getExtensions()) {
						model.addElement(ext);
					}
					extensionList = new JList<Extension>(model);
					listScroll.getViewport().add(extensionList);
					break;
				case("Preferred Extension"):
					PreferredExtensionCalculator pc1 = new PreferredExtensionCalculator();
					PreferredExtensionList pl1 = pc1.calculate(framework);
					model.clear();
					for(Extension ext : pl1.getExtensions()) {
						model.addElement(ext);
					}
					extensionList = new JList<Extension>(model);
					listScroll.getViewport().add(extensionList);
					break;
				case("Stable Extension"):
					StableExtensionCalculator sc1 = new StableExtensionCalculator();
					StableExtensionList sl1 = sc1.calculate(framework);
					model.clear();
					try {
						for(Extension ext : sl1.getExtensions()) {
							model.addElement(ext);
							extensionList = new JList<Extension>(model);
							listScroll.getViewport().add(extensionList);
						}
					} catch(NullPointerException e) {
						nullModel.clear();
						nullModel.addElement("There exists no stable extension to this framework.");
						nullList = new JList<String>(nullModel);
						listScroll.getViewport().add(nullList);
					}					
					break;
				case("Complete Extension"):
					CompleteExtensionCalculator cc1 = new CompleteExtensionCalculator();
					CompleteExtensionList cl1 = cc1.calculate(framework);
					model.clear();
					for(Extension ext : cl1.getExtensions()) {
						model.addElement(ext);
					}
					extensionList = new JList<Extension>(model);
					listScroll.getViewport().add(extensionList);
					break;
				case("Grounded Extension"):
					GroundedExtensionCalculator gc1 = new GroundedExtensionCalculator();
					GroundedExtensionList gl1 = gc1.calculate(framework);
					model.clear();
					for(Extension ext : gl1.getExtensions()) {
						model.addElement(ext);
					}
					extensionList = new JList<Extension>(model);
					listScroll.getViewport().add(extensionList);
					break;
				default:
					break;
			}
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
		//TODO calculate and display graph
	}
}
 