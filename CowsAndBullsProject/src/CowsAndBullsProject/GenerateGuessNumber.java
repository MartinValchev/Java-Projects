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
	 
	public static int generateRandom() {
		Random rand = new Random();

		int guessNumber = rand.nextInt(9000) + 1000;
		char[] arrayGuess = Integer.toString(guessNumber).toCharArray();
	
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
	public static void resetNumber(){
		generateRandom();
	}
	public static int getGuessNumber(){
		return guessNumber;
	}
}
