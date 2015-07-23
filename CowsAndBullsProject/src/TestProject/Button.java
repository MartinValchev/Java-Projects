package TestProject;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;

public class Button {
	JButton button;
	Font buttonFont;
	String buttonName ="";

	public Button(String newButtonName) {
		buttonName = newButtonName;
		button = new JButton(buttonName);
		GiveFont buttonFont = new GiveFont();
		button.setFont(buttonFont.getFont("button"));
		
	}

	public String getButtonName() {
		return buttonName;
	}
	public JComponent getGraphElement() {
		return this.button;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "button";
	}

}
