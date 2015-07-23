package CowsAndBullsProject;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JComponent;

public class GiveWidgetsLayout {
LayoutManager layout;

	public void setInfoConsoleOrientation(Container cont,
			JComponent partToAdd) {
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

	}
}
