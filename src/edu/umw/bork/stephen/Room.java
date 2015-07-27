
package edu.umw.bork.stephen;

import java.util.ArrayList;

public class Room {

    private String title;
    private String desc;
    private boolean beenHere;
    private ArrayList<Item> contents;

    Room(String title) {
        this.title = title;
        contents = new ArrayList<Item>();
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
            description = title;
        } else {
            description = title + "\n" + desc;
        }
        beenHere = true;
        return description;
    }
    
}
