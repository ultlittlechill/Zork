
package edu.umw.cpsc240fall2015team8.zork;

/**
Handles any anyknown commands given by the player, informing the caller that the command is unknown.
@author Austin
*/
class UnknownCommand extends Command {

    private String bogusCommand;

    /**
	Creates a new UnknownCommand from the given string. If null or an empty string are passed, behaves the same way.
    */
    UnknownCommand(String bogusCommand) {
        this.bogusCommand = bogusCommand;
    }

    /**
	Returns a string which says that the held string is not a known command.
    */
    String execute() {
        return "I'm not sure what you mean by \"" + bogusCommand + "\".\n";
    }
}
