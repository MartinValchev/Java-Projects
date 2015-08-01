package CowsAndBullsProject;

public class CheckBulls {
	private int genNumber;
	private int bullsCount;
	private TransformNumber transfNum;
	private int numberLength;
	public CheckBulls(int newNumber) {
		resetBullCount();
		genNumber = newNumber;
		numberLength =(Integer.toString(genNumber)).length();
		transfNum = new TransformNumber(numberLength);
	}
	public int checkBulls(int userGuess){
		resetBullCount();
		int[] generatedArr = transfNum.transformNumber(genNumber);
		int[] userArr = transfNum.transformNumber(userGuess);
		for (int i = 0; i < numberLength; i++) {
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
