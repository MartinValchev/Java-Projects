package campaignProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {
	private Connection myConn;
	private Statement myStat;
	private ResultSet myRes;
	private ArrayList<String> arrayList;
	private ArrayList<String> settingsPositions;
	private ArrayList<Integer> impressionLimit;
	private ArrayList<PositionTableRecord> positionList;
	private ArrayList<CampaignTableRecord> campaignList;
	private String dbURL = "jdbc:mysql://localhost:3306/campaignproject";
	private String user = "root";
	private String password = "Portal123@";
	private DatabaseConnectionStatements statements;
	private CampaignCash campaignCash;
	private char delimiter = '-';

	public DatabaseConnection() {

		try {
			myConn = DriverManager.getConnection(dbURL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		myStat = null;
		myRes = null;
		positionList = new ArrayList<PositionTableRecord>();
		campaignList = new ArrayList<CampaignTableRecord>();
		//
		arrayList = new ArrayList<String>();
		statements = new DatabaseConnectionStatements();
		campaignCash = new CampaignCash();
	}

	// position table
	public void addToPositionTable(String newPosition, int impressLimit) throws ClassNotFoundException {
		try (PreparedStatement stmt = myConn.prepareStatement(statements.getAddPositionStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, newPosition);
			stmt.setInt(2, impressLimit);
			stmt.execute();
			// Tours.displayData(rs);

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void removePositionRecord(String positionName,Date positionStartDate) {
		try (PreparedStatement stmt = myConn.prepareStatement(statements.getDelPositionStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, positionName);
			stmt.execute();
			int positionId = getPositionIdFromName(positionName);
			removeFromCampaignPositionTable(positionId,positionStartDate);
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public ArrayList<PositionTableRecord> pullFromPositionTable() throws SQLException {
		Statement myStat = myConn.createStatement();
		ResultSet myRes = myStat.executeQuery(" select * from position ");
		while (myRes.next()) {
			PositionTableRecord currentRecord = new PositionTableRecord(myRes.getString("position_name"),
					myRes.getInt("impressions_limit"));
			positionList.add(currentRecord);

		}
		return positionList;
	}

	// used for PositionCash
	public ArrayList<String> getPositionName() {
		ArrayList<String> positionNameList = new ArrayList<String>();
		try {
			ResultSet myRes = myStat.executeQuery(" select position_name from position ");
			while (myRes.next()) {
				String current = myRes.getString("position_name");
				positionNameList.add(current);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return positionNameList;
	}
	public ArrayList<String> getCampaignName() {
		ArrayList<String> campaignNameList = new ArrayList<String>();
		try {
			ResultSet myRes = myStat.executeQuery(" select campaign_name from campaign ");
			while (myRes.next()) {
				String current = myRes.getString("campaign_name");
				campaignNameList.add(current);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return campaignNameList;
	}
	// used for PositionCash
	public ArrayList<Integer> getPositionId() {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			ResultSet myRes = myStat.executeQuery(" select id from position ");
			while (myRes.next()) {
				int current = myRes.getInt("id");
				idList.add(current);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idList;
	}
	public ArrayList<Integer> getCampaignId() {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			ResultSet myRes = myStat.executeQuery(" select id from campaign ");
			while (myRes.next()) {
				int current = myRes.getInt("id");
				idList.add(current);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idList;
	}

	public void addToCampaignTable(String campaignName, Date campaignStartDate, Date campaignEndDate)
			throws ClassNotFoundException {
		try (PreparedStatement stmt = myConn.prepareStatement(statements.getAddCampStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, campaignName);
			stmt.setDate(2, campaignStartDate);
			stmt.setDate(3, campaignEndDate);
			stmt.execute();
			// Tours.displayData(rs);

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void updateCampaignRecord(String newCampaignName, Date newCampaignStartDate, Date newCampaignEndDate) {

		try (PreparedStatement stmt = myConn.prepareStatement(statements.getUpdateCampStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, newCampaignName);
			stmt.setDate(2, newCampaignStartDate);
			stmt.setDate(3, newCampaignEndDate);
			stmt.execute();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public ArrayList<CampaignTableRecord> pullFromCampaignTable() throws SQLException {

		Statement myStat = myConn.createStatement();
		ResultSet myRes = myStat.executeQuery(" select * from campaign ");
		while (myRes.next()) {
			CampaignTableRecord campaignRecord = new CampaignTableRecord(myRes.getString("campaign_name"),
					myRes.getDate("campaign_start_date"), myRes.getDate("campaign_end_date"));

			campaignList.add(campaignRecord);
		}
		return campaignList;
	}

	// Position Database

	public void removeCampaignRecord(String campaignName) {
		try (PreparedStatement stmt = myConn.prepareStatement(statements.getDeleteCampStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, campaignName);
			stmt.execute();
			int campaignId = campaignCash.getCampaignId(campaignName);
			removeFromCampaignPositionTable(campaignId);
			
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void addToCampaignPositionTable(int campaignId, int positionId, Date positionStartDate, Date positionEndDate,
			int dailyImpressions) {
		try (PreparedStatement stmt = myConn.prepareStatement(statements.getAddCampaignPositionStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setInt(1, campaignId);
			stmt.setInt(2, positionId);
			stmt.setDate(3, positionStartDate);
			stmt.setDate(4, positionEndDate);
			stmt.setInt(5, dailyImpressions);
			stmt.execute();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	public void removeFromCampaignPositionTable(int positionId,Date positionStartDate){
		try (PreparedStatement stmt = myConn.prepareStatement(statements.getDeleteCampaignPositionStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setInt(1, positionId);
			stmt.setDate(2, positionStartDate);
			stmt.execute();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	public void removeFromCampaignPositionTable(int campaignId){
		try (PreparedStatement stmt = myConn.prepareStatement(statements.getDeleteCampaignPositionStatement2(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setInt(1, campaignId);
			stmt.execute();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	public int getPositionIdFromName(String posName){
		int id = 0;
		try (PreparedStatement stmt = myConn.prepareStatement(statements.getSelectPositionIdStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, posName);
			
			myRes =stmt.executeQuery(statements.getSelectPositionIdStatement());
			while(myRes.next()){
				id = myRes.getInt("id");
			}
			return id;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return -1;
	}

}
