package campaignProject;

import java.util.ArrayList;
import java.util.List;

public class GeneralSettings {
	private List<String> websites;
	//!it should not be List of Strings but of Class BanerPosition
	private List<String> positions;
	private List<Integer> positionLimit;
	public GeneralSettings() {
		websites = new ArrayList<String>();
		positions= new ArrayList<String>();
		positionLimit = new ArrayList<Integer>();
	}
	public void setWebsite(String websiteName){
		websites.add(websiteName);
	}
	public List<String> getWebsites(){
		return websites;
	}
	public List<String> getPositions(){
		return positions;
	}
}
