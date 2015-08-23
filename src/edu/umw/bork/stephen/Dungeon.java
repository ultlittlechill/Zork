
package edu.umw.bork.stephen;

import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Dungeon {

    public static class IllegalDungeonFormatException extends Exception {
        public IllegalDungeonFormatException(String e) {
            super(e);
        }
    }

    // Variables relating to both dungeon file and game state storage.
    public static String TOP_LEVEL_DELIM = "===";
    public static String SECOND_LEVEL_DELIM = "---";

    // Variables relating to dungeon file (.bork) storage.
    public static String ROOMS_MARKER = "Rooms:";
    public static String EXITS_MARKER = "Exits:";
    public static String ITEMS_MARKER = "Items:";
    
    // Variables relating to game state (.sav) storage.
    static String FILENAME_LEADER = "Dungeon file: ";
    static String ROOM_STATES_MARKER = "Room states:";

    private String name;
    private Room entry;
    private Hashtable<String,Room> rooms;
    private Hashtable<String,Item> items;
    private String filename;

    Dungeon(String name, Room entry) {
        init();
        this.filename = null;    // null indicates not hydrated from file.
        this.name = name;
        this.entry = entry;
    }

    /**
     * Read from the .bork filename passed, and instantiate a Dungeon object
     * based on it.
     */
    public Dungeon(String filename) throws IOException,
        IllegalDungeonFormatException {

        init();
        this.filename = filename;

        BufferedReader r = new BufferedReader(new FileReader(filename));
        name = r.readLine();

        r.readLine();   // Throw away version indicator.

        // Throw away delimiter.
        if (!r.readLine().equals(TOP_LEVEL_DELIM)) {
            throw new IllegalDungeonFormatException("No '" +
                TOP_LEVEL_DELIM + "' after version indicator.");
        }

        // Throw away Items starter.
        if (!r.readLine().equals(ITEMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ITEMS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate items.
            while (true) {
                add(new Item(r));
            }
        } catch (Item.NoItemException e) {  /* end of items */ }

        // Throw away Rooms starter.
        if (!r.readLine().equals(ROOMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ROOMS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate and add first room (the entry).
            entry = new Room(r, this);
            add(entry);

            // Instantiate and add other rooms.
            while (true) {
                add(new Room(r, this));
            }
        } catch (Room.NoRoomException e) {  /* end of rooms */ }

        // Throw away Exits starter.
        if (!r.readLine().equals(EXITS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                EXITS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate exits.
            while (true) {
                Exit exit = new Exit(r, this);
            }
        } catch (Exit.NoExitException e) {  /* end of exits */ }

        r.close();
    }
    
    // Common object initialization tasks, regardless of which constructor
    // is used.
    private void init() {
        rooms = new Hashtable<String,Room>();
        items = new Hashtable<String,Item>();
    }

    /*
     * Store the current (changeable) state of this dungeon to the writer
     * passed.
     */
    void storeState(PrintWriter w) throws IOException {
        w.println(FILENAME_LEADER + getFilename());
        w.println(ROOM_STATES_MARKER);
        for (Room room : rooms.values()) {
            room.storeState(w);
        }
        w.println(TOP_LEVEL_DELIM);
    }

    /*
     * Restore the (changeable) state of this dungeon to that reflected in the
     * reader passed.
     */
    void restoreState(BufferedReader r) throws IOException,
        GameState.IllegalSaveFormatException {

        // Note: the filename has already been read at this point.
        
        if (!r.readLine().equals(ROOM_STATES_MARKER)) {
            throw new GameState.IllegalSaveFormatException("No '" +
                ROOM_STATES_MARKER + "' after dungeon filename in save file.");
        }

        String roomName = r.readLine();
        while (!roomName.equals(TOP_LEVEL_DELIM)) {
            getRoom(roomName.substring(0,roomName.length()-1)).restoreState(r);
            roomName = r.readLine();
        }
    }

    public Room getEntry() { return entry; }
    public String getName() { return name; }
    public String getFilename() { return filename; }
    public void add(Room room) { rooms.put(room.getTitle(),room); }
    public void add(Item item) { items.put(item.getName(),item); }

    public Room getRoom(String roomTitle) {
        return rooms.get(roomTitle);
    }

    public Item getItem(String itemName) {
        return items.get(itemName);
    }
}
