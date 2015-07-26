package CowsAndBullsProject;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TimeCounter implements ActionListener, MouseListener {
	private int interval;
	private int range;
	private Timer timer;
	private JFrame frame;
	private JTextField timeField;
	private long now;
	private long elapsed;
	private long remaining;
	private long lastUpdate;
	private int timerSeconds;
	private int delay;
	private int period;
	private JButton pauseButton;

	public TimeCounter(String newSeconds) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setLayout(new FlowLayout());
		timeField = new JTextField();
		timeField.setColumns(5);
		pauseButton = new JButton("Pause");
		timerSeconds = Integer.parseInt(newSeconds);
		delay = 1000;
		period = 1000;
		remaining = timerSeconds * 1000;
		timeField.setText("Time : " + (remaining/1000));
		lastUpdate = System.currentTimeMillis();
		timeField.addActionListener(this);
		timeField.addMouseListener(this);
		timer = new Timer(period, this);
		timer.setInitialDelay(delay);
		timer.start();
		pauseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pauseButton.getText().equals("Pause")){
					pauseButton.setText("Resume");
					pauseTimer();
				}
				else{
					pauseButton.setText("Pause");
					resumeTimer();
				}
				
				
			}
			
		});
		frame.add(timeField);
		frame.add(pauseButton);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		TimeCounter timeCount = new TimeCounter("27");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();

	}

	public void update() {

		now = System.currentTimeMillis(); // current time in ms
		elapsed = now - lastUpdate; // ms elapsed since last update
		remaining = remaining - elapsed; // adjust remaining time
		lastUpdate = now; // remember this update time
		int seconds = (int) ((remaining) / 1000);
		String input = Integer.toString(seconds);
		timeField.setText("Time  : " + input);
		// Convert remaining milliseconds to mm:ss format and display
		if (remaining < 0) {
			remaining = 0;
		}
		if (remaining == 0) {
			timer.stop();
		}
	}

	private void pauseTimer() {
		now = System.currentTimeMillis();
		remaining -= (now - lastUpdate);
		timeField.setText("Pause");
		timer.stop();
	}

	public void resumeTimer() {
		lastUpdate = System.currentTimeMillis();
		timer.start();
	}

	/*
	 * private static final int setInterval() { if (interval == 1) timer return
	 * --interval; }
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		timeField.setText("Paused");
		pauseTimer();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		resumeTimer();

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
