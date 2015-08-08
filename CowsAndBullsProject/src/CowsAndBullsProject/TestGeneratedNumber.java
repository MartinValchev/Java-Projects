package CowsAndBullsProject;

import java.nio.channels.ShutdownChannelGroupException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class TestGeneratedNumber {
	public int guessNumber;
	public final int NUMBER_SIZE = 4;
	public int[] resultNumber;
	public StringBuilder numBuild;
	public Queue<Integer> delimiterQueue;
	public ArrayList<Integer> numberList;
	public int delimiter;
	public int counter;
	private boolean isUsed;
	private int result;

	public TestGeneratedNumber() {
		result = 0;
		resultNumber = new int[NUMBER_SIZE];

		numberList = new ArrayList();
		delimiterQueue = new LinkedList();
		isUsed = false;

	}

	public static void main(String[] args) {
		TestGeneratedNumber test = new TestGeneratedNumber();
		int finalRes = test.generateAnotherRand();
		System.out.println(finalRes);
		int another = test.generateAnotherRand();
		System.out.println(another);
		int anotherOne = test.generateAnotherRand();
		System.out.println(anotherOne);
		int anotherTwo = test.generateAnotherRand();
		System.out.println(anotherTwo);
	}

	/*
	 * public static int generateRandom() { Random rand = new Random();
	 * 
	 * guessNumber = rand.nextInt(900000) + 100000; char[] arrayGuess =
	 * Integer.toString(guessNumber).toCharArray();
	 * 
	 * while ((arrayGuess[0] == arrayGuess[1]) || (arrayGuess[0] ==
	 * arrayGuess[2]) || (arrayGuess[0] == arrayGuess[3]) || (arrayGuess[1] ==
	 * arrayGuess[2]) || (arrayGuess[1] == arrayGuess[3]) || (arrayGuess[2] ==
	 * arrayGuess[3])) { guessNumber = rand.nextInt(999999) + 100000; arrayGuess
	 * = Integer.toString(guessNumber).toCharArray(); } return guessNumber; }
	 */
	public int generateAnotherRand() {
		if (!isUsed) {
			// initial shuffle
			for (int i = 0; i <= 9; i++) {
				numberList.add(i);
			}
			delimiter =1;
			shuffleArray(delimiter);
			isUsed = true;
		}
		delimiter = 1 + (int) (Math.random() * 8);
		shuffleArray(delimiter);

		buildRandomNum();
		result = getResult();
		return result;
	}

	private int getResult() {
		numBuild = new StringBuilder();
		// convert digits to String
		for (int i = 0; i < resultNumber.length; i++) {
			numBuild.append(resultNumber[i]);
		}

		result = Integer.parseInt(numBuild.toString());
		return result;
	}

	private int buildRandomNum() {
		int index =0;
		int resIndex =0;
		for (int i = delimiter; i < NUMBER_SIZE + delimiter; i++) {
			index = i;
			if (index < numberList.size()) {
				resultNumber[resIndex] = numberList.get(index);
		
			} else {
				// start from the begining of numberList
				index -= numberList.size();
				resultNumber[resIndex] = numberList.get(index);
			}
			resIndex++;
		}
		return resIndex;
	}

	private void fillDelimiterQueue() {
		for (int i = delimiter; i < numberList.size(); i++) {
			int current = numberList.get(i);
			delimiterQueue.add(current);

		}
	}

	public void shuffleArray(int delimiter) {

		fillDelimiterQueue();
		numberList.subList(delimiter, numberList.size()).clear();
		counter = 0;
		while (!delimiterQueue.isEmpty()) {
			counter++;
			if (counter % 2 == 0) {
				numberList.add(delimiterQueue.poll());
			} else {
				numberList.add(0, delimiterQueue.poll());
			}
		}
	}
}
