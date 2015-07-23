package CowsAndBullsProject;

public class MovesAndDigitsCheck {
	private static final int DIGIT_COUNT =4;
	private int movesLeft;
	public MovesAndDigitsCheck() {

	}
	public boolean checkDigitMoves(int currentMoves,int currentDigitsLeft){
		if(movesLeft>0 && currentDigitsLeft>0){
			return true;
		}
		else{
			return false;
		}
			
	}
}
