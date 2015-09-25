
package edu.umw.bork.stephen;

class LookCommand extends Command {

    LookCommand() {
    }

    public String execute() {
        return GameState.instance().getAdventurersCurrentRoom().
            describe(true) + "\n";
    }
}
