package otherGames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SnakePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel scoreLabel;
	private JTextField score;
	private Container gameContainer; 
	
	public SnakePanel(){
		setBackground(Color.lightGray);
		setLayout(new BorderLayout());
		Container scorePanel = new Container();
		scorePanel.setLayout(new FlowLayout());
		add(scorePanel,BorderLayout.NORTH);
		gameContainer = new Container();
		SnakeLogic snake = new SnakeLogic();
		snake.startGame();
		gameContainer.add(snake);
		scoreLabel = new JLabel("Score: "); 
		score = new JTextField(5);
		score.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
		score.setEditable(false);
		scorePanel.add(scoreLabel);
		scorePanel.add(score);
		score.setBackground(Color.ORANGE);
		scorePanel.setBackground(Color.CYAN);
		gameContainer.setBackground(Color.ORANGE);
		add(gameContainer,BorderLayout.CENTER);
	
		
	}

}
