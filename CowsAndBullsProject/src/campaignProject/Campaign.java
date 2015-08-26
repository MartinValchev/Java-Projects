package campaignProject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Campaign {
	private String campaignName;
	Calendar campaignStart;
	Calendar campaignEnd;
	DateManipulator dates;
	List<Position> positions;
	List<Website> websites;
	private int campaignImpressions;
	public Campaign(String campStartDate,String campEndDate,int newImpressions) {
		dates = new DateManipulator(campStartDate, campEndDate);
		campaignStart =dates.getDates("start");
		campaignEnd =dates.getDates("end");
		positions = new ArrayList<Position>();
		websites = new ArrayList<Website>(); 
		campaignImpressions = newImpressions;
	}
	public void addWebsite(Website site){
		Website current = site;
		websites.add(current);
	}
	public Website getSpecWebsite(String websiteName){
		for (Website site: websites ){
			if(site.getName().equals(websiteName)){
				return site;
			}
		}
		return null;
	}
	public void addPosition(Position newPos){
		Position current = newPos;
		positions.add(current);
	}
	public Position getSpecPosition(String posName){
		for (Position pos: positions){
			if(pos.getPositionName().equals(posName)){
				return pos;
			}
		}
		return null;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public Calendar getCampaignStart() {
		return campaignStart;
	}
	public void setCampaignStart(Calendar campaignStart) {
		this.campaignStart = campaignStart;
	}
	public Calendar getCampaignEnd() {
		return campaignEnd;
	}
	public void setCampaignEnd(Calendar campaignEnd) {
		this.campaignEnd = campaignEnd;
	}
	public int getCampaignImpressions() {
		return campaignImpressions;
	}
	public void setCampaignImpressions(int campaignImpressions) {
		this.campaignImpressions = campaignImpressions;
	}
}
