package CowsAndBullsProject;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projectFinale.CowsAndBullPanel;

public class TestCowsAndBulls {

	public TestCowsAndBulls() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		WelcomeMessage message = new WelcomeMessage();
		message.setUserName();
		DifficultySelection select = new DifficultySelection();	
		select.setPlayerMoves();	
		int playerMoves = select.getPlayerMoves();
		String gameSeconds = select.getGameSeconds();
	CandBMainPanel panel = new CandBMainPanel(playerMoves, gameSeconds,message);
	}

}
