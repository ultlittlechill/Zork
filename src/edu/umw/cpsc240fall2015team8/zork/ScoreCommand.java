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
		//return "Your score is: " + GameState.instance().getScore() + "\n";
		int score = GameState.instance().getScore();
		Stirng r = "Your rank is: ";	

		if(score = 0)
			return r + "n00b\n";
		else if(score ==1)
			return r + "Looser\n";
		else if(score ==2)
			return r + "Fool\n";
		else if(score ==3)
			return r + "Lucas\n";
		else if(score ==4)
			return r + "Droid\n";
		else if(score ==5)
			return r + "Clone Trooper\n";
		else if(score ==6)
			return r + "Youngling\n";
		else if(score ==7)
			return r + "Jedi Padawan\n";
		else if(score == 8)
			return r + "Jedi Knight\n";
		else if(score == 9)
			return r + "Jedi Master\n";
		else if(score == 10)
			return r + "Baller\n";
		else
			return r + "N/A. Also, you should not be able to read this messaage\nThere is a problem.\n";
	}

}
