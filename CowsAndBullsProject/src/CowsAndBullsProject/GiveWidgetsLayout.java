package CowsAndBullsProject;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GiveWidgetsLayout {
	LayoutManager layout;

	public void setInfoConsoleOrientation(Container cont, JComponent partToAdd) {
		layout = null;
		String panelName = partToAdd.getName();
		if (panelName.equals("GuessTextField")) {
			layout = new FlowLayout(FlowLayout.LEFT, -30, 20);
			cont.setLayout(layout);

		} else if (panelName.equals("DigitsTextField")) {
			layout = new FlowLayout(FlowLayout.CENTER, 25, 20);
			cont.setLayout(layout);
		} else if (panelName.equals("MovesTextField")) {
			layout = new FlowLayout(FlowLayout.RIGHT, 25, 20);
			cont.setLayout(layout);
		}
		else if (panelName.equals("timeField")){
			layout = new FlowLayout(FlowLayout.LEFT, 10, 20);
			cont.setLayout(layout);
		}
		else if (panelName.equals("timeButton")){
			layout = new FlowLayout(FlowLayout.RIGHT, 55, 20);
			cont.setLayout(layout);
		}
	}

	public void setTimerOrientation(Container timeCont) {
		timeCont.setLayout(new FlowLayout());
	}
}
