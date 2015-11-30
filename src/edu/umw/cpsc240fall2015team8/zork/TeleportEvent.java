
package edu.umw.cpsc240fall2015team8.zork;

import java.util.Random;

/**
Changes the players current room to another, random one. 
@author Austin
*/
class TeleportEvent extends Event{

	String room;

	/**
		Creates a new TeleportEvent.
	*/
	TeleportEvent(){
	}

	TeleportEvent(String r){
		room = r;
	}

	/**
		Randomly changes the players current room to another one.	
	*/
	public void execute(){
	    if(room != null){
		Dungeon d = GameState.instance().getDungeon();
		Room r = d.getRoom(room);
		GameState.instance().setAdventurersCurrentRoom(r);
	    }else{
		Random r = new Random();
		Dungeon d = GameState.instance().getDungeon();
		//Randomly get a room
		Room[] rs = d.getRooms();
		Room nr = rs[r.nextInt(rs.length)];
		GameState.instance().setAdventurersCurrentRoom(nr);
		//System.out.println(nr.describe());
		//Add a method to Dungeon that gives rooms in an ArrayList
	    }
	}
}
