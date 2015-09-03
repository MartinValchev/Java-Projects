package campaignProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {
	private Connection myConn;
	private Statement myStat;
	private ResultSet myRes;
	private ArrayList<String> arrayList;
	public DatabaseConnection() {
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
		String dbURL = "jdbc:mysql://localhost:3306/campaignproject";
		String user = "root";
		String password = "Portal123@";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection(dbURL, user, password);
			myStat = myConn.createStatement();
			int rowsAffected = myStat.executeUpdate("insert into settings_table"
					+ "(Position, Impressions Limit)"
					+ "values"
					+ String.format("('%s', %d)", newPosition,
							impressLimit));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> pullFromSettingsDatabase() throws SQLException{
		myRes = myStat
				.executeQuery("select * from settings_table order by Position ");
		
		while (myRes.next()) {

			arrayList.add(myRes.getString("Position") + '/'
					+ myRes.getString("Impressions Limit"));
					

		}
		return arrayList;
	}
}
