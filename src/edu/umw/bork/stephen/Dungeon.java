
package edu.umw.bork.stephen;

import java.util.Hashtable;

public class Dungeon {

    private String name;
    private Room entry;
    private Hashtable<String,Room> rooms;

    Dungeon(String name, Room entry) {
        this.name = name;
        this.entry = entry;
        rooms = new Hashtable<String,Room>();
    }

    public Room getEntry() { return entry; }
    public String getName() { return name; }
    public void add(Room room) { rooms.put(room.getTitle(), room); }

    public void getRoom(String roomTitle) {
        return rooms.get(roomTitle); 
    }

}
