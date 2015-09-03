package campaignProject;

public class delete {

	public delete() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		boolean isValid = false;
		String stringToCheck ="2a80 / PositionOne";
		String another = "3210 / PositionOne";
		TestValidInput input = new TestValidInput();
		isValid = input.checkSettingsInput(stringToCheck);
		System.out.println(isValid);
		isValid =input.checkSettingsInput(another);
		System.out.println(isValid);
		String anotherOne = "32s0 / PositionOne";
		isValid =input.checkSettingsInput(anotherOne);
		System.out.println(isValid);
		isValid =input.checkSettingsInput(another);
		System.out.println(isValid);
	}

}
