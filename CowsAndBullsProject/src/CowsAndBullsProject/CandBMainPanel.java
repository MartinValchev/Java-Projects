package CowsAndBullsProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import projectFinale.CowsAndBulls;

public class CandBMainPanel {
	ButtonsConsole buttonsConsole;
	InfoConsole infoConsole;
	static JTextField guessTextField;
	static JTextField movesTextField;
	static JTextField digitTextField;
	static JTextArea logArea;
	static TimeCounter timeCounter;
	static int secondsRemaining;
	private static final int NUM_QUANTITY = 10;
	private static final int GUESS_DIGITS = 4;
	static int digitsCounter;
	static StringBuilder numsLine;
	static StringBuilder logLine;
	static int playerMoves;
	private String gameSeconds;
	private int numberToGuess;
	private ValidateCheckInput checkInput;
	private CandBLogic logic;
	private JFrame frame;

	public CandBMainPanel(int newPlayerMoves, String newGameSeconds) {
		playerMoves = newPlayerMoves;
		gameSeconds = newGameSeconds;
		digitsCounter = 4;
		numberToGuess = GenerateGuessNumber.getInstance().getGuessNumber();
		frame = new JFrame("Cows and Bulls Game");
		frame.setSize(600, 400);
		frame.setMinimumSize(new Dimension(600, 400));
		frame.setLocation(50, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setLayout(new FlowLayout());
		MainConsole mainConsole = new MainConsole("MainConsole");
		InfoConsole infoConsole = new InfoConsole("InfoConsole");
		timeCounter = new TimeCounter(gameSeconds,this);
		infoConsole.addTimeCounter(timeCounter.getTimeCont());
		secondsRemaining = timeCounter.getSecondsRemaining();
		logic = new CandBLogic(playerMoves);
		LogConsole logConsole = new LogConsole("LogConsole");
		buttonsConsole = new ButtonsConsole("ButtonsConsole");
		logArea = logConsole.getLogArea();
		guessTextField = infoConsole.getSpecificField("GuessTextField");
		movesTextField = infoConsole.getSpecificField("MovesTextField");
		digitTextField = infoConsole.getSpecificField("DigitsTextField");
		numsLine = new StringBuilder();
		digitTextField.setText(Integer.toString(digitsCounter));
		movesTextField.setText(Integer.toString(playerMoves));

		addActionButton();/*
						 * mainConsole.addPanels(infoConsole);
						 * mainConsole.addPanels(buttonsConsole);
						 * mainConsole.addPanels(logConsole);
						 */
		// just for test

		(mainConsole.getConsole()).add(infoConsole.getConsole(),
				BorderLayout.NORTH);
		(mainConsole.getConsole()).add(buttonsConsole.getConsole(),
				BorderLayout.CENTER);
		(mainConsole.getConsole()).add(logConsole.getConsole(),
				BorderLayout.EAST);
		frame.add(mainConsole.getConsole());
		frame.setVisible(true);

	}

	public void anotherGame() {
		// ///////////// set seconds remaining to the Time field;
		String title = "New Game request";
		String message = "Do you want another game?";
		int choice = JOptionPane.showConfirmDialog(null, message, title,
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			// JOptionPane.showConfirmDialog(arg0, arg1)
			WelcomeMessage newMessage2 = new WelcomeMessage();
			DifficultySelection newSelect = new DifficultySelection();
			int newPlayerMoves = newSelect.getPlayerMoves();
			movesTextField.setText(Integer.toString(newPlayerMoves));
			timeCounter.resetTimer(newSelect.getGameSeconds());
			logic.newGameStarts(newPlayerMoves);
			guessTextField.setText(null);
			logArea.setText(null);
			digitsCounter = GUESS_DIGITS;
			digitTextField.setText(Integer.toString(digitsCounter));
		} else {
			frame.setVisible(false);
			frame = null;
		}
	}

	class CheckButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			checkInput = new ValidateCheckInput(numsLine.toString());
			if (!timeCounter.getStatus().equals("Paused")) {
				if (checkInput.validateInput()) {
					logLine = new StringBuilder("");
					logic.gamePlay(numsLine.toString());
					String result = logic.returnResult();
					logArea.append(result);
					// update player moves
					playerMoves = logic.getMovesLeft();
					movesTextField.setText(Integer.toString(playerMoves));
					numsLine = new StringBuilder("");
					guessTextField.setText(numsLine.toString());
					digitsCounter = 4;
					digitTextField.setText(Integer.toString(digitsCounter));
					guessTextField.requestFocus();
					secondsRemaining = timeCounter.getSecondsRemaining();
					// check if game is lost
					if (playerMoves <= 0 || secondsRemaining == 0) {
						gameLost();
					}
					secondsRemaining = timeCounter.getSecondsRemaining();
					if (logic.isGameWon() && secondsRemaining > 0) {
						JOptionPane.showMessageDialog(null,
								"Congratulations You guessed the number "
										+ guessTextField.getText()
										+ " and You won the game. ");
						// check if user want a new game
						// //////// start a new game
						// setPlayerMoves(CowsAndBulls.showDifficultyMessage());
						timeCounter.pauseTimer();
						anotherGame();

					}
				} else {
					// clear screen; reset digits counter;
					numsLine = new StringBuilder("");
					guessTextField.setText(numsLine.toString());
					digitsCounter = 4;
					digitTextField.setText(Integer.toString(digitsCounter));
				}
			}

		}

		

	}
	public void gameLost() {
		JOptionPane.showMessageDialog(null,
				"Sorry you loose the game!");
		anotherGame();
	}
	class NumListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ((e.getSource() instanceof JButton)
					&& (!timeCounter.getStatus().equals("Paused"))) {
				JButton btn = (JButton) e.getSource();
				if (digitsCounter > 0) {
					String btnText = btn.getText();
					numsLine.append(btn.getText());
					digitsCounter--;
					digitTextField.setText(Integer.toString(digitsCounter));
					updateText();
					guessTextField.requestFocus();
				}
			}

		}

	}

	public void updateText() {
		guessTextField.setText(numsLine.toString());
	}

	public void addActionButton() {
		JButton current = null;
		for (int i = NUM_QUANTITY - 1; i >= 0; i--) {
			String input = (Integer.toString(i));
			current = buttonsConsole.getSpecButton(input);
			current.addActionListener(new NumListener());
		}
		current = buttonsConsole.getSpecButton("C");
		current.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				numsLine = new StringBuilder("");
				guessTextField.setText(numsLine.toString());
				digitsCounter = 4;
				digitTextField.setText(Integer.toString(digitsCounter));
				guessTextField.requestFocus();

			}

		});
		current = buttonsConsole.getSpecButton("Check");
		current.addActionListener(new CheckButtonListener());
	}
}
