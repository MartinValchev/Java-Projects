package CowsAndBullsProject;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoConsole implements Console {
	JPanel infoConsole;
	Color backgroundColor;
	InfoConsoleWidgets infoWidgets;
	GiveLayout logConsLayout;
	String consoleName;
	public InfoConsole(String newName) {
		infoConsole = new JPanel();
		consoleName = newName;
		logConsLayout = new GiveLayout();
		logConsLayout.setLayout(this);
		infoWidgets = new InfoConsoleWidgets();
		infoWidgets.addWidgets(this.getConsole());
	}
	public JTextField getSpecificField(String fieldName){
		return infoWidgets.getWidget(fieldName);
	}
	
	@Override
	public JPanel getConsole() {
		// TODO Auto-generated method stub
		return infoConsole;
	}
	public String getName(){
		return consoleName;
	}

}
