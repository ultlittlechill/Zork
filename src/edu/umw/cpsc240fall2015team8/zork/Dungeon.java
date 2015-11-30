
package edu.umw.cpsc240fall2015team8.zork;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/** Contains Rooms and Items; Has methods to create from .zork and .sav files, and save to .sav files.
@author Jeff Wallhermfechtel*/
public class Dungeon {

    /** Gets thrown whenever a text file representing a Dungeon object is not properly formatted. */
    public static class IllegalDungeonFormatException extends Exception {
/**Creates an IllegalDungeonFormatException*/
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
    private ArrayList<Npc> npcs;
    private String filename;

    /**
    Creates a Dungeon object when passed a String for the name and a Room for the entry.
    */
    Dungeon(String name, Room entry) {
        init();
        this.filename = null;    // null indicates not hydrated from file.
        this.name = name;
        this.entry = entry;
        rooms = new Hashtable<String,Room>();
    }

    /**
 Creates a Dungeon object from the filename passed.
 @Throws FileNotFoundException if it can not find the file from the filename passed
 @Throws IllegalDungeonFormatException if the zork file does not meet the proper requirements.
     */
    public Dungeon(String filename) throws FileNotFoundException, 
        IllegalDungeonFormatException {

        this(filename, true);
    }

    /**
	Creates a Dungeon object from the filename passed, and can place its items in thier original locations if
	a true boolean is passed in the constructor.
	@Throws FileNotFoundException if it can not find the file from the filename passed
	@Throws IllegalDungeonFormatException if the zork file does not meet the proper requirements
     */
    public Dungeon(String filename, boolean initState) 
        throws FileNotFoundException, IllegalDungeonFormatException {

        init();
        this.filename = filename;

        Scanner s = new Scanner(new FileReader(filename));
        name = s.nextLine();

        if(s.nextLine().equals("Zork v1.0")){   // Throw away version indicator.

        	// Throw away delimiter.
        	if (!s.nextLine().equals(TOP_LEVEL_DELIM)) {
            		throw new IllegalDungeonFormatException("No '" +
                	    TOP_LEVEL_DELIM + "' after version indicator.");
        	}

        	// Throw away Items starter.
        	if (!s.nextLine().equals(ITEMS_MARKER)) {
            		throw new IllegalDungeonFormatException("No '" +
                	    ITEMS_MARKER + "' line where expected.");
        	}

        	try {
            		// Instantiate items.
            		while (true) {
                		add(new Item(s));
            		}
       	 	} catch (Item.NoItemException e) {  /* end of items */ }

        	// Throw away Rooms starter.
        	if (!s.nextLine().equals(ROOMS_MARKER)) {
            		throw new IllegalDungeonFormatException("No '" +
                	    ROOMS_MARKER + "' line where expected.");
        	}

        	try {
            		// Instantiate and add first room (the entry).
            		entry = new Room(s, this, initState);
            		add(entry);

            		// Instantiate and add other rooms.
            		while (true) {
                		add(new Room(s, this, initState));
            		}
        	} catch (Room.NoRoomException e) {  /* end of rooms */ }

        	// Throw away Exits starter.
        	if (!s.nextLine().equals(EXITS_MARKER)) {
            		throw new IllegalDungeonFormatException("No '" +
                	    EXITS_MARKER + "' line where expected.");
        	}

        	try {
            		// Instantiate exits.
            		while (true) {
                		Exit exit = new Exit(s, this);
            		}
        	} catch (Exit.NoExitException e) {  /* end of exits */ }
	}else{
		s.nextLine(); // delimiter
		s.nextLine(); // DurableItems
		try{
			while(true){
				add(new DurableItem(s));
			}
		}catch(Item.NoItemException e){} // Done with items
		s.nextLine(); // NPCs:
		try{
			while(true){
				add(new Npc(s, this));
			}
		}catch(Npc.NoNpcException e){} // Done with NPCs
		s.nextLine(); // Rooms:
		try{
			entry = new Room(s, this, initState);
			add(entry);
			while(true){
				add(new Room(s, this, initState));
			}
		}catch(Room.NoRoomException e){} // Done with Rooms
		s.nextLine(); // Exits:
		try{
			while(true){
				Exit exit = new Exit(s, this);
			}
		}catch(Exit.NoExitException e){} // Done with Exits
	}
        s.close();
    }
    
/**Common object initialization tasks, regardless of which constructor
    is used.*/
    private void init() {
        rooms = new Hashtable<String,Room>();
        items = new Hashtable<String,Item>();
	npcs = new ArrayList<Npc>();
    }

