
package edu.umw.cpsc240fall2015team8.zork;

/**
Ends the game and declares that the player has won.
@author Austin
*/
class WinEvent extends Event{

	/**
		Creates a new WinEvent.
	*/
	WinEvent(){
	}

	/**
		Declares that the player has won the game, and then ends the game.		 
	*/
	public void execute(){
		System.out.println("You have won!");
		System.exit(0);
	}
}
