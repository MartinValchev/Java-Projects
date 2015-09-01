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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;

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
	private StringBuilder builder;
	private String errorMessage = "Position or impressions field is empty";
	private String name;

	public GeneralSettingsModule() {
		settingsFrame = new JFrame("General Settings");
		settingsFrame.setSize(350, 250);
		settingsFrame.setResizable(false);
		settingsFrame.setLocation(410, 0);
		settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingsFrame.setLayout(new BorderLayout());
		limitCont = new Container();
		builder = new StringBuilder();
		limitCont.setLayout(new FlowLayout());
		windowNameLabel = new JLabel("General Settings");

		// adding elements to infoCont
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(createPositionPanel());

		// /
		saveSettings = new JButton("Save settings");
		settingsFrame.getContentPane().add(windowNameLabel, BorderLayout.NORTH);
		settingsFrame.getContentPane().add(verticalBox, BorderLayout.CENTER);
		settingsFrame.getContentPane().add(saveSettings, BorderLayout.SOUTH);
		settingsFrame.setVisible(true);
	}

	public JPanel createPositionPanel() {
		JPanel positionPanel = new JPanel();
		listModel = new DefaultListModel();
		// listModel.addElement("5000 - Position 1			");
		// listModel.addElement("4000 - Position 2			");
		// listModel.addElement("3000 - Position 3			");
		// listModel.addElement("5000 - Position 4			");
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		// list.addListSelectionListener(new AddListener);
		list.setFixedCellWidth(330);
		list.setVisibleRowCount(7);
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setSize(new Dimension(330, 200));
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
		positionsField.getDocument().addDocumentListener(addListener);
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
		public void actionPerformed(ActionEvent e) {
			builder.append(impressionsField.getText());
			builder.append(" / ");
			builder.append(positionsField.getText());
			name = builder.toString();
			// User didn't type in a unique name...
			if (name.equals("") || alreadyInList(name)) {
				positionsField.requestFocusInWindow();
				positionsField.selectAll();
				return;
			}
			if (impressionsField.getText().isEmpty()
					|| positionsField.getText().isEmpty()) {
				JOptionPane
						.showMessageDialog(null, errorMessage,
								"Settings input error",
								JOptionPane.ERROR_MESSAGE, null);
				impressionsField.setText("");
				positionsField.setText("");
			} else {
				listModel.addElement(name);
				builder.setLength(0);
			}

		}

		protected boolean alreadyInList(String name) {
			return listModel.contains(name);
		}

		@Override
		// Required by DocumentListener.
		public void insertUpdate(DocumentEvent e) {
			enableButton();
		}

		// Required by DocumentListener.
		public void removeUpdate(DocumentEvent e) {
			handleEmptyTextField(e);
		}

		// Required by DocumentListener.
		public void changedUpdate(DocumentEvent e) {
			if (!handleEmptyTextField(e)) {
				enableButton();
			}
		}

		private void enableButton() {
			if (!alreadyEnabled) {
				button.setEnabled(true);
			}
		}

		private boolean handleEmptyTextField(DocumentEvent e) {
			if (e.getDocument().getLength() <= 0) {
				button.setEnabled(false);
				alreadyEnabled = false;
				return true;
			}
			return false;
		}

		// This method is required by ListSelectionListener.
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {

				if (list.getSelectedIndex() == -1) {
					// No selection, disable fire button.
					removeButton.setEnabled(false);

				} else {
					// Selection, enable the fire button.
					removeButton.setEnabled(true);
				}
			}

		}

		
	}
	public class RemoveListener implements ActionListener {

		 public void actionPerformed(ActionEvent e) {
	            //This method can be called only if
	            //there's a valid selection
	            //so go ahead and remove whatever's selected.
	            int index = list.getSelectedIndex();
	            listModel.remove(index);
	 
	            int size = listModel.getSize();
	 
	            if (size == 0) { //Nobody's left, disable firing.
	                removeButton.setEnabled(false);
	 
	            } else { //Select an index.
	                if (index == listModel.getSize()) {
	                    //removed item in last position
	                    index--;
	                }
	 
	                list.setSelectedIndex(index);
	                list.ensureIndexIsVisible(index);
	            }
	        }

	}
}
