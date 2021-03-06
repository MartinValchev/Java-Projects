package CowsAndBullsProject;

import java.awt.Font;

import javax.swing.JComponent;

public class GiveFont {
	private String[] nums = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	private String[] specials = { "C", "Check", "Digits Left", "moves Left",
			"secondaryTextField" };
	private Font font = new Font("Arial", Font.PLAIN, 14);

	public void setFont(JComponent comp) {
		String compName = comp.getName();
		if (findNum(compName) ||findSpecial(compName)) {
			font = new Font("Arial", Font.BOLD, 18);
			
		}
		if (compName.equals("GuessTextField")) {
			font = new Font("Arial", Font.BOLD, 26);
		}
		else if (compName.equals("MovesTextField")) {
			font = new Font("Arial", Font.PLAIN, 14);
		}
		else if (compName.equals("digitsLeftLabel")
				|| compName.equals("movesLeftLabel")) {
			font = new Font("verdana", Font.BOLD, 14);
		}
		else if (compName.equals("textArea")) {
			font = new Font("verdana", Font.PLAIN, 16);
		}
		else if (compName.equals("welcome")) {
			font = new Font("verdana", Font.BOLD, 12);
		}
		else if (compName.equals("TimeField")) {
			font = new Font("Arial", Font.PLAIN, 14);
		}
		else if (compName.equals("PauseButton")) {
			font = new Font("verdana", Font.BOLD, 14);
		}
		else if (compName.equals("userField")) {
			font = new Font("verdana", Font.ITALIC, 14);
		}
		else if (compName.equals("userLabel")) {
			font = new Font("verdana", Font.BOLD, 14);
		}
		comp.setFont(font);
	}

	public boolean findNum(String word) {
		boolean isNumFound = false;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i].equals(word)) {
				isNumFound = true;
				return isNumFound;
			}
		}
		return isNumFound;

	}

	public boolean findSpecial(String word) {
		boolean isSpecFound = false;
		for (int i = 0; i < specials.length; i++) {
			if (specials[i].equals(word)) {
				isSpecFound = true;
				return isSpecFound;
			}
		}
		return isSpecFound;

	}
}
