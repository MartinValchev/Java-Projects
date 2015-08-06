package CowsAndBullsProject;

import javax.swing.JOptionPane;

public class DifficultySelection {
	private static final String[] levelModes = { "easy", "medium", "hard",
			"hardest", "insane" };
	//private static final String[] gameModes = { "Time Mode", "Moves mode" };
	int playerMoves;

	public DifficultySelection() {

	}

	public int showDifficultyMessage() {

		String mode = (String) JOptionPane.showInputDialog(null,
				"Please choose game difficulty", "Choose difficulty",
				JOptionPane.QUESTION_MESSAGE, null, levelModes, levelModes[1]);
		// set the player moves
		if (mode.equals("easy")) {
			return 25;
		} else if (mode.equals("medium")) {
			return 20;
		} else if (mode.equals("hard")) {
			return 15;
		} else if (mode.equals("hardest")) {
			return 35;
		}
		else{
			return 30;
		}
	}
	public String getGameSeconds() {
		String gameSeconds = "";
		if (playerMoves == 25) {
			gameSeconds = "600"; // 10 min
		} else if (playerMoves == 20) {
			gameSeconds = "420"; // 7 min
		} else if (playerMoves == 15) {
			gameSeconds = "300"; // 5 min
		}
		else if (playerMoves == 35) {
			gameSeconds = "360"; // 6 min
		}
		else if (playerMoves == 30) {
			gameSeconds = "330"; // 5,5 min
		}
		return gameSeconds;
	}

	public void setPlayerMoves() {
		playerMoves = showDifficultyMessage();
	}

	public int getPlayerMoves() {

		return playerMoves;
	}


}
