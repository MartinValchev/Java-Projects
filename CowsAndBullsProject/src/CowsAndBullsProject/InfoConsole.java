package CowsAndBullsProject;

import java.awt.Color;
import java.awt.Container;

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
		setName(newName);
		consoleName = newName;
		logConsLayout = new GiveLayout();
		logConsLayout.setLayout(infoConsole);
		infoWidgets = new InfoConsoleWidgets();
		infoWidgets.addWidgets(this.getConsole());
	}
	public JTextField getSpecificField(String fieldName){
		return infoWidgets.getWidget(fieldName);
	}
	public void addTimeCounter(Container timeCont){
		infoConsole.add(timeCont);
	}
	@Override
	public JPanel getConsole() {
		// TODO Auto-generated method stub
		return infoConsole;
	}
	public String getName(){
		return consoleName;
	}
	public void setName(String name){
		infoConsole.setName(name);
	}

}
