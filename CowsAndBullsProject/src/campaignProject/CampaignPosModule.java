package campaignProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
import javax.swing.event.ListSelectionListener;

public class CampaignPosModule extends JPanel implements ListSelectionListener {
	private JList list;
	private DefaultListModel listModel;
	private static final String removeString = "Del";
	private static final String addString = "Add";
	// buttons
	private JButton removeButton;
	private JButton addButton;
	// textField
	private JTextField impressionsField;
	// labels
	private JLabel startDateLabel;
	private JLabel endDateLabel;
	// date pickers
	private DatePicker posStartDatePicker;
	private DatePicker posEndDatePicker;
	//
	private String posStartDate;
	private String posEndDate;
	private String[] positionStrings;
	private JComboBox positionList;
	private StringBuilder builder;
	// JPanels
	private JPanel positionDatesPanel;
	private JPanel positionStartDatePanel;
	private JPanel positionEndDatePanel;
	private static final char DELIMITER = '-';
	private String errorMessage = "Please fill all the fields prior to submitting the record";
	private DatabaseConnection databaseConnection;
	
	public CampaignPosModule() {
		super(new BorderLayout());
		listModel = new DefaultListModel();
		// listModel.addElement("5000 - Position 1");
		// listModel.addElement("4000 - Position 2");
		// listModel.addElement("3000 - Position 3");
		// listModel.addElement("5000 - Position 4");
		databaseConnection = new DatabaseConnection();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(5);
		///////////////////////
		JScrollPane listScrollPane = new JScrollPane(list);
		positionDatesPanel = new JPanel();
		positionDatesPanel.setLayout(new GridLayout(2, 2));
		startDateLabel = new JLabel("Position Start Date");
		endDateLabel = new JLabel("Position End Date");
		posStartDatePicker = new DatePicker();
		posEndDatePicker = new DatePicker();
		positionStartDatePanel = posStartDatePicker.getDatesPanel();
		positionEndDatePanel = posEndDatePicker.getDatesPanel();
		positionDatesPanel.add(startDateLabel);
		positionDatesPanel.add(endDateLabel);
		positionDatesPanel.add(positionStartDatePanel);
		positionDatesPanel.add(positionEndDatePanel);
		//
		posStartDate = "";
		posEndDate = "";
		//

		builder = new StringBuilder();
		positionStrings = new String[] { "Position 1", "Position 2", "Position 3", "Position 4" };
		positionList = new JComboBox(positionStrings);
		positionList.setSelectedIndex(0);
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
		impressionsField = new JTextField(12);
		impressionsField.setText("daily impressions");
		impressionsField.addActionListener(addListener);
		impressionsField.getDocument().addDocumentListener(addListener);
		//e
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(removeButton);
		buttonPane.add(Box.createHorizontalStrut(5));
		buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPane.add(Box.createHorizontalStrut(5));
		buttonPane.add(impressionsField);
		buttonPane.add(positionList);
		buttonPane.add(addButton);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(positionDatesPanel, BorderLayout.NORTH);
		add(buttonPane, BorderLayout.CENTER);
		add(listScrollPane, BorderLayout.SOUTH);

	}

	// !needs rework
	public String getPositionDates(String type) {
		if (type.equals("start")) {
			return posStartDatePicker.getDateValue();
		} else if (type.equals("end")) {
			return posEndDatePicker.getDateValue();
		}
		return null;
	}
	/*
	 * private static void createAndShowGUI() { // Create and set up the window.
	 * JFrame frame = new JFrame("Add/Remove Position");
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * 
	 * // Create and set up the content pane. JComponent newContentPane = new
	 * CampaignPosModule(); newContentPane.setOpaque(true); // content panes
	 * must be opaque frame.setContentPane(newContentPane);
	 * 
	 * // Display the window. frame.pack(); frame.setVisible(true); }
	 */

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// createAndShowGUI();
			}
		});
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public class AddListener implements ActionListener, DocumentListener {
		private boolean alreadyEnabled = false;
		private JButton button;

		public AddListener(JButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// check if all the fields are not empty
			posStartDate = getPositionDates("start");
			posEndDate = getPositionDates("end");
			if (isInputEntered()) {

				builder.append(impressionsField.getText());
				builder.append(DELIMITER);
				builder.append(positionList.getSelectedItem());
				builder.append(DELIMITER);
				builder.append(posStartDate);
				builder.append(DELIMITER);
				builder.append(posEndDate);
				listModel.addElement(builder.toString());
				// for adding the list 
			} else {
				JOptionPane.showMessageDialog(null, errorMessage, "Settings input error", JOptionPane.ERROR_MESSAGE,
						null);
				impressionsField.setText("");
				builder.setLength(0);
				impressionsField.setText("");
				posStartDate = "";
				posEndDate = "";
			}
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

		public boolean isInputEntered() {
			boolean isInputEnetered = true;
			if (impressionsField.getText().equals("") || posStartDate.equals("") || posEndDate.equals("")) {
				isInputEnetered = false;
			}
			return isInputEnetered;
		}

	}

	public class RemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
