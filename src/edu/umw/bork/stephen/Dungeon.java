
package edu.umw.bork.stephen;

import java.util.ArrayList;

public class Dungeon {

    private String name;
    private Room entry;
    private ArrayList<Room> rooms;

    Dungeon(String name, Room entry) {
        this.name = name;
        this.entry = entry;
        rooms = new ArrayList<Room>();
    }

    // for persistence, a big ol constructor will load from file.
    
    public Room getEntry() { return entry; }
    public String getName() { return name; }
    public void add(Room room) { rooms.add(room); }

}
