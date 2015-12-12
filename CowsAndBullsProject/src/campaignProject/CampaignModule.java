package campaignProject;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import campaignProject.GeneralSettingsModule.PositionRenderer;

public class CampaignModule implements ListSelectionListener {
	private JFrame campFrame;
	private String campaignName;
	private JLabel windowName;
	private JLabel campNameLabel;
	private JLabel campaignStartDateLabel;
	private JLabel campaignEndDateLabel;
	private JLabel positionStartDateLabel;
	private JLabel positionEndDateLabel;
	private JTextField campNameField;
	// JPanel initialize
	private JPanel campStartDate;
	private JPanel campEndDate;
	private JPanel posStartDate;
	private JPanel posEndDate;
	private JPanel positionDatesPanel;
	private JPanel positionStartDatePanel;
	private JPanel positionEndDatePanel;
	private JPanel buttonPane;
	private JPanel content;
	private JPanel infoCont;
	private JPanel campDetails;
	private JPanel posDetails;
	// JTextField positionField;
	private JTextField impressionsField;
	// JButtons
	private JButton submitButton;
	private JButton addPositionButton;
	private JButton removeButton;
	// BannerPositionModule banerPos;
	private DateManipulator dates;
	private String[] positionStrings;
	// date pickers
	private DatePicker campStartDatePicker;
	private DatePicker campEndDatePicker;
	private DatePicker posStartDatePicker;
	private DatePicker posEndDatePicker;
	///////////////////////
	private JList list;
	private DefaultListModel<CampaignPositionTableRecord> listModel;
	private static final String removeString = "Del";
	private static final String addString = "Add";
	private String posStartDateString;
	private String posEndDateString;
	private static final int scrollPaneWith = 200;
	private String posStartDateStr;
	private String posEndDateStr;
	private JComboBox<PositionTableRecord> positionList;
	private StringBuilder builder;
	private CampaignDataModule dataModule;
	private boolean isListCleared;
	private String positionName;
	// JPanels

	private static final String DELIMITER = "/ ";
	private String errorMessage = "Please fill all the fields prior to submitting the record";
	private PositionTableConnection databaseConnection;
	private int lastCampaignIndex;

