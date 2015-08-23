
package edu.umw.bork.stephen;

class MovementCommand extends Command {

    private String dir;
                       

    MovementCommand(String dir) {
        this.dir = dir;
    }

    public String execute() {
        Room currentRoom = Adventurer.instance().getRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            Adventurer.instance().setRoom(nextRoom);
            return "";
        } else {
            return "You can't go " + dir + ".\n";
        }
    }
}
