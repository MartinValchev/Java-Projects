package CowsAndBullsProject;

import java.awt.Container;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TimeCounter implements ActionListener, MouseListener {
	private Timer timer;
	private Container timeCont;
	private JTextField timeField;
	private long now;
	private long elapsed;
	private long remaining;
	private long lastUpdate;
	private int timerSeconds;
	private int delay;
	private int period;
	private GiveWidgetsLayout layout;
	private GiveFont font;
	private JButton pauseButton;
	private String status;
	private CandBMainPanel mainPanel;

	public TimeCounter(String newSeconds, CandBMainPanel newMainPanel) {
		mainPanel = newMainPanel;
		timeCont = new Container();
		timeCont.setName("TimePanel");
		//timeCont.setSize(300, 200);
		timeField = new JTextField();
		layout = new GiveWidgetsLayout();
		layout.setTimerOrientation(timeCont);
		timeField.setName("TimeField");
		timeField.setColumns(8);
		font = new GiveFont();
		font.setFont(timeField);
		pauseButton = new JButton("Pause");
		pauseButton.setName("PauseButton");
		font.setFont(pauseButton);
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
		status  = "Running";
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
		timeCont.add(timeField);
		timeCont.add(pauseButton);
		timeCont.setVisible(true);
	}
	public Container getTimeCont(){
		return timeCont;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		update();

	}
	public void resetTimer(String seconds){
		pauseTimer();
		timerSeconds = Integer.parseInt(seconds);
		delay = 1000;
		period = 1000;
		remaining = timerSeconds * 1000;
		timeField.setText("Time : " + (remaining/1000));
		lastUpdate = System.currentTimeMillis();
		timeField.addActionListener(this);
		timeField.addMouseListener(this);
		timer = new Timer(period, this);
		timer.setInitialDelay(delay);
		status  = "Running";
		timer.start();
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
			status  = "Paused";
			mainPanel.gameLost();
		}
	}

	public void pauseTimer() {
		now = System.currentTimeMillis();
		remaining -= (now - lastUpdate);
		timeField.setText("Pause");
		status  = "Paused";
		timer.stop();
	}

	public void resumeTimer() {
		
		lastUpdate = System.currentTimeMillis();
		timer.start();
		status  = "Running";
	}

	public int getSecondsRemaining(){
		int secondsRemain = (int)(remaining/1000);
		return secondsRemain;
	}
	public JTextField getTimeField(){
		return timeField;
	}
	public JButton getPauseButton(){
		return pauseButton;
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
		status  = "Paused";
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		resumeTimer();
		status  = "Running";
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public String getStatus(){
		return this.status;
	}
	
}
