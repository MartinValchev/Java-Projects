package CowsAndBullsProject;

import javax.swing.JOptionPane;

public class ValidateCheckInput {

	String userInput;
	int guessNumberLength;

	public ValidateCheckInput(String newUserInput, int guessDigits) {
		userInput = newUserInput;
		guessNumberLength = guessDigits;
	}

	public boolean validateInput() {
		if (userInput.length() < guessNumberLength) {
			JOptionPane
					.showMessageDialog(null, "The input you entered is not "
							+ guessNumberLength
							+ " digit number. Please try again",
							"Error in number format",
							JOptionPane.WARNING_MESSAGE, null);
			return false;
		} else {
			try {
				int number = Integer.parseInt(userInput);
				return true;

			} catch (NumberFormatException e) {
				JOptionPane
						.showMessageDialog(
								null,
								"The input you entered is not a number please try again",
								"Error in number format",
								JOptionPane.WARNING_MESSAGE, null);
				return false;
			}
		}
	}
}
