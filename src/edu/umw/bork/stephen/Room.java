
package edu.umw.bork.stephen;

import java.util.ArrayList;

public class Room {

    private String title;
    private String desc;
    private boolean beenHere;
    private ArrayList<Exit> exits;

    Room(String title) {
        this.title = title;
        exits = new ArrayList<Exit>();
        beenHere = false;
    }

    String getTitle() { return title; }

    void setDesc(String desc) { this.desc = desc; }

    public String describe() {
        String description;
        if (beenHere) {
            description = title + "\n";
        } else {
            description = title + "\n" + desc + "\n";
        }
        for (Exit exit : exits) {
            description += exit.describe() + "\n";
        }
        beenHere = true;
        return description;
    }
    
    public Room leaveBy(String dir) {
        for (Exit exit : exits) {
            if (exit.getDir().equals(dir)) {
                return exit.getDest();
            }
        }
        return null;
    }

    void addExit(Exit exit) {
        exits.add(exit);
    }
}
