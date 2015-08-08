package CowsAndBullsProject;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HighScores {
	private JFrame scoreFrame;
	private JTextArea scoresArea;
	private JButton closeButton;
	private Connection myConn = null;
	private Statement myStat = null;
	private ResultSet myRes = null;
	private StringBuilder areaContent;

	public HighScores() {
		myConn = null;
		myStat = null;
		myRes = null;
		areaContent = new StringBuilder();
	}

	public void pushToDatabase(String userName, int initialMoves,
			int movesLeft, int timeLeft, int score)
			throws ClassNotFoundException {
		myConn = null;
		myStat = null;
		myRes = null;
		String dbURL = "jdbc:mysql://localhost:3306/cowsandbulls";
		String user = "root";
		String password = "Portal123@";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection(dbURL, user, password);
			myStat = myConn.createStatement();
			int rowsAffected = myStat.executeUpdate("insert into highscores"
					+ "(username,initialMoves, movesLeft, timeLeft, score)"
					+ "values"
					+ String.format("('%s', %d,%d, '%d', %d)", userName,
							initialMoves, movesLeft, timeLeft, score));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createPanel() {
		scoreFrame = new JFrame("High Scores");
		scoreFrame.setSize(500, 300);
		scoreFrame.setLayout(new BorderLayout());
		areaContent.append("username" + "\t" + "initial Moves" + "\t"
				+ "moves Left " + "\t" + "time Left" + "\t" + "score" + "\n");

		scoresArea = new JTextArea(areaContent.toString(), 50, 15);

		JScrollPane vScroll = new JScrollPane(scoresArea);
		vScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scoresArea.setName("textArea");
		scoresArea.setEditable(false);
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				scoreFrame.setVisible(false);
				scoreFrame = null;
				
			}
			
		});
		scoreFrame.add(vScroll, BorderLayout.CENTER);
		scoreFrame.add(closeButton, BorderLayout.SOUTH);
		scoreFrame.setDefaultCloseOperation(scoreFrame.EXIT_ON_CLOSE);
		scoreFrame.setVisible(true);
	}

	public void generateHighScores() throws SQLException {
		createPanel();
		myRes = myStat
				.executeQuery("select * from highscores order by username ");
		areaContent.setLength(0);
		while (myRes.next()) {

			areaContent.append(myRes.getString("username") + "\t"
					+ myRes.getString("initialMoves") + "\t"
					+ myRes.getString("movesLeft") + "\t"
					+ myRes.getString("timeLeft") + "\t"
					+ myRes.getString("score") + "\n");

		}
		scoresArea.append(areaContent.toString());
	}
}
