package CowsAndBullsProject;

import javax.swing.JOptionPane;

public class DifficultySelection {
	private static final String[] levelModes = { "easy", "medium", "hard" };
	int playerMoves;

	public DifficultySelection() {
		playerMoves = showDifficultyMessage();
	}
	
	private static int showDifficultyMessage() {

		String mode = (String) JOptionPane.showInputDialog(null,
				"Please choose game difficulty", "Choose difficulty",
				JOptionPane.QUESTION_MESSAGE, null, levelModes, levelModes[1]);
		// set the player moves
		if (mode.equals("easy")) {
			return 25;
		} else if (mode.equals("medium")) {
			return 20;
		} else {
			return 15;
		}
	}
	public String getGameSeconds(){
		String gameSeconds ="";
		if (playerMoves ==25){
			gameSeconds ="600"; // 10 min
		}
		else if(playerMoves ==20){
			gameSeconds ="420"; // 7 min
		}
		else if(playerMoves ==15){
			gameSeconds ="300"; // 5 min
		}
		return gameSeconds;
	}

	public int getPlayerMoves() {
		return playerMoves;
	}

}
