package otherGames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame{
private JPanel mousepanel;
private JLabel statusbar;
	public GUI() {
		super("Mouse Example");
		mousepanel = new JPanel();
		mousepanel.setBackground(Color.WHITE);
		add(mousepanel, BorderLayout.CENTER);
		statusbar = new JLabel("default");
		add(statusbar,BorderLayout.SOUTH);
		Handlerclass handler = new Handlerclass();
		mousepanel.addMouseListener(handler);
		mousepanel.addMouseMotionListener(handler);
	}
	private class Handlerclass implements MouseListener,MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent event) {
			statusbar.setText("You dragging the mouse");
			
		}

		@Override
		public void mouseMoved(MouseEvent event) {
			statusbar.setText("You moved the mouse");
			
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			statusbar.setText(String.format("Clicked at %d,%d", event.getX(),event.getY()));
			
		}

		@Override
		public void mouseEntered(MouseEvent event) {
			statusbar.setText("You entered the area");
			mousepanel.setBackground(Color.RED);
			
		}

		@Override
		public void mouseExited(MouseEvent event) {
			statusbar.setText("The mouse has exited the area");
			mousepanel.setBackground(Color.WHITE);
		}

		@Override
		public void mousePressed(MouseEvent event) {
			
			statusbar.setText("You pressed down the mouse");
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			statusbar.setText("You released the mouse");
			
		}
		
	}
}
