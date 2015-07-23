package TestProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GiveLayout {

	LayoutManager compLayout;

	public void setLayout(JPanel console) {
		switch (console.getName()) {
		case "ButtonsConsole":
			compLayout = new GridLayout(4, 3);
			break;
		case "InfoConsole":
			compLayout = new FlowLayout();
			break;
		case "MainConsole":
			compLayout = new BorderLayout();
			break;
		case "LogConsole":
			compLayout = new FlowLayout();
			break;
		}
		console.setLayout(compLayout);
		/*
		 * if (consoleName.equals("numeric")){ return new GridLayout(4, 3); }
		 * if(consoleName.equals("info")){ return new FlowLayout(); }
		 * if(consoleName.equals("main")){ return new BorderLayout(); }
		 * if(consoleName.equals("log")){ return new FlowLayout(); } return
		 * null;
		 */

	}
}
