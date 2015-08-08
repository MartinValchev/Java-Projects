package CowsAndBullsProject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FinalMessage {
	private Container finalGameCont;
	private JLabel scoreLabel;
	private JLabel loggedOnLabel;
	private JTextField finalScoreField;
	private JTextField userLoggedField;
	private JPanel gamePanel;
	private JPanel userPanel;
	private JPanel scorePanel;
	private JButton newGameButton;
	private JButton quitGameButton;
	private JButton highScoresButton;
	private JButton switchUserButton;
	private JFrame frame;
	private HighScores highScores;
	CandBMainPanel mainPanel;

	public FinalMessage(CandBMainPanel mainPanel, HighScores highScores) {
		this.mainPanel = mainPanel;
		this.highScores = highScores;
		frame = new JFrame();
		frame.setSize(300, 180);
		frame.setResizable(false);
		finalGameCont = new Container();
		finalGameCont.setLayout(new BorderLayout());
		scorePanel = new JPanel();
		scorePanel.setLayout(new FlowLayout());
		scoreLabel = new JLabel("Your Score is: ");
		finalScoreField = new JTextField(6);
		finalScoreField.setName("FinalScoreField");
		finalScoreField.setEditable(false);
		finalScoreField.setText(Integer.toString(mainPanel.getFinalScore()));
		scorePanel.add(scoreLabel);
		scorePanel.add(finalScoreField);
		// game panel assingnments
		gamePanel = new JPanel();
		newGameButton = new JButton("New Game");
		newGameButton.setName("NewGameButton");
		newGameButton.addActionListener(new NewGameButtonListener());
		quitGameButton = new JButton("Quit Game");
		quitGameButton.setName("QuitGameButton");
		quitGameButton.addActionListener(new QuitButtonListener());
		highScoresButton = new JButton("High Scores");
		highScoresButton.setName("HighScoresButton");
		highScoresButton.addActionListener(new HighScoresListener());
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(newGameButton, BorderLayout.NORTH);
		gamePanel.add(quitGameButton, BorderLayout.CENTER);
		gamePanel.add(highScoresButton, BorderLayout.SOUTH);
		// user assingments
		userPanel = new JPanel();
		userPanel.setLayout(new FlowLayout());
		loggedOnLabel = new JLabel("Logged on:");
		userLoggedField = new JTextField(8);
		userLoggedField.setName("UserLoggedField");
		userLoggedField.setEditable(false);
		userLoggedField.setText(mainPanel.getUserName());
		switchUserButton = new JButton("switch user");
		switchUserButton.setName("SwitchUserButton");
		switchUserButton.addActionListener(new SwitchUserListener());
		userPanel.add(loggedOnLabel);
		userPanel.add(userLoggedField);
		userPanel.add(switchUserButton);
		// add panels
		finalGameCont.add(scorePanel, BorderLayout.NORTH);
		finalGameCont.add(gamePanel, BorderLayout.CENTER);
		finalGameCont.add(userPanel, BorderLayout.SOUTH);
		finalGameCont.setVisible(true);
		frame.add(finalGameCont);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public JButton getSpecButton(String buttonName) {
		if ("NewGameButton".equals(buttonName)) {
			return newGameButton;
		} else if ("QuitGameButton".equals(buttonName)) {
			return quitGameButton;
		} else if ("HighScoresButton".equals(buttonName)) {
			return highScoresButton;
		} else if ("SwitchUserButton".equals(buttonName)) {
			return switchUserButton;
		}
		return null;
	}

	public JTextField getSpecField(String fieldName) {
		if ("FinalScoreField".equals(fieldName)) {
			return finalScoreField;
		} else if ("UserLoggedField".equals(fieldName)) {
			return userLoggedField;
		}
		return null;
	}

	public void closeFinalMessage() {
		frame.setVisible(false);
		frame = null;
	}

	public class QuitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			closeFinalMessage();
			mainPanel.quitGame();
		}

	}

	public class NewGameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			closeFinalMessage();
			mainPanel.anotherGame();
		}

	}

	public class HighScoresListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				highScores.generateHighScores();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	public class SwitchUserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.setUserName();
			userLoggedField.setText(mainPanel.getUserName());
		}

	}
}
