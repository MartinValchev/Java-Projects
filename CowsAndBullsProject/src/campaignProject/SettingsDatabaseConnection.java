package campaignProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SettingsDatabaseConnection {
	private Connection myConn;
	private Statement myStat;
	private ResultSet myRes;
	private ArrayList<String> arrayList;
	private String dbURL = "jdbc:mysql://localhost:3306/campaignproject";
	private String user = "root";
	private String password = "Portal123@";
	private String addStatement = "INSERT INTO settings_table (Position,ImpressionsLimit)"+
		"VALUES" + "(?,?)";

	// method for adding data to settings table
	// method for retrieving data from settings table
	public SettingsDatabaseConnection() {
		myConn = null;
		myStat = null;
		myRes = null;
		arrayList = new ArrayList<String>();
	}

	// add data to settings table on Save Settings button clicked
	public void addToSettingsDatabase(String newPosition, int impressLimit)
			throws ClassNotFoundException {
		myConn = null;
		myStat = null;
		myRes = null;
		
		ResultSet rs = null;
		try (Connection myConn = DriverManager.getConnection(dbURL, user,
				password);
				PreparedStatement stmt = myConn.prepareStatement(addStatement,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
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

		try (Connection myConn = DriverManager.getConnection(dbURL, user,
				password);
				PreparedStatement stmt = myConn.prepareStatement(delStatement,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			stmt.setString(1, positionName);
			stmt.setInt(2, impressions);
			stmt.execute();
			

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public ArrayList<String> pullFromSettingsDatabase() throws SQLException {
		Connection myConn = DriverManager.getConnection(dbURL, user,
				password);
		Statement myStat = myConn.createStatement();
		ResultSet myRes = myStat.
				executeQuery(" select * from settings_table ");
			while (myRes.next()) {
			arrayList.add(myRes.getString("ImpressionsLimit") + '/'
					+ myRes.getString("Position"));

		}
		return arrayList;
	}
}
