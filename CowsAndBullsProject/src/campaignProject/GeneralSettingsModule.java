package campaignProject;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
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
import javax.swing.ListCellRenderer;
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
	private JList<PositionTableRecord> list;
	private DefaultListModel<PositionTableRecord> listModel;
	private static final String removeString = "Del";
	private static final String addString = "Add";
	private JButton removeButton;
	private JButton addButton;
	private JTextField impressionsField;
	private JTextField positionsField;
	private JLabel separator;
	private StringBuilder builder;
	private String numberFormatErrorMessage = "Impressions field does not contain valid number";
	private String alreadyInListErrorMessage = "The position you are trying to enter is already in the List";
	private PositionTableRecord positionRecord;
	private GeneralSettingsData settingsData;
	private static final char DELIMITER = '-';

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
		listModel = new DefaultListModel<PositionTableRecord>();
		settingsData.fillListModel(listModel);
		list = new JList<>(listModel);
		list.setCellRenderer(new PositionRenderer());
		list.setOpaque(true);
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
		addButton.setEnabled(true);
		//
		removeButton = new JButton(removeString);
		removeButton.setActionCommand(removeString);
		removeButton.addActionListener(new RemoveListener());
		//
		impressionsField = new JTextField(7);
		impressionsField.setToolTipText("Impress max");
		impressionsField.requestFocus();
		positionsField = new JTextField(12);
		positionsField.setToolTipText("Position Name");
		positionsField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (impressionsField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Impressions not entered", "Settings input error",
							JOptionPane.ERROR_MESSAGE, null);
					impressionsField.requestFocus();
				}
				if (!settingsData.validateInput(impressionsField.getText())) {
					JOptionPane.showMessageDialog(null, numberFormatErrorMessage, "Settings input error",
							JOptionPane.ERROR_MESSAGE, null);
				}

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
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

	public class AddListener implements ActionListener {
		protected boolean alreadyEnabled = false;
		private JButton button;

		public AddListener(JButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (positionsField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Position not entered", "Settings input error",
						JOptionPane.ERROR_MESSAGE, null);
				positionsField.requestFocus();
				return;
			}
			String positionName = positionsField.getText().trim();
			int impressions = Integer.parseInt(impressionsField.getText());
			positionRecord = new PositionTableRecord(positionName, impressions);
			builder.append(impressions);
			builder.append(DELIMITER);
			builder.append(positionName);
			// User didn't type in a unique name...
			if (settingsData.compareRecords(listModel, positionRecord)) {
				JOptionPane.showMessageDialog(null, alreadyInListErrorMessage, "Settings input error",
						JOptionPane.ERROR_MESSAGE, null);
				positionsField.requestFocusInWindow();
				positionsField.selectAll();
				return;
			}

			listModel.addElement(new PositionTableRecord(positionName, impressions));
			positionsField.setText("");
			impressionsField.setText("");

		}

		protected boolean alreadyInList(PositionTableRecord name) {
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
			ArrayList<PositionTableRecord> records = Collections.list(listModel.elements());
			try {
				settingsData.compareLists(records);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// close window
			settingsFrame.setVisible(false);
			settingsFrame = null;

		}
	}

	public class ImpressionsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(impressionsField.getText());

		}

	}

	public class PositionRenderer extends JTextField implements ListCellRenderer<PositionTableRecord> {

		@Override
		public Component getListCellRendererComponent(JList<? extends PositionTableRecord> list,
				PositionTableRecord record, int index, boolean isSelected, boolean cellHasFocus) {
			String positionRecord = record.getPostionRecord();
			setText(positionRecord);
			setEditable(false);
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			return this;
		}

	}
}
