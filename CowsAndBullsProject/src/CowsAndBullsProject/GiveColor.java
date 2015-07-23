package CowsAndBullsProject;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;

public class GiveColor {
Color color;
	public  GiveColor (){
		color = new Color(1,1,1);
	}
		public void setColor(JComponent comp) {
			switch (comp.getName()) {
			case "Check":
				color = new Color(0,255,0); break;
			case "MainConsole":
				color = new Color(102, 204, 255); break;
			case "textArea":
				color = new Color(255, 255, 255); break;
			
			}
			comp.setBackground(color);

		}

}
