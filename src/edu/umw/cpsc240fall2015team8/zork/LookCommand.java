
package edu.umw.cpsc240fall2015team8.zork;

/**
Gives the complete description of the current Room, regrardless of whether it has been visited or not.
@author Austin
*/
class LookCommand extends Command {

    /**
	Creates a new LookCommand.
    */
    LookCommand() {
    }

    /**
	Returns a string containing the description of the current Room.
    */
    public String execute() {
        return GameState.instance().getAdventurersCurrentRoom().
            describe(true) + "\n";
    }
}
