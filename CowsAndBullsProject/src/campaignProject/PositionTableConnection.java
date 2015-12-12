package campaignProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PositionTableConnection {
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
	private char delimiter = '-';

	public PositionTableConnection() {

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

	public void removePositionRecord(String positionName,int impessionsLimit) {
		try (PreparedStatement stmt = myConn.prepareStatement(statements.getDelPositionStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, positionName);
			stmt.setInt(2, impessionsLimit);
			stmt.execute();
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

}
