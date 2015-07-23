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
		consoleName = newName;
		//frame = new JFrame();
		//frame.setMinimumSize(new Dimension(500,400));
		mainPanel = new JPanel();
		mainConsLayout = new GiveLayout();
		mainConsLayout.setLayout(this);
		mainPanel.setLocation(50, 50);
		mainPanel.setVisible(true);
	}
	public void addPanels(Console addCons){
		String orientation = mainConsLayout.setMainOrientation(addCons);
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

}
