package CowsAndBullsProject;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LogConsole implements Console {
	JPanel logConsole;
	JTextArea textArea;
	String consoleName;
	public LogConsole(String newName) {
		logConsole = new JPanel();
		consoleName = newName;
		setName(consoleName);
		GiveLayout logConsLayout = new GiveLayout();
		logConsLayout.setLayout(logConsole);
		textArea =new JTextArea("Guess Log  \n", 15, 12);
		textArea.setName("textArea");
		GiveFont logFont = new GiveFont();
		logFont.setFont(textArea);
		logConsole.add(textArea);
	}
	public JTextArea getLogArea(){
		return textArea;
	}
	@Override
	public JPanel getConsole() {
		// TODO Auto-generated method stub
		return logConsole;
	}
	@Override
	public String getName(){
		return consoleName;
	}
	public void setName(String name){
		logConsole.setName(name);
	}

}
