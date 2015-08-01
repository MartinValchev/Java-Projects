package CowsAndBullsProject;

import java.util.Random;

public class GenerateGuessNumber {
	static int guessNumber;
    private static final GenerateGuessNumber instance = new GenerateGuessNumber();
    private GenerateGuessNumber() {
    	if(instance != null) {
        	throw new IllegalStateException("Already instantiated");
        }
     }
     public static GenerateGuessNumber getInstance() {
        
        return instance;
     }
	 
	public int generateRandom() {
		Random rand = new Random();

		guessNumber = rand.nextInt(900000) + 100000;
		char[] arrayGuess = Integer.toString(guessNumber).toCharArray();
	
		while ((arrayGuess[0] == arrayGuess[1])
				|| (arrayGuess[0] == arrayGuess[2])
				|| (arrayGuess[0] == arrayGuess[3])
				|| (arrayGuess[1] == arrayGuess[2])
				|| (arrayGuess[1] == arrayGuess[3])
				|| (arrayGuess[2] == arrayGuess[3])) {
			guessNumber = rand.nextInt(999999) + 100000;
			arrayGuess = Integer.toString(guessNumber).toCharArray();
		}
		return guessNumber;
	
	}
	public void resetNumber(){
		generateRandom();
	}
	public int getGuessNumber(int playerMoves){
		if (playerMoves ==25){
			guessNumber /=100; //easy level
		}
		else if (playerMoves ==20){
			guessNumber /=100;  // medium level
		}
		else if (playerMoves ==15){
			guessNumber /=100;  // hard level
		}
		else if (playerMoves ==35){
			guessNumber /=10; //mode hardest;
		}
		else if (playerMoves ==30){
			return guessNumber; // insane level
		}
		return guessNumber;
	}
}
