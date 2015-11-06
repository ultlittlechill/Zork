package edu.umw.cpsc240fall2015team8.zork;

/**
Adds a given integer to the player's score. If a negative number is added, then the player's score is reduced by that many points.
@author Austin
*/
class ScoreEvent extends Event{

	/**
		Creates a new ScoreEvent. Works the same way for all values of i.
	*/
	ScoreEvent(int i){
	}

	/**
		Adds the stored integer to the players score.
		If the integer is negative, then removes points from the player's score.
		If null, the score remains unchanged. 
	*/
	public void execute(){
	}
}
