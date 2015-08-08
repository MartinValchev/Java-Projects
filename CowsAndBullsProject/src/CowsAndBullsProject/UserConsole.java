package CowsAndBullsProject;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserConsole implements Console {
	GiveLayout barLayout;
	JPanel userBar;
	JTextField userField;
	JLabel userLabel;
	JLabel scoreLabel;
	JTextField scoreField;
	GiveFont font;

	public UserConsole() {
		userBar = new JPanel();
		userBar.setName("UserConsole");
		barLayout = new GiveLayout();
		font = new GiveFont();
		barLayout.setLayout(getConsole());
		userLabel = new JLabel("username: ");
		userLabel.setName("userLabel");
		font.setFont(userLabel);
		userField = new JTextField(12);
		userField.setText("unknown");
		userField.setName("userField");
		font.setFont(userField);
		userField.setEditable(false);
		scoreLabel = new JLabel("Score: ");
		scoreLabel.setName("scoreLabel");
		scoreField = new JTextField(8);
		scoreField.setText("0");
		scoreField.setEditable(false);  
		userBar.add(userLabel);
		userBar.add(userField);
		userBar.add(scoreLabel);
		userBar.add(scoreField);
		userBar.setVisible(true);
	}

	@Override
	public String getName() {
		return "UserConsole";
	}

	@Override
	public JPanel getConsole() {
		return userBar;
	}
	public JTextField getScoreField(){
		return scoreField;
	}
	public JTextField getUserField(){
		return userField;
	}
}
