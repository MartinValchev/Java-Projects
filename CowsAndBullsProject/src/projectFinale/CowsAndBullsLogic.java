package projectFinale;

public class CowsAndBullsLogic {
	// checking if the user number is equal to
	// method for checking same number positon
	// method for checking same number position and value
	private StringBuilder callResult;
	private int guessNumber;
	private int userGuess;
	private int[] guessNumArray;
	private int[] userNumArray;
	private int moves;
	public static void main(String[] args){
		CowsAndBullsLogic one = new CowsAndBullsLogic(5072);
		System.out.println(one.checkStatus(0572));
	}
	public CowsAndBullsLogic(int guessNumber){
		this.guessNumber = guessNumber;
		guessNumArray = convertToIntArray(guessNumber);
	}
	public int[] convertToIntArray(int numberTConvert) {
		int workNumber = numberTConvert;
		int[] numArray = new int[4];
		int counter = numArray.length - 1;
		while (workNumber > 0) {
			int current = workNumber % 10;
			workNumber /= 10;
			numArray[counter] = current;
			counter--;
		}
		return numArray;
	}

	public String checkCows(int[] userNumArray) {
		int cowCounter = 0;
		String cowsRes ="";
		for (int i = 0; i < guessNumArray.length; i++) {
			for (int x = 0; x < userNumArray.length; x++) {
				if ((guessNumArray[i] == userNumArray[x]) && (i != x)) {
					cowCounter++;
				}
			}
		}
		cowsRes = cowCounter +"";
		return cowsRes;
	}

	public String checkBulls(int[] userNumArray) {
		int bullCounter = 0;
		String bullRes ="";
		for (int i = 0; i < guessNumArray.length; i++) {
			for (int x = 0; x < i+1; x++) {
				if ((guessNumArray[i] == userNumArray[x]) && (i == x)) {
					bullCounter++;
				}
			}
		}
		bullRes =bullCounter +"";
		return bullRes;
	}
	// check if correct number is entered
	public boolean checkUserInput(int userNum) {
		String check = Integer.toString(userNum);
		if (check.length() != 4) {
			System.out.println("Invalid number");
			return false;
		} else {
			return true;
		}
	}

	public void wonGame() {
		System.out.println("You guessed the number:" + guessNumber + "\n"
				+ "You won the game!!!");
	}
	// Check the status
	public String checkStatus(int numberToCheck){
		if (checkUserInput(numberToCheck)) {
			callResult = new StringBuilder("");
		userNumArray = convertToIntArray(numberToCheck);		
	/*		if(moves <=0){ 
				System.out.println("No more moves - You lost the game");
				callResult.append("No more moves - You lost the game");
				return callResult.toString();
			}
			else{
			*/
				callResult.append("You have ");
				callResult.append(checkCows(userNumArray));
				callResult.append(" cows ");
				callResult.append(checkBulls(userNumArray));
				callResult.append(" bulls ");
				moves--;
				return (callResult.toString());
			//}
			
		}
	    return callResult.append("Invalid Operation").toString();
		
	}
}
