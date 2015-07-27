
package edu.umw.bork.stephen;


public class Dungeon {

    private String name;
    private Room entry;

    Dungeon(String name, Room entry) {
        this.name = name;
        this.entry = entry;
    }

    // for persistence, a big ol constructor will load from file.
    
    public Room getEntry() { return entry; }
    public String getName() { return name; }

}
