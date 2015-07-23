package TestProject;

import java.awt.Font;

import javax.swing.JComponent;

public class GiveFont {
	Font font =new Font("Arial", Font.PLAIN, 14);

	public void setFont(JComponent comp) {
		switch (comp.getName()) {
		case "button":
			font = new Font("Arial", Font.BOLD, 16); break;
		case "primaryTextField":
			font =  new Font("Arial", Font.PLAIN, 14);break;
		case "secondaryTextField":
			font =  new Font("Arial", Font.PLAIN, 14);break;
		case "label":
			font =  new Font("verdana", Font.BOLD, 14);break;
		case "textArea":
			font =  new Font("verdana", Font.PLAIN, 16);break;
		case "welcome":
			font =  new Font("verdana", Font.BOLD, 12);break;
		}
		comp.setFont(font);

	}
}
