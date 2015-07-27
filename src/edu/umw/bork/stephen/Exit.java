
package edu.umw.bork.stephen;

public class Exit {

    private char dir;
    private Room loc;

    Exit(char dir, Room loc) {
        this.dir = dir;
        this.loc = loc;
    }

    String describe() {
        return "You can go " + dir + " to the " + loc.getTitle() + ".";
    }
}
