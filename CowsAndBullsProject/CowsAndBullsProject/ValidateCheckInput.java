package CowsAndBullsProject;

import javax.swing.JOptionPane;

public class ValidateCheckInput {

	String userInput;

	public ValidateCheckInput(String newUserInput) {
		userInput = newUserInput;
	}

	public boolean validateInput() {
		if (userInput.length() < 4) {
			JOptionPane
					.showMessageDialog(
							null,
							"The input you entered is not 4 digit number. Please try again",
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
