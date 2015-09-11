package campaignProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class delete {
	JFrame dateFrame;

	public delete() {
		dateFrame = new JFrame("General Settings");
		dateFrame.setSize(450, 100);
		dateFrame.setResizable(false);
		dateFrame.setLocation(410, 0);
		dateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dateFrame.setLayout(new BorderLayout());
		JPanel datesPanel = new JPanel();
		datesPanel.setLayout(new FlowLayout());
		//date one
		UtilDateModel modelOne = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelOne = new JDatePanelImpl(modelOne, p);
		DateLabelFormatter dateFormater = new DateLabelFormatter();
		JDatePickerImpl datePickerOne = new JDatePickerImpl(datePanelOne, dateFormater);
		// date two
		UtilDateModel modelTwo = new UtilDateModel();
		JDatePanelImpl datePanelTwo = new JDatePanelImpl(modelOne, p);
		JDatePickerImpl datePickerTwo = new JDatePickerImpl(datePanelTwo, dateFormater);
		datesPanel.add(datePickerOne);
		datesPanel.add(datePickerTwo);
		dateFrame.add(datesPanel);
		dateFrame.setVisible(true);
	}

	public static void main(String[] args) {
		delete del = new delete();

	}

	public class DateLabelFormatter extends AbstractFormatter {

		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws java.text.ParseException {
			return dateFormatter.parseObject(text);
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
