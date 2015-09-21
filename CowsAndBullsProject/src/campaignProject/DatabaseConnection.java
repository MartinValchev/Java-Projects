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
	private String dbURL = "jdbc:mysql://localhost:3306/campaignproject";
	private String user = "root";
	private String password = "Portal123@";
	private String addStatement = "INSERT INTO settings_table (Position,ImpressionsLimit)" + "VALUES" + "(?,?)";

	public DatabaseConnection() {
		myConn = null;
		myStat = null;
		myRes = null;
		arrayList = new ArrayList<String>();
	}

	// Settings database
	public void addToSettingsTable(String newPosition, int impressLimit) throws ClassNotFoundException {
		myConn = null;
		myStat = null;
		myRes = null;

		ResultSet rs = null;
		try (Connection myConn = DriverManager.getConnection(dbURL, user, password);
				PreparedStatement stmt = myConn.prepareStatement(addStatement, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, newPosition);
			stmt.setInt(2, impressLimit);
			stmt.execute();
			// Tours.displayData(rs);

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void removeSettingsRecord(String positionName, int impressions) {
		String delStatement = "delete Position, ImpressionsLimit FROM settings_table "
				+ "WHERE Position = ? AND ImpressionsLimit = ?";

		try (Connection myConn = DriverManager.getConnection(dbURL, user, password);
				PreparedStatement stmt = myConn.prepareStatement(delStatement, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, positionName);
			stmt.setInt(2, impressions);
			stmt.execute();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public ArrayList<String> pullFromSettingsTable() throws SQLException {
		Connection myConn = DriverManager.getConnection(dbURL, user, password);
		Statement myStat = myConn.createStatement();
		ResultSet myRes = myStat.executeQuery(" select * from settings_table ");
		while (myRes.next()) {
			arrayList.add(myRes.getString("ImpressionsLimit") + '/' + myRes.getString("Position"));

		}
		return arrayList;
	}
// Campaign Database
	public void addToCampaignTable(String campaignName, Date campaignStartDate, Date campaignEndDate)
			throws ClassNotFoundException {
		myConn = null;
		myStat = null;
		myRes = null;

		ResultSet rs = null;
		try (Connection myConn = DriverManager.getConnection(dbURL, user, password);
				PreparedStatement stmt = myConn.prepareStatement(addStatement, ResultSet.TYPE_SCROLL_INSENSITIVE,
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

	public void removeCampaignRecord(String positionName, Date campaignStartDate) {
		String delStatement = "delete CampaignName, CampaignStartDate, CampaignEndDate FROM campaign_table "
				+ "WHERE CampaignName = ? AND CampaignStartDate = ?";

		try (Connection myConn = DriverManager.getConnection(dbURL, user, password);
				PreparedStatement stmt = myConn.prepareStatement(delStatement, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, positionName);
			stmt.setDate(2, campaignStartDate);
			stmt.execute();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public ArrayList<String> pullFromCampaignTable() throws SQLException {
		Connection myConn = DriverManager.getConnection(dbURL, user, password);
		Statement myStat = myConn.createStatement();
		ResultSet myRes = myStat.executeQuery(" select * from campaign_table ");
		while (myRes.next()) {
			arrayList.add(myRes.getString("CampaignName") + '/' + myRes.getDate("CampaignStartDate") + '/'
					+ myRes.getDate("CampaignEndDate"));

		}
		return arrayList;
	}
// Position Database
	public void addToPositionTable(String campaignName, String position, Date positionStartDate,
			Date positionEndDate, int dailyImpressions) throws ClassNotFoundException {
		myConn = null;
		myStat = null;
		myRes = null;

		ResultSet rs = null;
		try (Connection myConn = DriverManager.getConnection(dbURL, user, password);
				PreparedStatement stmt = myConn.prepareStatement(addStatement, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, campaignName);
			stmt.setString(2, position);
			stmt.setDate(3, positionStartDate);
			stmt.setDate(4, positionEndDate);
			stmt.setInt(5, dailyImpressions);
			stmt.execute();
			// Tours.displayData(rs);

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void removePositionRecord(String positionName, Date startDate) {
		String delStatement = "delete Position, ImpressionsLimit FROM settings_table "
				+ "WHERE Position = ? AND PositioStartDate = ?";

		try (Connection myConn = DriverManager.getConnection(dbURL, user, password);
				PreparedStatement stmt = myConn.prepareStatement(delStatement, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, positionName);
			stmt.setDate(2, startDate);
			stmt.execute();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public ArrayList<String> pullFromPositionTable() throws SQLException {
		Connection myConn = DriverManager.getConnection(dbURL, user, password);
		Statement myStat = myConn.createStatement();
		ResultSet myRes = myStat.executeQuery(" select * from campaign_table ");
		while (myRes.next()) {
			arrayList.add(myRes.getString("Position") + '/' + myRes.getDate("PositionStartDate") + '/'
					+ myRes.getDate("PositionEndDate") + '/' + myRes.getInt("DailyImpressions"));

		}
		return arrayList;
	}
}