    /**
     * Store the current (changeable) state of this dungeon to the writer
     * passed. 
     @Throws IOException if Room.storeState() throws IOException
     */
    void storeState(PrintWriter w) throws IOException {
        w.println(FILENAME_LEADER + getFilename());
        w.println(ROOM_STATES_MARKER);
        for (Room room : rooms.values()) {
            room.storeState(w);
        }
        w.println(TOP_LEVEL_DELIM);
    }

    /**
     * Restores the (changeable) state of this dungeon to that reflected in the
     * reader passed.
     * @param Scanner A scanner holding the save file of the dungeon.
     * @Throws IllegalSaveFormatException if there is no "Room states:" marker after the dungeon file name
     */
    void restoreState(Scanner s) throws GameState.IllegalSaveFormatException {

        // Note: the filename has already been read at this point.
        
        if (!s.nextLine().equals(ROOM_STATES_MARKER)) {
            throw new GameState.IllegalSaveFormatException("No '" +
                ROOM_STATES_MARKER + "' after dungeon filename in save file.");
        }

        String roomName = s.nextLine();
        while (!roomName.equals(TOP_LEVEL_DELIM)) {
            getRoom(roomName.substring(0,roomName.length()-1)).
                restoreState(s, this);
            roomName = s.nextLine();
        }
    }

/**Returns the entry room for this dungeon.
   @return The entry room of this dungeon */
    public Room getEntry() { return entry; }

/**Returns the name of this dungeon as a String.*/
    public String getName() { return name; }

/**Returns a String containing the file's name that the dungeon is built from with path information.*/
   public String getFilename() { return filename; }

/**Adds the room passed to this Dugeon's collection of rooms.*/
    public void add(Room room) { rooms.put(room.getTitle(),room); }

/**Adds the item passed to this Dungeon's collection of Items.*/
    public void add(Item item) { items.put(item.getPrimaryName(),item); }

/**Adds the Npc passed to this Dungeon's collection of Npcs. */
    public void add(Npc npc) { npcs.add(npc); }

/** Removes a passed Item from the collection of Items held by this Dungeon. */
    public void remove(Item item) { items.remove(item.getPrimaryName()); }

/**Returns the room in this dungeon whose name equals the String passed. If no room exists with such name, returns null.*/
    public Room getRoom(String roomTitle) {
        return rooms.get(roomTitle);
    }

/**Returns an array of all of the Room objects contianed in this Dungeon. If there are no Room objects in this Dungeon, then the array is empty.*/
    public Room[] getRooms(){
	Room[] rms = new Room[0];
	return rooms.values().toArray(rms);
    }

    /**
     * Returns the Item object whose primary name is passed.
     * If the Item does not exist, throws a NoItemException.
     * @param String The primary name of the Item that is to be returned.
     */
    public Item getItem(String primaryItemName) throws Item.NoItemException {
        
        if (items.get(primaryItemName) == null) {
            throw new Item.NoItemException();
        }
        return items.get(primaryItemName);
    }

    public Npc getNpc(String name){
	for(int i = 0; i < npcs.size(); i++){
		if(npcs.get(i).getName().equals(name)){
			return npcs.get(i);
		}
	}
	return null;
    }
}
