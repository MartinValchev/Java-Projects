package campaignProject;

public class Website {
	private String websiteName;
	private int positionsLimit;
	private int webSiteImpressions;
	
	
	public Website(String name, int newLimit,int siteImpressions ) {
		websiteName = name;
		positionsLimit = newLimit;
		webSiteImpressions =siteImpressions; 
	}
	public int getPositionsLimit() {
		return positionsLimit;
	}
	public void setPositionsLimit(int positionsLimit) {
		this.positionsLimit = positionsLimit;
	}
	public void setName(String newName){
		websiteName =  newName;
	}
	public String getName(){
		return websiteName;
	}
	public int getWebSiteImpressions() {
		return webSiteImpressions;
	}
	public void setWebSiteImpressions(int webSiteImpressions) {
		this.webSiteImpressions = webSiteImpressions;
	}
}
