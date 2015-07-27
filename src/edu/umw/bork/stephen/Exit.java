
package edu.umw.bork.stephen;

public class Exit {

    private String dir;
    private Room loc;

    Exit(String dir, Room loc) {
        this.dir = dir;
        this.loc = loc;
    }

    String describe() {
        return "You can go " + dir + " to " + loc.getTitle() + ".";
    }

    String getDir() { return dir; }

    Room getLoc() { return loc; }
    
}
