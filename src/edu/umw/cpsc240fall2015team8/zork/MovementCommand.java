
package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;

/** Moves an adventurer to another Room.

@author Lucas */
class MovementCommand extends Command {
    private String dir;
                       
/** Creates a new MovementCommand, which can be executed to move the adventurer in the direction represented by the String passed. 
*/
    MovementCommand(String dir) {
        this.dir = dir;
    }
/** Moves the adventurer from their current room to the room in the direction that was passed into the constructor; returns a String containing the desciption of the new Room.
If the direction that was passed into the constructor is not 'u','d', 'n', 's', 'e', or 'w', or is a direciton in which there is no Exit (and therefore no Room), then returns a String informing the player that they can not go in that direction. */
    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
		if(nextRoom != null && !currentRoom.getExit(dir).allowPassage())
			return "You do not have the key needed to go " + dir + ".\n";
		else if (nextRoom != null) {  // could try/catch here.
            		GameState.instance().setAdventurersCurrentRoom(nextRoom);
            		ArrayList<String> keys = currentRoom.getExit(dir).getKeysNeeded();
			if(keys.size() > 0){
				return "\nYou temporarily unlocked this exit!\n\n" + nextRoom.describe() + "\n";
			}else{
	    			return "\n" + nextRoom.describe() + "\n";
			}
        	} else {
            		return "You can't go " + dir + ".\n";
        	}
    }
}
