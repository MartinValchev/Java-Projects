package CowsAndBullsProject;

public class TransformNumber {
 int[] numArray;
 int arrLength;
	public TransformNumber(int newArrLength) {
		// TODO Auto-generated constructor stub
		arrLength = newArrLength;
		numArray = new int[arrLength];
	}
	public int[] transformNumber(int number){
		numArray = new int[arrLength];
		for (int i = numArray.length-1; i >= 0; i--) {
			numArray[i] = number%10;
			number /=10;
		}
		return numArray;
	}

}
