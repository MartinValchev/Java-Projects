package CowsAndBullsProject;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainConsole implements Console{
	JFrame frame;
	JPanel mainPanel;
	GiveLayout mainConsLayout;
	String consoleName;
	public MainConsole(String newName) {
		mainPanel = new JPanel();
		consoleName = newName;
		setName(consoleName);
		//frame = new JFrame();
		//frame.setMinimumSize(new Dimension(500,400));
		mainConsLayout = new GiveLayout();
		mainConsLayout.setLayout(mainPanel);
		mainPanel.setLocation(50, 50);
		mainPanel.setVisible(true);
	}
	public void addPanels(Console addCons){
		String orientation = "BorderLayout." + mainConsLayout.setMainOrientation(addCons);
		mainPanel.add(addCons.getConsole(),orientation);
	}
	@Override
	public JPanel getConsole() {
		// TODO Auto-generated method stub
		return mainPanel;
	}
	@Override
	public String getName(){
		return consoleName;
	}
	public void setName(String newName){
		mainPanel.setName(newName);
	}

}
