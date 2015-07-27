
package edu.umw.bork.stephen;

import java.util.ArrayList;

public class Room {

    private String title;
    private String desc;
    private boolean beenHere;
    //private ArrayList<Item> contents;
    private ArrayList<Exit> exits;

    Room(String title) {
        this.title = title;
        //contents = new ArrayList<Item>();
        exits = new ArrayList<Exit>();
        beenHere = false;
    }

    String getTitle() { return title; }

    String getDesc() { 
        return desc;
    }

    void setDesc(String desc) { this.desc = desc; }

    public String describe() {
        String description;
        if (beenHere) {
            description = title + "\n";
        } else {
            description = title + "\n" + desc + "\n";
        }
        //for (Item item : contents) {
        //    description += "There is a " + item.getName() + " here.\n";
        //}
        for (Exit exit : exits) {
            description += exit.describe();
        }
        beenHere = true;
        return description;
    }
    
}
