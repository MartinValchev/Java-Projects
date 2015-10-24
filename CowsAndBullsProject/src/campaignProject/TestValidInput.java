package campaignProject;

public class TestValidInput {
	private boolean isValid;
	private StringBuilder builder;
	private String inputEntered;
	private static final char separator = '-';

	public TestValidInput() {
		isValid = true;
		builder = new StringBuilder();
	}

	public boolean checkSettingsInput(String input) {
		isValid = true;
		inputEntered = input;
		char currentChar = ' ';
		int currentCounter = 0;
		currentChar = inputEntered.charAt(currentCounter);
		while (currentChar != separator) {
			builder.append(currentChar);
			currentCounter++;
			currentChar = inputEntered.charAt(currentCounter);
		}
		try {
			Integer.parseInt(builder.toString().trim());
			builder.setLength(0);
		} catch (Exception e) {
			isValid = false;
			builder.setLength(0);
		}
		return isValid;
	}
}
