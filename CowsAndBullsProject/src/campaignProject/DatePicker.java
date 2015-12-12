package campaignProject;

import java.awt.BorderLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DatePicker {
	private static JFrame dateFrame;
	private JPanel datesPanel;
	private JDatePanelImpl datePanelOne;
	private JDatePickerImpl datePickerOne;
	boolean changed;
	private SqlDateModel modelOne;
	DateLabelFormatter dateFormater;
	Date selectedDate;

	public DatePicker() {

		datesPanel = new JPanel();
		// date one adding
		modelOne = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanelOne = new JDatePanelImpl(modelOne, p);
		dateFormater = new DateLabelFormatter();
		datePickerOne = new JDatePickerImpl(datePanelOne, dateFormater);
		//changed = false;
		/*
		 * datePickerOne.getModel().addChangeListener(new ChangeListener(){
		 * 
		 * @Override public void stateChanged(ChangeEvent arg0) { if
		 * (datePickerOne.getModel().getValue() == null) { changed = false;
		 * return; } if (!changed) {
		 * System.out.println(datePickerOne.getModel().getValue());
		 * datePickerOne.getModel().setValue(null); changed = true; }
		 * 
		 * } });
		 */
		datesPanel.add(datePickerOne);

	}

	public Date getSelectedDate() {
		java.sql.Date selectedDate = (java.sql.Date) datePickerOne.getModel().getValue();
		return selectedDate;
	}

	public JPanel getDatesPanel() {
		return datesPanel;
	}

	public JDatePickerImpl getDatePickerOne() {
		return datePickerOne;
	}

	public void generateGUI() {
		dateFrame = new JFrame("General Settings");
		dateFrame.setSize(300, 100);
		dateFrame.setResizable(false);
		dateFrame.setLocation(410, 0);
		dateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dateFrame.setLayout(new BorderLayout());
		DatePicker datePicker = new DatePicker();
		dateFrame.add(datesPanel);
		DatePicker del = new DatePicker();
		dateFrame.setVisible(true);
	}

	public String getDateValue() {
		Date selected = getSelectedDate();
		Calendar call = Calendar.getInstance();
		call.setTime(selected);
		return new DateLabelFormatter().valueToString(call);

	}

	public class DateLabelFormatter extends AbstractFormatter {

		private String datePattern = "dd-MM-yyyy";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws java.text.ParseException {
			// return dateFormatter.parseObject(text);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormatter.parse(text));
			return calendar;
		}

		@Override
		public String valueToString(Object value) throws ParseException {

			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}
}
