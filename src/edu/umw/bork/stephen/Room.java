
package edu.umw.bork.stephen;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

public class Room {

    public class NoRoomException extends Exception {}

    private String title;
    private String desc;
    private boolean beenHere;
    //private ArrayList<Item> contents;
    private ArrayList<Exit> exits;

    Room(String title) {
        init();
        this.title = title;
    }

    /** Given a Reader object positioned at the beginning of a "room" file
        entry, read and return a Room object representing it. 
        @throws NoRoomException The reader object is not positioned at the
        start of a room entry. A side effect of this is the reader's cursor
        is now positioned one line past where it was.
        @throws IOException A generic file I/O error occurred.
     */
    Room(BufferedReader r) throws IOException, NoRoomException,
        Dungeon.IllegalDungeonFormatException {

        init();
        title = r.readLine();
        desc = "";
        if (title.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoRoomException();
        }
        
        String lineOfDesc = r.readLine();
        while (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM) &&
               !lineOfDesc.equals(Dungeon.TOP_LEVEL_DELIM)) {
            desc += lineOfDesc + "\n";
            lineOfDesc = r.readLine();
        }

        // throw away delimiter
        if (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after room.");
        }
    }

    // Common object initialization tasks.
    private void init() {
        //contents = new ArrayList<Item>();
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
        //for (Item item : contents) {
        //    description += "There is a " + item.getName() + " here.\n";
        //}
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
