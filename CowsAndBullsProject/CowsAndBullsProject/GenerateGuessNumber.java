package CowsAndBullsProject;

import java.util.Random;

public class GenerateGuessNumber {
	static int guessNumber;
    private static GenerateGuessNumber instance = null;
    protected GenerateGuessNumber() {
    	
     }
     public static GenerateGuessNumber getInstance() {
        if(instance == null) {
           instance = new GenerateGuessNumber();
           guessNumber =  generateRandom();
        }
        return instance;
     }
	 
	private static int generateRandom() {
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
	public void resetNumber(){
		instance = null;
		getInstance();
	}
	public int getGuessNumber(){
		return guessNumber;
	}
}
