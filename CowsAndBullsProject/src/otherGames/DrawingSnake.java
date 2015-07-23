package otherGames;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingSnake extends JPanel{
	private int xPoint;
	private int yPoint;
	private  static final int snakeRec=7;
	public DrawingSnake(int xPoint,int yPoint){
		this.xPoint = xPoint;
		this.yPoint = yPoint;
	}
	public void drawSnake(){
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLUE);
		g.fillRect(xPoint, yPoint, snakeRec, snakeRec);
	}
}