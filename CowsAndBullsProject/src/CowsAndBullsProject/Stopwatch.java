package CowsAndBullsProject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer; // not java.util.Timer
import java.text.NumberFormat;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Stopwatch {
	static int interval;
	static int range;
	static Timer timer;
	static JFrame frame;
	static JTextField timeField;
	private static long startTime;
	private static long pauseTime;
	private static long resumeTime;
	private static int delay;
	private static int period;

	public Stopwatch() {
		startTime = 0;
		delay = 1000;
		period = 1000;
	}

	public static void main(String[] args) {
		String secs = "50";
		frame = new JFrame();
		frame.setSize(100, 200);
		timeField = new JTextField(5);
		delay = 1000;
		period = 1000;
		frame.add(timeField);
		frame.setVisible(true);
		timer = new 
		interval = Integer.parseInt(secs);
		range = interval;
		timeField.setText(secs);
		startTime = System.currentTimeMillis();
		timer..scheduleAtFixedRate(new TimerTask() {
			
			public void run() {
				
				timeField.setText(Integer.toString(setInterval()));

			}
		}, delay, period);
		timeField.addMouseListener(new Listener());
		timeField.addMouseMotionListener(new Listener());
	}

	private static final int setInterval() {
		if (interval == 1)
			timer.cancel();
		return --interval;
	}

	public static void pause() {
		pauseTime = System.currentTimeMillis();
		resumeTime = (pauseTime - startTime);
		timeField.setText("Pause");
		
	}

	public static void resume() {
		// start from the pause moment;
		int secondsRemaining = (int)((range*1000 -  resumeTime)/1000);
		interval = secondsRemaining;
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				startTime = System.currentTimeMillis();
				timeField.setText(Integer.toString(setInterval()));

			}
		}, delay, period);
		
	}

	public class myTimerTask extends TimerTask {

		public void run() {
			startTime = System.currentTimeMillis();
			timeField.setText(Integer.toString(setInterval()));

		}
	}

}

class Listener implements MouseListener, MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		Stopwatch.pause();

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		Stopwatch.resume();

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
