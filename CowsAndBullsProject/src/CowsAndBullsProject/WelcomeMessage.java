package CowsAndBullsProject;

import javax.swing.JOptionPane;

public class WelcomeMessage {

	public WelcomeMessage() {
		String initialMessage = "Wellcome to Cows and Bulls Game! "
				+ "\n"
				+ "At the start of the game you will have number remaining moves depending on the chosed level"
				+ "\n"
				+ "easy, medium or hard"
				+ "\n"
				+ "try to guess what is the four digit number(all the digits are different)"
				+ "\n"
				+ "1.If you match one digit position and value you will have one bull"
				+ "\n"
				+ "2. If you match only digit value you will receive cow "
				+ "\n"
				+ "3. The game end if you guess the four digit number (four bulls) or "
				+ "\n" + "do not have any more moves remaining." + "\n"
				+ "Good Luck!";
		JOptionPane.showMessageDialog(null, initialMessage,
				"Welcome to Cows and Bulls Game",
				JOptionPane.INFORMATION_MESSAGE, null);
	}

}
