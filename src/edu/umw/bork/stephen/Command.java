
package edu.umw.bork.stephen;

// For now, only direction commands. If the "direction" is bogus, then this
// effectively doubles as an UnknownCommand (to be a subclass later).
public class Command {

    private String dir;     // for now, this class is only for direction 
                            // commands.

    Command(String dir) {
        this.dir = dir;
    }

    public String execute() {
        if (dir.equals("save")) {
            try {
                GameState.instance().store();
                return "Data saved to " + GameState.DEFAULT_SAVE_FILE + 
                    GameState.SAVE_FILE_EXTENSION + ".\n";
            } catch (Exception e) {
                System.err.println("Couldn't save!");
                e.printStackTrace();
                return "";
            }
        } else if (CommandFactory.MOVEMENT_COMMANDS.contains(dir)) {
            Room currentRoom = Adventurer.instance().getRoom();
            Room nextRoom = currentRoom.leaveBy(dir);
            if (nextRoom != null) {  // could try/catch here.
                Adventurer.instance().setRoom(nextRoom);
                return "";
            } else {
                return "You can't go " + dir + ".\n";
            }
        }
        return "Unknown command '" + dir + "'?\n";
    }
}
