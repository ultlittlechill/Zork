
package edu.umw.cpsc240fall2015team8.zork;

/**
Gives the description of the current Room.
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
