
package edu.umw.cpsc240fall2015team8.zork;

/**
Kills the player and ends the game. 
@author Austin
*/
class DieEvent extends Event{

	/**
		Creates a new DieEvent.
	*/
	DieEvent(){
	}

	/**
		Kills the player and ends the game.
	*/
	public void execute(){
		//System.out.println("You have died. Next time, get good.");
		//System.exit(0);
		GameState.instance().setHealth(0);
	}
}
