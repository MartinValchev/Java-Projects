package CowsAndBullsProject;

public class CheckBulls {
	private int genNumber;
	private int bullsCount;
	private int numberToGuess;
	private TransformNumber transfNum;
	private static final int NUMBER_LENGTH =4;
	public CheckBulls(int newNumber) {
		resetBullCount();
		genNumber = newNumber;
		transfNum = new TransformNumber(4);
	}
	public int checkBulls(int userGuess){
		resetBullCount();
		int[] generatedArr = transfNum.transformNumber(genNumber);
		int[] userArr = transfNum.transformNumber(userGuess);
		for (int i = 0; i < NUMBER_LENGTH; i++) {
			if (generatedArr[i] == userArr[i]){
				bullsCount++;	
			}
		}
		return bullsCount;
	}
	public void resetBullCount(){
		bullsCount =0;
	}
}
