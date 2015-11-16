package edu.umw.cpsc240fall2015team8.zork;


/** Contains the methods that will allow a player to check their numerical score.
@author Lucas 
*/
class ScoreCommand extends Command {

	/** initialize a new scorecommand object*/
	ScoreCommand() {

	}
	/** When the player inputs a score command the player's current score is displayed on the screen along with a corresponding message. */
	public String execute() {
	return "Your score is: " + GameState.instance().getScore() + "\n";
	}

}
