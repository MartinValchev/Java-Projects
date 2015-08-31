package campaignProject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import campaignProject.CampaignPosModule.AddListener;
import campaignProject.CampaignPosModule.RemoveListener;

public class GeneralSettingsModule {
	private JFrame settingsFrame;
	private Container limitCont;
	private JButton saveSettings;
	private DataModule websiteModule;
	private DataModule positionModule;
	private JLabel windowNameLabel;
	private JList list;
	private DefaultListModel listModel;
	private static final String removeString = "Del";
	private static final String addString = "Add";
	private JButton removeButton;
	private JButton addButton;
	private JTextField impressionsField;
	private JTextField positionsField;
	private JLabel separator;
	private String[] positionStrings;
	
	public GeneralSettingsModule() {
		settingsFrame= new JFrame("General Settings");
		settingsFrame.setSize(350, 250);
		settingsFrame.setResizable(false);
		settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingsFrame.setLayout(new BorderLayout());
		limitCont = new Container();
		limitCont.setLayout(new FlowLayout());
		windowNameLabel = new JLabel("General Settings");

		// adding elements to infoCont
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(createPositionPanel());

		///
		saveSettings = new JButton("Save settings");
		settingsFrame.getContentPane().add(windowNameLabel,BorderLayout.NORTH);
		settingsFrame.getContentPane().add(verticalBox,BorderLayout.CENTER);
		settingsFrame.getContentPane().add(saveSettings, BorderLayout.SOUTH);
		settingsFrame.setVisible(true);
	}
	public JPanel createPositionPanel(){
		JPanel positionPanel = new JPanel();
		listModel = new DefaultListModel();
		//listModel.addElement("5000 - Position 1			");
		//listModel.addElement("4000 - Position 2			");
		//listModel.addElement("3000 - Position 3			");
		//listModel.addElement("5000 - Position 4			");
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		//list.addListSelectionListener(new AddListener);
		list.setFixedCellWidth(330);
		list.setVisibleRowCount(7);
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setSize(new Dimension(330,200));
		//

		//
		addButton = new JButton(addString);
		AddListener addListener = new AddListener(addButton);
		addButton.setActionCommand(addString);
		addButton.addActionListener(addListener);
		addButton.setEnabled(false);
		//
		removeButton = new JButton(removeString);
		removeButton.setActionCommand(removeString);
		removeButton.addActionListener(new RemoveListener());
		//
		impressionsField = new JTextField(7);
		impressionsField.setText("impres. max");
		impressionsField.addActionListener(addListener);
		impressionsField.getDocument().addDocumentListener(addListener);
		positionsField = new JTextField(12); 
		positionsField.setText("Position Name");
		//
		separator = new JLabel(" / ");
		//
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(removeButton);
		buttonPane.add(Box.createHorizontalStrut(5));
		buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPane.add(Box.createHorizontalStrut(5));
		buttonPane.add(impressionsField);
		buttonPane.add(separator);
		buttonPane.add(positionsField);
		buttonPane.add(addButton);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		positionPanel.add(buttonPane, BorderLayout.NORTH);
		positionPanel.add(listScrollPane, BorderLayout.CENTER);
		return positionPanel;

}
	public class AddListener implements ActionListener, DocumentListener {
		private boolean alreadyEnabled = false;
		private JButton button;

		public AddListener(JButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	public class RemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}

