package campaignProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Properties;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.sun.xml.internal.ws.api.Component;


public class DatePicker {
	private static JFrame dateFrame;
	private static JPanel datesPanel;
	private JDatePanelImpl datePanelOne;
	private JDatePickerImpl datePickerOne ;
	Date selectedDate;
	public DatePicker() {
		
		datesPanel = new JPanel();
		//date one adding
		SqlDateModel modelOne = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanelOne = new JDatePanelImpl(modelOne, p);
		DateLabelFormatter dateFormater = new DateLabelFormatter();
		datePickerOne = new JDatePickerImpl(datePanelOne, dateFormater);
		datesPanel.add(datePickerOne);
		
		
	}
	public Date getSelectedDate(){
		java.sql.Date selectedDate = (java.sql.Date) datePickerOne.getModel().getValue();
		return selectedDate;
	}
	public JPanel getDatesPanel(){
		return datePickerOne;
	}
	public Object getDatePickerOne(){
		return datePickerOne;
	}
	public static void main(String[] args) {
		dateFrame = new JFrame("General Settings");
		dateFrame.setSize(450, 100);
		dateFrame.setResizable(false);
		dateFrame.setLocation(410, 0);
		dateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dateFrame.setLayout(new BorderLayout());
		DatePicker datePicker = new DatePicker();
		dateFrame.add(datesPanel);
		DatePicker del = new DatePicker();
		dateFrame.setVisible(true); 
		//DatePicker.DateLabelFormatter formatter = datePicker.new DateLabelFormatter();
		//Date selected = datePicker.getSelectedDate();
		//Calendar call = Calendar.getInstance();
		//call.setTime(selected);
		//DatePicker.DateLabelFormatter formatter = datePicker.new DateLabelFormatter();
		//System.out.println(formatter.valueToString(call));
	}
	public String getDateValue(){
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
			//return dateFormatter.parseObject(text);
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
