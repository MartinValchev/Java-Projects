package campaignProject;

import java.util.Calendar;

public class DateManipulator {
	private String campaignName;
	private Calendar startDate;
	private Calendar endDate;
	private String campStart;
	private String campEnd;
	private static final int DATE_LENGTH = 3;
	int[] startArrayDate;
	int[] endArrayDate;

	public DateManipulator(String newStart, String newEnd) {
		startArrayDate = new int[DATE_LENGTH];
		endArrayDate = new int[DATE_LENGTH];
		this.startDate = startDate;
		campStart = newStart;
		campEnd = newEnd;
		convertDate(newStart,startArrayDate);
		convertDate(newEnd,endArrayDate);
		setCalendarDate(startArrayDate, startDate);
		setCalendarDate(endArrayDate, endDate);
	}

	public void convertDate(String dateProvided, int[] dateArray) {
		startArrayDate = new int[DATE_LENGTH];
		String[] temp;
		temp = dateProvided.split("\\");
		for (int i = 0; i < dateArray.length; i++) {
			dateArray[i] = Integer.parseInt(temp[i]);
		}
	}
	public void setCalendarDate(int[] dateArray, Calendar calendar){
		int dayDelta =dateArray[0]-(calendar.getInstance().DAY_OF_MONTH);
		int monthDelta =dateArray[1]-(calendar.getInstance().MONTH);
		int yearDelta = dateArray[2]-calendar.getInstance().YEAR;
		calendar.roll(Calendar.DAY_OF_MONTH, dateArray[0]);
		calendar.add(Calendar.DAY_OF_MONTH, dayDelta);
		calendar.add(Calendar.MONTH, monthDelta);
		calendar.add(Calendar.YEAR, yearDelta);
	}
	public Calendar getDates(String dateName){
		if(dateName.equals("start")){
			return startDate;
		}
		else if (dateName.equals("end")){
			return endDate;
		}
		return null;
	}
}
