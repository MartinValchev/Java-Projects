package CowsAndBullsProject;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Stopwatch {
	static int interval;
	static Timer timer;
	static JFrame frame;
	static JTextField  timeField;
	public static void main(String[] args) {
		String secs = "20";
		frame = new JFrame();
		frame.setSize(100, 200);
		timeField = new JTextField(5);
		frame.add(timeField);
		frame.setVisible(true);
		int delay = 1000;
		int period = 1000;
		timer = new Timer();
		interval = Integer.parseInt(secs);
		timeField.setText(secs);
		timer.scheduleAtFixedRate(new TimerTask() {
			
			public void run() {
				timeField.setText(Integer.toString(setInterval()));

			}
		}, delay, period);
	}

	private static final int setInterval() {
		if (interval == 1)
			timer.cancel();
		return --interval;
	}

}
