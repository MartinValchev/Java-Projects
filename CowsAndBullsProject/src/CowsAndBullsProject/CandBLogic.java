package CowsAndBullsProject;

import java.util.Random;

public class CandBLogic {
	private int guessNumber;
	private int movesLeft;
	private String userInput;
	private boolean isGameEnded;
	private CheckBulls bulls;
	private CheckCows cows;
	private ValidateCheckInput validateInput;
	private int cowCounter;
	private int bullCounter;
	private static StringBuilder result;



	public CandBLogic(int movesLeft) {
		GenerateGuessNumber.getInstance();
		guessNumber = GenerateGuessNumber.getInstance().generateRandom();
		bulls = new CheckBulls(guessNumber);
		cows = new CheckCows(guessNumber);
		isGameEnded = false;
		this.movesLeft = movesLeft;
		result = new StringBuilder("");
	}


	public String returnResult() {
		//remove
		//System.out.println(guessNumber);
		return result.toString();
	}

	public int getMovesLeft() {
		return movesLeft;
	}

	public boolean isGameWon() {
		if ((bullCounter == 4) && (movesLeft > 0)) {
			isGameEnded = true;
			return true;
		} else {
			return false;
		}
	}
	public void newGameStarts(int newMovesLeft){
		GenerateGuessNumber.getInstance().resetNumber();
		guessNumber = GenerateGuessNumber.getInstance().getGuessNumber();
		bulls = new CheckBulls(guessNumber);
		cows = new CheckCows(guessNumber);
		isGameEnded = false;
		this.movesLeft = newMovesLeft;
	}
	public void gamePlay(String userInput) {
		if (isGameEnded == false) {
			if (movesLeft <= 0) {
				// Game over
				isGameEnded = true;

			} else {
					// reset all values
					result.setLength(0);
					result.append(userInput + " -");
					cowCounter = cows.checkCows(Integer.parseInt(userInput));
					result.append(cowCounter + " COW(s), ");
					// bulls check
					bullCounter =bulls.checkBulls(Integer.parseInt(userInput));
					result.append(bullCounter + " BULL(s)" + "\n");
				}
				
				movesLeft--;
			}

		}
		
	
};
