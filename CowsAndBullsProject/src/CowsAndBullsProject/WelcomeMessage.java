package CowsAndBullsProject;

import javax.swing.JOptionPane;

public class WelcomeMessage {
	String userName;
	public WelcomeMessage() {
		String initialMessage = "Wellcome to Cows and Bulls Game! "
				+ "\n"
				+ "At the start of the game you will have number remaining moves depending on the chosed level"
				+ "\n"
				+ "easy, medium,hard,hardest and insane"
				+ "\n"
				+ "easy, medium and hard the guess number is four digit "
				+ "\n"
				+ "for hardest the guess number is five and for insane six digit "
				+ "try to guess what is the number(all the digits are different)"
				+ "\n"
				+ "1.If you match one digit position and value you will have one bull"
				+ "\n"
				+ "2. If you match only digit value you will receive cow "
				+ "\n"
				+ "3. The game end if you guess the number (four bulls) or "
				+ "\n" + "do not have any more moves\time remaining." + "\n"
				+ "Good Luck!";
		JOptionPane.showMessageDialog(null, initialMessage,
				"Welcome to Cows and Bulls Game",
				JOptionPane.INFORMATION_MESSAGE, null);
	}

	public void setUserName() {
		String userMessage = "Please choose username";
		String newUserName = JOptionPane.showInputDialog(userMessage,
				"choose username");
		if (newUserName.length() == 0) {
			userName= "unknown";
		} else {
			userName=  newUserName;
		}
	}
	public String getUserName(){
		return userName;
	}

}
