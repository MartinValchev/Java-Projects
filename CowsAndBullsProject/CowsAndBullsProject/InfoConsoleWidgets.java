package CowsAndBullsProject;

import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoConsoleWidgets {

	private JTextField guessDisplay;
	private JTextField digitsLeft;
	private JLabel digitsLeftLabel;
	private JTextField movesLeft;
	private JLabel movesLeftLabel;
	private GiveWidgetsLayout layout;
	private Container guessCont;
	private Container digitsCont;
	private Container movesCont;
	GiveFont font;
	StringBuilder numsLine;
	private int digitsCounter;
	private static InfoConsoleWidgets instance;
	  
    public static InfoConsoleWidgets getInstance() {
    if(instance == null) {
       instance = new InfoConsoleWidgets();

    }
    return instance;
 }
	protected InfoConsoleWidgets() {
		font = new GiveFont();
		setContainers();
		setWidgets();
		guessCont.add(guessDisplay);
		digitsCont.add(digitsLeft);
		digitsCont.add(digitsLeftLabel);
		movesCont.add(movesLeft);
		layout = new GiveWidgetsLayout();
		numsLine = new StringBuilder();
		setDigitsCounter(4);
	}

	protected void setContainers() {
		guessCont = new Container();
		digitsCont = new Container();
		movesCont = new Container();
	}

	protected void setWidgets() {
		guessDisplay = new JTextField(4);
		guessDisplay.setName("GuessTextField");
		guessDisplay.setSize(5, 3);
		guessDisplay.setColumns(5);

		font.setFont(guessDisplay);
		guessDisplay.setEditable(false);
		digitsLeft = new JTextField(2);
		digitsLeft.setName("DigitsTextField");
		digitsLeft.setEditable(false);
		digitsLeft.setAlignmentX((float) 1.0);
		digitsLeftLabel = new JLabel("Digits Left");
		digitsLeftLabel.setName("digitsLeftLabel");
		movesLeftLabel = new JLabel("moves Left");
		movesLeft = new JTextField(4);
		movesLeft.setName("MovesTextField");
		movesLeftLabel.setName("movesLeftLabel");
		movesLeft.setEditable(false);
	}
	public JTextField getWidget(String widgetName){
		if(widgetName.equals("GuessTextField")){
			return guessDisplay;
		}
		else if (widgetName.equals("MovesTextField")){
			return movesLeft;
		}
		else if (widgetName.equals("DigitsTextField")){
			return digitsLeft;
		}
		return null;
	}
	protected void setContainerOrientation() {
		layout.setInfoConsoleOrientation(guessCont, guessDisplay);
		layout.setInfoConsoleOrientation(digitsCont, digitsLeft);
		layout.setInfoConsoleOrientation(movesCont, movesLeft);
	}

	public void addWidgets(JPanel console) {
		setContainerOrientation();
		console.add(guessDisplay);
		console.add(digitsLeft);
		console.add(digitsLeftLabel);
		console.add(movesLeftLabel);
		console.add(movesLeft);
	}
	public int getDigitsCounter() {
		return digitsCounter;
	}
	public void setDigitsCounter(int digitsCounter) {
		this.digitsCounter = digitsCounter;
	}




}
