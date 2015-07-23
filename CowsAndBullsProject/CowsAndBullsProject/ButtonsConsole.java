package CowsAndBullsProject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonsConsole implements Console {
	private static final int NUM_QUANTITY =9;
	private JTextField guessWidget;
	private JTextField movesWidget;
	private JTextField digLeftWidget;
	private JPanel console;
	private List<JButton> consoleButtons;
	private GiveLayout consLayout;
	private String consoleName;
	private static ButtonsConsole instance;
	private StringBuilder numsLine;
	private static final int GUESS_DIGITS = 4;
  
	    public static ButtonsConsole getInstance() {
        if(instance == null) {
           instance = new ButtonsConsole();
  
        }
        return instance;
     }

	protected ButtonsConsole() {
		console = new JPanel();
		setName("ButtonsConsole");
		consoleButtons = new ArrayList();
		GiveLayout buttonConsLayout = new GiveLayout();
		buttonConsLayout.setLayout(this);
		createButtons();
		addButtons();
	}

	private void createButtons(){
		JButton current = null;
		for (int i = NUM_QUANTITY; i >=0 ; i--) {
			String name = Integer.toString(i);
			current = new Button(name).getButton();
			current.setName(name);
			consoleButtons.add(current);
			
		}
		current  = (new Button("C")).getButton();
		current.setName("C");
		consoleButtons.add(current);
		current = (new Button("Check")).getButton();
		current.setName("Check");
		consoleButtons.add(current);
	}
	private void addButtons(){
		for (JButton button: consoleButtons){
			console.add(button);
		}

	}
	public JButton getSpecButton(String name){
		JButton foundButton = null;
		for (JButton button : consoleButtons ){
			if (name != null && name.equals(button.getName())){
				foundButton = button;
				return foundButton;
			}
		}
		return foundButton;
	}
	public JPanel getConsole(){
		return console;
	}
	public String getName(){
		return consoleName;
	}
	public void setName(String newName){
		consoleName = newName;
	}

}
