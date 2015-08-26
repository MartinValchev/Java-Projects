package campaignProject;

import java.util.Calendar;

public class Position {
	private String positionName;
	DateManipulator dates;
	Calendar positionStartDate;
	Calendar positionEndDate;
	int impressions;
	public int getImpressions() {
		return impressions;
	}
	public void setImpressions(int newImpressions) {
		this.impressions = newImpressions;
	}
	public Position(String posName, String startDate,String endDate) {
		positionName = posName;
		dates = new DateManipulator(startDate, endDate);
		positionStartDate = dates.getDates("start");
		positionEndDate = dates.getDates("end");
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Calendar getStartDateCalendar() {
		return positionStartDate;
	}
	public void setStartDateCalendar(Calendar startDateCalendar) {
		this.positionStartDate = startDateCalendar;
	}
	public Calendar getEndDateCalendar() {
		return positionEndDate;
	}
	public void setEndDateCalendar(Calendar endDateCalendar) {
		this.positionEndDate = endDateCalendar;
	}
}
