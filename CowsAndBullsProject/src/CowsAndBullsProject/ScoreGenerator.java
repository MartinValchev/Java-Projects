package CowsAndBullsProject;

public class ScoreGenerator {
	private int movesLeft;
	private int playerMoves;
	private int timeLeft;
	private int difficultyBonus;
	private double cowsQuatient;
	private double bullsQuatient;
	private int currentScore;
	private int finalScore;

	public ScoreGenerator(int playerMoves) {
		currentScore = 0;
		finalScore = 0;
		cowsQuatient = 15;
		bullsQuatient = 20;
		difficultyBonus = 0;
		this.playerMoves = playerMoves;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public int generateFinalScore(boolean isGameWon) {
		// if the game is won will have bonus
		if (isGameWon) {
			if (playerMoves == 25) { // easy level
				difficultyBonus = 150;
			} else if (playerMoves == 20) {// medium level
				difficultyBonus = 200;
			} else if (playerMoves == 15) {// hard level
				difficultyBonus = 250;
			} else if (playerMoves == 35) { // hardest level
				difficultyBonus = 300;
			} else if ((playerMoves == 30)) { // insane level
				difficultyBonus = 650;
			}
		}
		else{
			difficultyBonus =0;
		}
		finalScore = currentScore + difficultyBonus;
		currentScore = finalScore;
		return currentScore;
	}

	public int updateScore(CandBLogic logic) {
		int cowsCounter = logic.getCowCounter();
		int bullsCounter = logic.getBullCounter();
		if (cowsCounter > 0) {
			currentScore += (int) (cowsCounter * cowsQuatient);
		}
		if (bullsCounter > 0) {
			currentScore += (int) (bullsCounter * bullsQuatient);
		}
		return currentScore;
	}

	public void resetScore() {
		currentScore = 0;
	}

}
