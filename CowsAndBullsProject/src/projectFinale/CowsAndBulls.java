package projectFinale;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CowsAndBulls {
private static final String[] levelModes = {"easy","medium","hard"}; 
//private static int playerMoves;
	public static void main(String[] args) {
		
		gameBegins();
	
	}
	public static int showDifficultyMessage(){
		
		String  mode= (String) JOptionPane.showInputDialog(null, "Please choose game difficulty", 
		"Choose difficulty", JOptionPane.QUESTION_MESSAGE, 
		null, levelModes, levelModes[1]);
		// set the player moves
		if(mode.equals("easy")){
			return 45;	
		}
		else if(mode.equals("medium")){
			return 35;
		}
		else{
			return 25;
		}
	}
	public static void gameBegins(){
		String initialMessage = "Wellcome to Cows and Bulls Game! " +"\n" + 
				"At the start of the game you will have number remaining moves depending on the chosed level"
				+ "\n" + "easy, medium or hard" + "\n"+"try to guess what is the four digit number(all the digits are different)" + "\n" +
				 "1.If you match one digit position and value you will have one bull" + "\n" +
				"2. If you match only digit postion you will receive cow " + "\n" + 
				 "3. The game end if you guess the four digit number (four bulls) or "+ "/n"  +
				"do not have any more moves remaining" + "/n" + 
				 "Good Luck";
		JOptionPane.showMessageDialog(null,initialMessage,"Welcome to Cows and Bulls Game", JOptionPane.INFORMATION_MESSAGE, null);

				JFrame frame = new JFrame("Cows and Bulls game");
				// drawing the panel
				frame.setSize(500, 400);
				frame.setMinimumSize(new Dimension(500,400));
				frame.setLocation(50, 50);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				CowsAndBullPanel panel = new CowsAndBullPanel(showDifficultyMessage());
				frame.add(panel);
				frame.setVisible(true);
	}
}
