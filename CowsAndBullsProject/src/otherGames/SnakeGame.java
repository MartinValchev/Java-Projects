package otherGames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeGame extends JPanel{
	public static void main(String[] args){
		JFrame frame = new JFrame("Snake game");
		// drawing the panel
		frame.setSize(500, 500);
		frame.setMinimumSize(new Dimension(500,400));
		frame.setLocation(50, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnakePanel panel = new SnakePanel();
		frame.add(panel);
		frame.setVisible(true);
	}
	
}
