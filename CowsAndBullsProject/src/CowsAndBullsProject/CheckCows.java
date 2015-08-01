package CowsAndBullsProject;

public class CheckCows {
	private int genNumber;
	private int cowsCount;
	private TransformNumber transfNum;
	private int numberLength;

	public CheckCows(int generatedNum) {
		genNumber = generatedNum;
		numberLength = (Integer.toString(genNumber)).length();
		resetCowCount();
		transfNum = new TransformNumber(numberLength);
	}
	public int checkCows(int userGuess){
		resetCowCount();
		int[] generatedArr = transfNum.transformNumber(genNumber);
		int[] userArr = transfNum.transformNumber(userGuess);
		for (int i = 0; i < generatedArr.length; i++) {
			for (int j = 0; j < userArr.length; j++) {
				if(generatedArr[i] ==userArr[j] && (i !=j)){
					cowsCount++;
				}
			}
		}
		return cowsCount;
	}
	public void resetCowCount() {
		cowsCount = 0;
	}

}
