package CowsAndBullsProject;

import java.awt.Font;

import javax.swing.JButton;

public class Button extends JButton  {
	JButton button;
	Font buttonFont;
	String buttonName ="";


	public Button(String newButtonName) {
		buttonName = newButtonName;
		button = new JButton(buttonName);
		button.setName(buttonName);
		GiveFont font =new GiveFont();
		font.setFont(button);
		
	}
	public JButton getButton(){
		return this.button;
	}
	public String getButtonName() {
		return buttonName;
	}
	public String toString(){
		return buttonName;
	}
}
