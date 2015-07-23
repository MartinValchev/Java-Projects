package CowsAndBullsProject;

public class CandBGamePlay {

	public static void main(String[] args){
		// show welcome message
		WelcomeMessage welcome = new WelcomeMessage();
		//choose difficutly
		DifficultySelection diffSelect = new DifficultySelection();
		int playerMoves = diffSelect.getPlayerMoves();
		// draw the mainScreen
		CandBMainPanel mainPanel = new CandBMainPanel(playerMoves);
	}
//Game starts 
	// show welcome message
	//choose difficutly
	// show the game  main screen with moves left set,digits left set=4, gues
	// while (true){
	/*
	 	1.0 user input number
	 	 2.number is being checked (is valid number)
	 	 2.1 number is not valid - show message to the user to enter again number
	 	  -guessDiplay is cleared, digitLeft reset;
	 	 2.2 number is valid - checkCows, checkBulls show cows and bulls number on LogConsole
	 	 - -guessDiplay is cleared;movesLeft--; digitLeft reset;
	 	 3 Check game status - check if game ends
	 	 3.1 if game is not ended - move forward
	 	 3.2 if game ended
	 	 	3.2.1 - Win - Show message to the user that he/she won the game
	 	 	- show message if the user wants another game -if yes show main screen
	 	 	if no close the window;
	 	 	3.2.2 - Loose - Show message to the user that he/she loose the game
	 	 	- show message if the user wants another game -if yes show main screen
	 	 	if no close the window;
	 	 4.
	 	 
	 */
	
}
