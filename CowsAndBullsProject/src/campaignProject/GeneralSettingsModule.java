package campaignProject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
	private ArrayList<String> currentSettingsList;
	private ArrayList<String> newSettingsList;
	private String numberFormatErrorMessage = "Impressions field does not contain valid number";
	private String alreadyInListErrorMessage = "The position you are trying to enter is already in the List";
	private String positionRecord;
	private GeneralSettingsData settingsData;
	private static final String DELIMITER = " - ";

	public GeneralSettingsModule() throws SQLException {
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
		settingsData = new GeneralSettingsData();
		// adding elements to infoCont
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(createPositionPanel());

		// /
		saveSettings = new JButton("Save settings");
		saveSettings.addActionListener(new SaveSettingsListener());
		settingsFrame.getContentPane().add(windowNameLabel, BorderLayout.NORTH);
		settingsFrame.getContentPane().add(verticalBox, BorderLayout.CENTER);
		settingsFrame.getContentPane().add(saveSettings, BorderLayout.SOUTH);
		settingsFrame.setVisible(true);
	}

	public String getPositionRecord() {
		return positionRecord;
	}

	public DefaultListModel getListModel() {
		return listModel;
	}
	/*
	 * public ArrayList<String> ConvertToArrayList() { ArrayList<String> arrList
	 * = new ArrayList<String>(); for (int i = 0; i < listModel.size(); i++) {
	 * arrList.add((String) listModel.getElementAt(i)); } return arrList; }
	 */

	public JPanel createPositionPanel() {
		JPanel positionPanel = new JPanel();
		listModel = new DefaultListModel();
		settingsData.fillListModel(listModel);
		list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		// list.addListSelectionListener(new AddListener);
		list.setFixedCellWidth(330);
		list.setVisibleRowCount(7);
		// settingsData.pullSettingsFromTable(this);

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
		impressionsField.setText("Impress max");
		impressionsField.addActionListener(addListener);
		impressionsField.getDocument().addDocumentListener(addListener);
		positionsField = new JTextField(12);
		positionsField.getDocument().addDocumentListener(addListener);
		positionsField.setText("Position Name");

		//
		separator = new JLabel(" - ");
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

	public ArrayList<String> getListArray() {
		ArrayList<String> arrList = new ArrayList<String>();
		for (int i = 0; i < listModel.size(); i++) {
			arrList.add((String) listModel.getElementAt(i));
		}

		return arrList;
	}

	public class AddListener implements ActionListener, DocumentListener {
		protected boolean alreadyEnabled = false;
		private JButton button;

		public AddListener(JButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			builder.append(impressionsField.getText());
			builder.append(DELIMITER);
			builder.append(positionsField.getText());
			positionRecord = builder.toString();
			// User didn't type in a unique name...
			if (positionRecord.equals("") || alreadyInList(positionRecord)) {
				JOptionPane.showMessageDialog(null, alreadyInListErrorMessage, "Settings input error",
						JOptionPane.ERROR_MESSAGE, null);
				positionsField.requestFocusInWindow();
				positionsField.selectAll();
				return;
			}

			if (settingsData.validateInput(positionRecord)) {
				listModel.addElement(positionRecord);
				builder.append(DELIMITER);
				positionsField.setText("");
				impressionsField.setText("");
				
			} else {
				JOptionPane.showMessageDialog(null, numberFormatErrorMessage, "Settings input error",
						JOptionPane.ERROR_MESSAGE, null);
				impressionsField.setText("");
				builder.setLength(0);
			}
			builder.setLength(0);

		}

		protected boolean alreadyInList(String name) {
			return listModel.contains(name);
		}

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
			// This method can be called only if
			// there's a valid selection
			// so go ahead and remove whatever's selected.
			/*
			 * builder.append(impressionsField.getText()); builder.append(" / "
			 * ); builder.append(positionsField.getText()); positionRecord =
			 * builder.toString();
			 */
			int index = list.getSelectedIndex();
			listModel.remove(index);

			int size = listModel.getSize();

			if (size == 0) { // Nobody's left, disable firing.
				removeButton.setEnabled(false);

			} else { // Select an index.
				if (index == listModel.getSize()) {
					// removed item in last position
					index--;
				}

				list.setSelectedIndex(index);
				list.ensureIndexIsVisible(index);
			}
		}

	}

	public class SaveSettingsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			newSettingsList = getListArray();
			settingsData.compareLists(currentSettingsList, newSettingsList);
			// close window
			settingsFrame.setVisible(false);
			settingsFrame = null;

		}
	}
}