	@SuppressWarnings("unchecked")
	public CampaignModule() throws SQLException {
		dataModule = new CampaignDataModule();
		lastCampaignIndex = dataModule.getLastCampaignIndex();
		builder = new StringBuilder();
		campStartDatePicker = new DatePicker();
		campEndDatePicker = new DatePicker();
		campStartDatePicker.getDatePickerOne().getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub

			}

		});
		campStartDate = campStartDatePicker.getDatesPanel();
		campEndDatePicker.getDatePickerOne().getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				boolean isChanged = true;
				if (campEndDatePicker.getDatePickerOne().getModel().getValue() == null) {
					isChanged = false;
					return;
				} else if (isChanged) {
					if (campStartDatePicker.getDateValue().equals("")) {
						JOptionPane.showMessageDialog(null, PopupMessages.CampaignStartNotEntered.getPopupString(),
								"Value not set", JOptionPane.ERROR_MESSAGE, null);
						// reset the value
						campEndDatePicker.getDatePickerOne().getModel().setValue(null);
					} else {
						if (campEndDatePicker.getSelectedDate().before(campStartDatePicker.getSelectedDate())) {
							JOptionPane.showMessageDialog(null, PopupMessages.CampaignEndBeforeStart.getPopupString(),
									"Incorrect value", JOptionPane.ERROR_MESSAGE, null);
							campEndDatePicker.getDatePickerOne().getModel().setValue(null);
						}
					}
				}
			}

		});
		campEndDate = campEndDatePicker.getDatesPanel();
		posStartDatePicker = new DatePicker();
		posStartDatePicker.getDatePickerOne().getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				boolean isChanged = true;
				if ((posStartDatePicker.getDatePickerOne().getModel().getValue() == null)) {
					isChanged = false;
					return;
				} else if (isChanged) {
					if (campStartDatePicker.getDateValue().equals("")) {
						JOptionPane.showMessageDialog(null, PopupMessages.CampaignStartNotEntered.getPopupString(),
								"Value not set", JOptionPane.ERROR_MESSAGE, null);
						// reset the value
						posStartDatePicker.getDatePickerOne().getModel().setValue(null);

					}
					if (campEndDatePicker.getDateValue().equals("")) {
						JOptionPane.showMessageDialog(null, PopupMessages.CampaignEndNotEntered.getPopupString(),
								"Value not set", JOptionPane.ERROR_MESSAGE, null);
						// reset the value
						posStartDatePicker.getDatePickerOne().getModel().setValue(null);

					} else {
						if (posStartDatePicker.getSelectedDate().before(campStartDatePicker.getSelectedDate())) {
							JOptionPane.showMessageDialog(null,
									PopupMessages.PositonStartDateBeforeCampaign.getPopupString(), "Incorrect Value",
									JOptionPane.ERROR_MESSAGE, null);
							// reset the value
							posStartDatePicker.getDatePickerOne().getModel().setValue(null);
						} else if (posStartDatePicker.getSelectedDate().after(campEndDatePicker.getSelectedDate())) {
							JOptionPane.showMessageDialog(null,
									PopupMessages.PositonStartDateAfterCampaign.getPopupString(), "Incorrect Value",
									JOptionPane.ERROR_MESSAGE, null);
							// reset the value
							posStartDatePicker.getDatePickerOne().getModel().setValue(null);
						}
					}

				}
			}
		});
		posEndDatePicker = new DatePicker();
		posEndDatePicker.getDatePickerOne().getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				boolean isChanged = true;
				if ((posEndDatePicker.getDatePickerOne().getModel().getValue() == null)) {
					isChanged = false;
					return;
				} else if (isChanged) {
					if (campStartDatePicker.getDateValue().equals("")) {
						JOptionPane.showMessageDialog(null, PopupMessages.CampaignStartNotEntered.getPopupString(),
								"Value not set", JOptionPane.ERROR_MESSAGE, null);
						// reset the value
						posStartDatePicker.getDatePickerOne().getModel().setValue(null);

					}
					if (campEndDatePicker.getDateValue().equals("")) {
						JOptionPane.showMessageDialog(null, PopupMessages.CampaignEndNotEntered.getPopupString(),
								"Value not set", JOptionPane.ERROR_MESSAGE, null);
						// reset the value
						posStartDatePicker.getDatePickerOne().getModel().setValue(null);

					}
					if (posStartDatePicker.getDateValue().equals("")) {
						JOptionPane.showMessageDialog(null, PopupMessages.PositionStartNotEntered.getPopupString(),
								"Value not set", JOptionPane.ERROR_MESSAGE, null);
						// reset the value
						posEndDatePicker.getDatePickerOne().getModel().setValue(null);
					} else {
						if (posEndDatePicker.getSelectedDate().before(posStartDatePicker.getSelectedDate())) {
							JOptionPane.showMessageDialog(null,
									PopupMessages.PositionEndDateBeforePositionStartDate.getPopupString(),
									"Incorrect Value", JOptionPane.ERROR_MESSAGE, null);
							// reset the value
							posEndDatePicker.getDatePickerOne().getModel().setValue(null);
						} else if (posEndDatePicker.getSelectedDate().after(campEndDatePicker.getSelectedDate())) {
							JOptionPane.showMessageDialog(null,
									PopupMessages.PositionEndDateAfterCampaignEndDate.getPopupString(),
									"Incorrect Value", JOptionPane.ERROR_MESSAGE, null);
							// reset the value
							posEndDatePicker.getDatePickerOne().getModel().setValue(null);
						}
					}
				}

			}

		});
		posStartDate = posStartDatePicker.getDatesPanel();
		posEndDate = posEndDatePicker.getDatesPanel();
		positionStartDateLabel = new JLabel("Position Start Date:");
		positionEndDateLabel = new JLabel("Position End Date:");
		addPositionButton = new JButton(addString);
		addPositionButton.addActionListener(new AddListener(addPositionButton));
		impressionsField = new JTextField(8);
		impressionsField.setPreferredSize(new Dimension(150, 30));
		impressionsField.setEnabled(false);
		impressionsField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (!isListCleared) {
					if (null == posEndDatePicker) {
						JOptionPane.showMessageDialog(null, PopupMessages.PositionEndNotEntered.getPopupString(),
								"No Value", JOptionPane.ERROR_MESSAGE, null);

					}
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!isListCleared) {
					try {
						int number = Integer.parseInt(impressionsField.getText());
						addPositionButton.setEnabled(true);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, PopupMessages.NotImpressionsEntered.getPopupString(),
								"No Value", JOptionPane.ERROR_MESSAGE, null);
						impressionsField.setText("");
						// impressionsField.requestFocus();
					}

				}
			}

		});
		addPositionButton.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane();
		positionList = new JComboBox<PositionTableRecord>();
		dataModule.fillComboBoxRecords(positionList);
		positionList.setSelectedIndex(0);
		positionList.setPreferredSize(new Dimension(200, 30));
		removeButton = new JButton(removeString);
		removeButton.addActionListener(new RemoveListener());
		removeButton.setEnabled(false);
		content = new JPanel();
		content.setLayout(new BorderLayout());
		windowName = new JLabel("Campaign");
		campaignName = "";
		campDetails = new JPanel();
		infoCont = new JPanel();
		posDetails = new JPanel();
		posDetails.setLayout(new BorderLayout());
		campDetails.setLayout(new GridLayout(5, 2));
		infoCont.setLayout(new BoxLayout(infoCont, BoxLayout.Y_AXIS));
		// label initialize
		campNameLabel = new JLabel("Campaign Name: ");
		campaignStartDateLabel = new JLabel("Campaign Start Date: ");
		campaignEndDateLabel = new JLabel("Campaign End Date: ");
		// TextField initialize
		campNameField = new JTextField(20);
		campNameField.setFocusable(true);
		campNameField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (campNameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, PopupMessages.CampaignNameNotEntered.getPopupString(),
							"No Value", JOptionPane.ERROR_MESSAGE, null);
					campNameField.requestFocus();
				} else {
					impressionsField.setEnabled(true);
				}
			}

		});

		listModel = new DefaultListModel<CampaignPositionTableRecord>();
		list = new JList<CampaignPositionTableRecord>(listModel);
		list.setCellRenderer(new CampaignPositionRenderer());
		list.setOpaque(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		//list.addListSelectionListener(this);
		list.setVisibleRowCount(5);
		///////////////////////
		JScrollPane listScrollPane = new JScrollPane(list);
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout());
		buttonPane.setLayout(new FlowLayout());
		buttonPane.add(removeButton);
		buttonPane.add(Box.createVerticalStrut(5));
		buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPane.add(Box.createVerticalStrut(5));
		buttonPane.add(impressionsField);
		buttonPane.add(positionList);
		buttonPane.add(addPositionButton);
		posDetails.add(buttonPane, BorderLayout.NORTH);
		posDetails.add(listScrollPane, BorderLayout.CENTER);
		// Container adding
		campDetails.add(campNameLabel);
		campDetails.add(campNameField);
		campDetails.add(campaignStartDateLabel);
		campDetails.add(campStartDate);
		campDetails.add(campaignEndDateLabel);
		campDetails.add(campEndDate);
		campDetails.add(positionStartDateLabel);
		campDetails.add(posStartDate);
		campDetails.add(positionEndDateLabel);
		campDetails.add(posEndDate);
		infoCont.add(campDetails);
		submitButton = new JButton("Submit");
		content.add(infoCont, BorderLayout.NORTH);
		content.add(posDetails, BorderLayout.CENTER);
		content.add(submitButton, BorderLayout.SOUTH);
		//
		// posDetails.add(buttonPane);
		// infoCont.add(posDetails);

	}

	public static void main(String[] args) {

	}

	public JPanel getContent() {
		return content;
	}

	public String getCampaignName() {
		return campNameField.getText();
	}

	public String getPositionDates(String type) {
		if (type.equals("start")) {
			return posStartDatePicker.getDateValue();
		} else if (type.equals("end")) {
			return posEndDatePicker.getDateValue();
		}
		return null;
	}

	public void generateGUI() {
		campFrame = new JFrame("Campaign Module");
		campFrame.setSize(500, 450);
		campFrame.setLocation(410, 0);
		campFrame.setResizable(false);
		campFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		campFrame.getContentPane().add(content);
		campFrame.setVisible(true);
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

			if (isInputEntered()) {
				positionName = ((PositionTableRecord) (positionList.getSelectedItem())).getPositionName();
				int positionId = positionList.getSelectedIndex();
				int impressions = Integer.parseInt(impressionsField.getText());
				// tests delete when working
				System.out.println(positionName);
				CampaignPositionTableRecord newRecord = new CampaignPositionTableRecord((++lastCampaignIndex), positionId,
						posStartDatePicker.getSelectedDate(), posEndDatePicker.getSelectedDate(), impressions);
				listModel.addElement(newRecord);
				isListCleared = false;
				removeButton.setEnabled(true);
				// reset values
				impressionsField.setText("");
				impressionsField.setToolTipText("Impressions");
				//positionList.setSelectedIndex(0);
				posStartDatePicker.getDatePickerOne().getModel().setValue(null);
				posEndDatePicker.getDatePickerOne().getModel().setValue(null);
				newRecord = null;
				
			} else {
				JOptionPane.showMessageDialog(null, errorMessage, "Settings input error", JOptionPane.ERROR_MESSAGE,
						null);
				impressionsField.setText("");
				posStartDatePicker.getDatePickerOne().getModel().setValue(null);
				posEndDatePicker.getDatePickerOne().getModel().setValue(null);
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
			if (campNameField.getText().equals("") || campStartDatePicker.getDateValue().equals("")
					|| campEndDatePicker.getDateValue().equals("") || posStartDatePicker.getDateValue().equals("")
					|| posEndDatePicker.getDateValue().equals("") || impressionsField.getText().equals("")) {
				isInputEnetered = false;
			}
			return isInputEnetered;
		}

	}

	public class RemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			/*
			 * int index = list.getSelectedIndex(); listModel.remove(index);
			 * 
			 * int size = listModel.getSize();
			 * 
			 * if (size == 0) { // Nobody's left, disable firing.
			 * removeButton.setEnabled(false);
			 * 
			 * } else { // Select an index. if (index == listModel.getSize()) {
			 * // removed item in last position index--; }
			 * 
			 * list.setSelectedIndex(index); list.ensureIndexIsVisible(index); }
			 */

			int index = positionList.getSelectedIndex();
			int size = listModel.getSize();
			listModel.remove(index);
			size = listModel.getSize();
			if (size == 0) { // Nobody's left, disable firing.
				isListCleared = true;
				removeButton.setEnabled(false);
				campNameField.requestFocus();

			} else { // Select an index.
				if (index == listModel.getSize()) {
					// removed item in last position
					index--;
				}

				// list.setSelectedIndex(index);
				// list.ensureIndexIsVisible(index);

			}

		}
	}

	public class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int size = listModel.getSize();
			if (campNameField.getText().equals("") || (null == campStartDatePicker) || (null == campEndDatePicker)) {
				JOptionPane.showMessageDialog(null, PopupMessages.CampDetailsNotEntered, "campaign details not entered",
						JOptionPane.ERROR_MESSAGE, null);
				return;

			} else if (size == 0) {
				JOptionPane.showMessageDialog(null, PopupMessages.NoPositionsEntered, "no positions entered",
						JOptionPane.ERROR_MESSAGE, null);
				return;
			} else {
				String campaignName = campNameField.getText();
				Date newCampStartDate = campStartDatePicker.getSelectedDate();
				try {
					if (!dataModule.isCampaignExist(campaignName, newCampStartDate)) {
						Date newCampEndDate = campEndDatePicker.getSelectedDate();
						CampaignTableRecord newCampaign = new CampaignTableRecord(campaignName, newCampStartDate,
								newCampEndDate);
						ArrayList<CampaignPositionTableRecord> records = Collections.list(listModel.elements());
						dataModule.sentAddCampRecord(newCampaign);
						dataModule.sentNewCampaignPositionRecords(records);

					} else {

						JOptionPane.showMessageDialog(null, PopupMessages.CampaignAlreadyExists,
								"campaign exists error", JOptionPane.ERROR_MESSAGE, null);
						campNameField.setText("");
						impressionsField.setText("");
						campStartDatePicker.getDatePickerOne().getModel().setValue(null);
						campEndDatePicker.getDatePickerOne().getModel().setValue(null);
						posStartDatePicker.getDatePickerOne().getModel().setValue(null);
						posEndDatePicker.getDatePickerOne().getModel().setValue(null);
						campNameField.requestFocus();
					}
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			/*
			 * if(campName,campstartDate,campEndDate != null)
			 * if(!positionList.isEmpty) create a new CampaignPositionRecord;
			 * send it to CampaignPosition database for (i =0;
			 * i<posList.size;i++){ int positionId = PositionCash.getID(); int
			 * campaignId = CampaignTableConnection.getId();
			 * CamapaignPositionRecord current = new
			 * CamapaignPositionRecord(campaignId,positionId,t) }
			 */

		}

	}

	public class CampaignPositionRenderer extends JTextField implements ListCellRenderer<CampaignPositionTableRecord> {

		@Override
		public Component getListCellRendererComponent(JList<? extends CampaignPositionTableRecord> list,
				CampaignPositionTableRecord record, int index, boolean isSelected, boolean cellHasFocus) {
			String campaignPositionRecord = record.getCampaignPositionRecord(positionName);
			setText(campaignPositionRecord);
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
