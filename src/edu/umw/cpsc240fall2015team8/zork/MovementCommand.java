
package edu.umw.cpsc240fall2015team8.zork;

class MovementCommand extends Command {
/** Contains the methods for moving an adventurer from one room to the author.
*/
/** @author Lucas */
    private String dir;
                       
/** intialize a new dir object 
*/
    MovementCommand(String dir) {
        this.dir = dir;
    }
/** when the player inputs a move command then this method will check with GameState to get the room the adventurer currently is in then and then moves them to the next one. */
    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            GameState.instance().setAdventurersCurrentRoom(nextRoom);
            return "\n" + nextRoom.describe() + "\n";
        } else {
            return "You can't go " + dir + ".\n";
        }
    }
}
