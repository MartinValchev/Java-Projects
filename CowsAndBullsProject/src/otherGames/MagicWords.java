package otherGames;

import java.util.Scanner;

public class MagicWords {
	private static String[] words;
	private static int wordsNumber;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		wordsNumber = sc.nextInt();
		words = new String[wordsNumber];
		
		for (int i = 0; i < words.length; i++) {
			System.out.println("Enter a word");
			words[i] = sc.nextLine();
		}
		sc.close();
		reorder();
		print();

	}

	public static void reorder() {
		for (int i = 0; i < words.length; i++) {
			int position = (words[i].length()) % (wordsNumber + 1);
			String temp = words[i];
			words[i] = words[position];
			words[position] = temp;
		}
	}

	public static void print() {
		int biggestLength =0;;
		// check the longest word
			for (int i = 0; i < words.length; i++) {
				if (words[i].length()>biggestLength){
					biggestLength =words[i].length();
				}
			}
			// create a matrix with biigestLength and words.length
		char[][] wordMatrix = new char[words.length][biggestLength];
		moveTocharMatrix(wordMatrix,biggestLength);
		for (int i = 0; i < biggestLength; i++) {
			for (int j = 0; j < words.length; j++) {
				System.out.print(wordMatrix[j][i]);
			}
		}
	}

	private static void moveTocharMatrix(char[][] wordMatrix,int biggestLength) {
		for (int rows = 0; rows < words.length; rows++) {
			for (int cols = 0; cols < biggestLength; cols++) {
				if(cols<words[rows].length())
				wordMatrix[rows][cols] = words[rows].charAt(cols);
			}
		}
	}

}
