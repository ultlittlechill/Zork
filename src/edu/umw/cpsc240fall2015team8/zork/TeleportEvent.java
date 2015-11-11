
package edu.umw.cpsc240fall2015team8.zork;

import java.util.Random;

/**
Changes the players current room to another, random one. 
@author Austin
*/
class TeleportEvent extends Event{

	/**
		Creates a new TeleportEvent.
	*/
	TeleportEvent(){
	}

	/**
		Randomly changes the players current room to another one.	
	*/
	public void execute(){
		Random r = new Random();
		Dungeon d = GameState.instance().getDungeon();
		//Randomly get a room
		//Add a method to Dungeon that gives rooms in an ArrayList
	}
}
