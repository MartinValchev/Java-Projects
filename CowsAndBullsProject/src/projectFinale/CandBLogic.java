package projectFinale;

import java.util.Random;

public class CandBLogic {
	private int guessNumber;
	private int movesLeft;
	private int userNumber;
	private String userInput;
	private String computerNum;
	private boolean isGameEnded;
	private int cowCounter;
	private int bullCounter;
	private static StringBuilder result;

	public CandBLogic(int movesLeft) {
		guessNumber = generateRandom();
		isGameEnded = false;
		this.movesLeft = movesLeft;
		result = new StringBuilder("");
	}

	// should remove main method when running the program
	public int generateRandom() {
		Random rand = new Random();

		int guessNumber = rand.nextInt(9000) + 1000;
		char[] arrayGuess = Integer.toString(guessNumber).toCharArray();
		// 2356
		while ((arrayGuess[0] == arrayGuess[1])
				|| (arrayGuess[0] == arrayGuess[2])
				|| (arrayGuess[0] == arrayGuess[3])
				|| (arrayGuess[1] == arrayGuess[2])
				|| (arrayGuess[1] == arrayGuess[3])
				|| (arrayGuess[2] == arrayGuess[3])) {
			guessNumber = rand.nextInt(9999) + 1000;
			arrayGuess = Integer.toString(guessNumber).toCharArray();
		}
		return guessNumber;
	}

	public void setUserInput(int userNumber) {
		if (userNumber > 1000 || userNumber <= 9999) {
			this.userNumber = userNumber;
			return;
		}
		userNumber = 0;
	}

	public boolean isUserInputValid() {
		if (userNumber >= 1000 && userNumber <= 9999) {
			return true;
		}
		return false;
	}

	public String returnResult() {
		return result.toString();
	}

	public String getMovesLeft() {
		return Integer.toString(movesLeft);
	}

	public boolean isGameWon() {
		if ((bullCounter == 4) && (movesLeft > 0)) {
			isGameEnded = true;
			return true;
		} else {
			return false;
		}
	}
	public void newGameStarts(){
		isGameEnded = false;
		guessNumber= generateRandom();
	}
	public void gamePlay() {
		if (isGameEnded == false) {
			if (movesLeft <= 0) {
				// Game over
				isGameEnded = true;

			} else {
				if (!isUserInputValid()) {

				} else {
					// reset all values
					cowCounter = 0;
					bullCounter = 0;
					result.setLength(0);
					userInput = Integer.toString(userNumber);
					result.append(userInput + " -");
					computerNum = Integer.toString(guessNumber);
					//deleter
					System.out.println(computerNum);
					// cows check
					for (int i = 0; i < computerNum.length(); i++) {
						for (int j = 0; j < userInput.length(); j++) {
							if (computerNum.charAt(i) == userInput.charAt(j)
									&& (i != j)) {
								cowCounter++;
							}
						}
					}
					result.append(cowCounter + " COW(s), ");
					// bulls check
					for (int i = 0; i < computerNum.length(); i++) {
						if (userInput.charAt(i) == computerNum.charAt(i)) {
							bullCounter++;
						}

					}
				}
				result.append(bullCounter + " BULL(s)" + "\n");
				movesLeft--;
				userNumber = 0;
			}

		}
		
	}
};
