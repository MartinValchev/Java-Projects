package projectFinale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CowsAndBullPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int numberDigits =4;
	private JTextField userGuess;
	private JLabel movesLabel;
	private JLabel digitsLabel;
	private JTextField movesLeft;
	private JTextField digitsCheck;
	private JButton[] numbers;
	private JButton clearBtn;
	private JButton checkBtn;
	private JTextArea guessLog;
	private Container buttonsCont;
	private StringBuilder numsLine;
	private CandBLogic declare;
	private int digitsCounter;
	private Color[] colorBackground;
	private Color chosenOne;
	// JTextField x3; JLabel x2; JButton x12;JTextArea 1x
	public CowsAndBullPanel(){
		this(25);
	}
	
	
	public CowsAndBullPanel(int playerMoves) {
		colorBackground = new Color[]{Color.RED,Color.cyan,Color.LIGHT_GRAY,Color.magenta,Color.ORANGE};
		Random rnd = new Random();
		//random.nextInt(max - min + 1) + min
		chosenOne = colorBackground[rnd.nextInt(colorBackground.length+1)];
		setBackground(chosenOne);
		setLayout(new BorderLayout());
		buttonsCont = new Container();
		buttonsCont.setLayout(new GridLayout(4, 3));
		numbers = new JButton[10];
		clearBtn = new JButton("C");
		Font font = new Font(clearBtn.getFont().getName(), Font.BOLD, 26);
		Container guessCont = new Container();
		guessCont.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
		userGuess = new JTextField();
		userGuess.setColumns(4);
		userGuess.setSize(4, 2);
		//userGuess.set
		Container digitsCont = new Container();
		digitsCont.setLayout(new FlowLayout());
		digitsCheck = new JTextField(1);
		digitsCheck.setEditable(false);
		digitsCounter=numberDigits;
		digitsCheck.setText(Integer.toString(digitsCounter));
		digitsLabel = new JLabel("Digits left");
		digitsCont.add(digitsCheck);
		digitsCont.add(digitsLabel);

		guessCont.add(userGuess);
		guessCont.add(digitsCont);
	//	userGuess.setAlignmentX(LEFT_ALIGNMENT);
		userGuess.setFont(font);
		Container movesCont = new Container();
		movesCont.setLayout(new FlowLayout());
		movesLeft = new JTextField(4);
		movesLeft.setBackground(Color.WHITE);
		movesLeft.setText(Integer.toString(playerMoves));
		movesLeft.setEditable(false);
		movesLeft.setAlignmentX(RIGHT_ALIGNMENT);
		movesLabel = new JLabel("Moves Left");
		movesCont.add(movesLabel);
		movesCont.add(movesLeft);
		guessCont.add(movesCont);
		add(guessCont, BorderLayout.NORTH);
		checkBtn = new JButton("CHECK");
		checkBtn.setBackground(Color.GREEN);
		checkBtn.setFont(new Font(clearBtn.getFont().getName(), Font.BOLD, 18));
		checkBtn.addActionListener(new CheckListener());
		clearBtn = new JButton("C");
		clearBtn.setBackground(Color.WHITE);
		numsLine = new StringBuilder();

		clearBtn.setFont(font);
		for (int i = 0; i < 10; i++) {
			numbers[i] = new JButton(Integer.toString(i));
			numbers[i].setFont(font);
			numbers[i].addActionListener(new Listener());
		}
		buttonsCont.add(numbers[7]);
		buttonsCont.add(numbers[8]);
		buttonsCont.add(numbers[9]);
		buttonsCont.add(numbers[4]);
		buttonsCont.add(numbers[5]);
		buttonsCont.add(numbers[6]);
		buttonsCont.add(numbers[1]);
		buttonsCont.add(numbers[2]);
		buttonsCont.add(numbers[3]);
		buttonsCont.add(numbers[0]);
		buttonsCont.add(clearBtn);
		buttonsCont.add(checkBtn);
		add(buttonsCont, BorderLayout.CENTER);
		guessLog = new JTextArea("Guess Log  \n", 15, 12);
		guessLog.setEditable(false);
		add(guessLog, BorderLayout.EAST);
		declare = new CandBLogic(playerMoves);
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userGuess.setText("");
				numsLine.setLength(0);
				digitsCounter=numberDigits;
				digitsCheck.setText(Integer.toString(digitsCounter));
				userGuess.requestFocus();

			}
		});

	}

	class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				if(digitsCounter>0 && (userGuess.getText().length()<=numberDigits))
				{
				numsLine.append(btn.getText());
				digitsCounter--;
				digitsCheck.setText(Integer.toString(digitsCounter));
				}
				updateText();
				userGuess.requestFocus();
			}

		}

	}

	class CheckListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent p) {
			declare.setUserInput(Integer.parseInt(userGuess.getText().trim()));
			if (!declare.isUserInputValid()) {
				JOptionPane.showMessageDialog(null, "Number should contain only 4 digits",
						"Cows and Bulls Game", JOptionPane.ERROR_MESSAGE);
				// clear the input screen;
				userGuess.setText(null);
				numsLine.setLength(0);
				digitsCounter = numberDigits;
				digitsCheck.setText(Integer.toString(digitsCounter));

			} else {
				declare.gamePlay();
				guessLog.append(declare.returnResult());
				userGuess.setText(null);
				numsLine.setLength(0);
				movesLeft.setText(declare.getMovesLeft());
				digitsCounter =4;
				digitsCheck.setText(Integer.toString(digitsCounter));
				userGuess.requestFocus();
				if(Integer.parseInt(declare.getMovesLeft())<=0){
					JOptionPane.showMessageDialog(
							null,"Sorry you loose the game!");
					userGuess.setText(null);
					declare.newGameStarts();
					userGuess.setText(null);
					guessLog.setText(null);
				}
				if (declare.isGameWon()) {
					JOptionPane.showMessageDialog(
							null,
							"Congratulations You guessed the number "
									+ userGuess.getText()
									+ " and You won the game. ");
					////////// start a new game
					setPlayerMoves(CowsAndBulls.showDifficultyMessage());
					declare.newGameStarts();
					userGuess.setText(null);
					guessLog.setText(null);
					digitsCounter = numberDigits;
					digitsCheck.setText(Integer.toString(digitsCounter));
					
				}
			}
		}

	}

	public void updateText() {
		userGuess.setText(numsLine.toString());
	}
	public void setPlayerMoves(int moves){
		this.movesLeft.setText(Integer.toString(moves));
	}

}
