package campaignProject;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateManipulator {
	private String campaignName;
	private Calendar startDate;
	private Calendar endDate;
	private String campStart;
	private String campEnd;
	private static final int DATE_LENGTH = 3;
	private int currentDay;
	private int currentMonth;
	private int currentYear;
	private int[] startArrayDate;
	private int[] endArrayDate;

	public DateManipulator(String newStart, String newEnd) {
		startArrayDate = new int[DATE_LENGTH];
		endArrayDate = new int[DATE_LENGTH];
		campStart = newStart;
		campEnd = newEnd;
		startArrayDate = convertDate(campStart);
		endArrayDate = convertDate(campEnd);
		startDate = getCalendar(startArrayDate);
		endDate = getCalendar(endArrayDate);
	}

	public int[] convertDate(String dateProvided) {
		int[] dateArray = new int[DATE_LENGTH];
		String[] temp;
		temp = dateProvided.split("/");
		for (int i = 0; i < dateArray.length; i++) {
			dateArray[i] = Integer.parseInt(temp[i]);
		}
		return dateArray;
	}

	public Calendar getCalendar(int[] dateArray) {
		Calendar current = new GregorianCalendar();
		current.set(Calendar.DAY_OF_MONTH, dateArray[0]);
		current.set(Calendar.MONTH, dateArray[1]);
		current.set(Calendar.YEAR, dateArray[2]);
		return current;
	}

	public Calendar getDates(String dateName) {
		if (dateName.equals("start")) {
			return startDate;
		} else if (dateName.equals("end")) {
			return endDate;
		}
		return null;
	}

	public Calendar getPeriod() {
		Calendar period = new GregorianCalendar();
		int periodDay = endDate.get(endDate.DAY_OF_MONTH)
				- startDate.get(startDate.DAY_OF_MONTH);
		int periodMonth = endDate.get(endDate.MONTH)
				- startDate.get(startDate.MONTH);
		int periodYear = endDate.get(endDate.YEAR)
				- startDate.get(startDate.YEAR);
		period.set(Calendar.DAY_OF_MONTH, periodDay);
		period.set(Calendar.MONTH, periodMonth);
		period.set(Calendar.YEAR, periodYear);
		return period;
	}

}
