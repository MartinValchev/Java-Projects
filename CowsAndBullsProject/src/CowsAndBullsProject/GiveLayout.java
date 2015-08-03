package CowsAndBullsProject;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GiveLayout {

	LayoutManager compLayout;

	public void setLayout(JPanel console) {
		String panelName = console.getName();
		if (panelName.equals("ButtonsConsole")) {
			compLayout = new GridLayout(4, 3);
		} else if (panelName.equals("InfoConsole")) {
			compLayout = new FlowLayout();
		} else if (panelName.equals("MainConsole")) {
			compLayout = new BorderLayout();
		} else if (panelName.equals("LogConsole")) {
			compLayout = new FlowLayout();
		}
		else if (panelName.equals("UserConsole")) {
			compLayout = new FlowLayout();
		}
		console.setLayout(compLayout);
	}

	// give orientation to the Consoles when they will be added to the Main
	// Console
	public String setMainOrientation(Console addCons) {
		String consName = addCons.getName();
		if (consName.equals("InfoConsole")) {
			return "BorderLayout.NORTH";
		} else if (consName.equals("ButtonsConsole")) {
			return "BorderLayout.CENTER";
		} else if (consName.equals("LogConsole")) {
			return "BorderLayout.EAST";
		}
		else if (consName.equals("UserConsole")){
			return "BorderLayout.SOUTH";
		}
		return null;
	}

}
