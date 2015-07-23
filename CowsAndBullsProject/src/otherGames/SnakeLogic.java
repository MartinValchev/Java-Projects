package otherGames;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

public class SnakeLogic extends JPanel{

	private static final int playgroundWidth = 500;
	private static final int playgroundHeight = 400;
	private List<DrawingSnake> snakeArray;
	public SnakeLogic(){
	 snakeArray = new ArrayList<DrawingSnake>();
	 
	 DrawingSnake head  = new DrawingSnake(playgroundWidth/2,playgroundHeight/2);
	 head.drawSnake();
	 snakeArray.add(head);
	
	}
	public void move(){
		
	}
	// draw segment
	public void startGame(){
		//drawing the initial snake state.
		for(int i=1;i<=4;i++){
			snakeArray.add(new DrawingSnake((playgroundWidth/2 -i),playgroundHeight/2));
		}
		Iterator it = snakeArray.iterator();
		while(it.hasNext()){
			DrawingSnake snakeElement = (DrawingSnake)it.next();
			snakeElement.drawSnake();
		}
	}
	public void play(){
		startGame();
		
		// move the snake x++
		// if player clicks left button move left x--
		//if player clicks right button move left x++
		//if player clicks up button move up y++
		//if player clicks down button move down y--
		// if snake head crash one of the borders - game ends
		// if snake head catch the tail game ends
		// if snake head catch an apple - add new segment (paint component) -snake tail
	}
	public void GameStarts(){
		// draw the snake head in the middle of the screen
		// draw the snake tail - 3 segments after the head
		
	}

}
