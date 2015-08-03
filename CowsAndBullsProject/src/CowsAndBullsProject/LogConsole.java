package CowsAndBullsProject;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		textArea =new JTextArea("Guess Log  \n", 15, 15);
		JScrollPane vScroll = new JScrollPane(textArea);
		vScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea.setName("textArea");
		textArea.setEditable(false);
		GiveFont logFont = new GiveFont();
		logFont.setFont(textArea);
		//logConsole.add(textArea);
		logConsole.add(vScroll);
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
