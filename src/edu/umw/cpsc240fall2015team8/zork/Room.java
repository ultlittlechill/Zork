
package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

/**
    Contains Items and Exits, as well as a title, and description. There are also methods used to manipulate the stored data.
@author Austin
*/
public class Room {

    class NoRoomException extends Exception {}

    static String CONTENTS_STARTER = "Contents: ";

    private String title;
    private String desc;
    private boolean beenHere;
    private ArrayList<Item> contents;
    private ArrayList<Exit> exits;
    private ArrayList<Npc> characters;
	private ArrayList<Item> litItems;
    private boolean isLit;
    
    /**
	Creates a new room object from a given string. If the string is null, it still works.
    */
    Room(String title) {
        init();
        this.title = title;
    }

    /**
	Creates a new room object from a given scanner and dungeon. If the file represented by scanner is not formatted correctly, throws an IllegalDungeonFormatException. If there is no room represented in the scanner, throws a NoRoomException.
    */
    Room(Scanner s, Dungeon d) throws NoRoomException,
        Dungeon.IllegalDungeonFormatException {

        this(s, d, true);
    }

    /**
	Creates a new room object from a given scanner, dungeon object, and boolean. If the boolean is true, items are added. If the boolean is false, no items are added. If the file is inproperly formatted, then a IllegalDungeonFormatException is thrown. If the file contains no data representing a room, then the NoRoomException is trown.  
     */
    Room(Scanner s, Dungeon d, boolean initState) throws NoRoomException,
        Dungeon.IllegalDungeonFormatException {

        init();
        title = s.nextLine();
        desc = "";
        if (title.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoRoomException();
        }
        
        String lineOfDesc = s.nextLine();
        while (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM) &&
               !lineOfDesc.equals(Dungeon.TOP_LEVEL_DELIM)) {

            if (lineOfDesc.startsWith(CONTENTS_STARTER)) {
                String itemsList = lineOfDesc.substring(CONTENTS_STARTER.length());
                String[] itemNames = itemsList.split(",");
                for (String itemName : itemNames) {
                    try {
                        if (initState) {
                            add(d.getItem(itemName));
                        }
                    } catch (Item.NoItemException e) {
                        throw new Dungeon.IllegalDungeonFormatException(
                            "No such item '" + itemName + "'");
                    }
                }
	    } else if(lineOfDesc.startsWith("People:")){
		lineOfDesc = lineOfDesc.substring(8,lineOfDesc.length());
		String[] people = lineOfDesc.split(",");
		for (String person : people) {
			//System.out.println(person);
			if(initState){
				add(d.getNpc(person));
			}
		}
            }
			else if(lineOfDesc.startsWith("Lit:")){
				isLit = Boolean.parseBoolean(lineOfDesc.substring(4,lineOfDesc.length()).trim());
			}

		 	else {
                desc += lineOfDesc + "\n";
            }
            lineOfDesc = s.nextLine();
        }

        // throw away delimiter
        if (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after room.");
        }
    }

    // Common object initialization tasks.
    /**
	Initializes basic variables to be used. 
    */
    private void init() {
        contents = new ArrayList<Item>();
        exits = new ArrayList<Exit>();
		characters = new ArrayList<Npc>();
        beenHere = false;
		isLit = true;
    }
    /**
	Returns the title of the room.
    */
    String getTitle() { return title; }

    /**
	Sets the description for the room to be the given string. If given string is null, it still works the same way.
    */
    void setDesc(String desc) { this.desc = desc; }

    /*
     * Store the current (changeable) state of this room to the writer
     * passed.
     */

    /**
	Stores the state of the room to the PrintWriter given. Throws IOException if PrintWriter throws an IOException. 
    */
    void storeState(PrintWriter w) throws IOException {
        w.println(title + ":");
        w.println("beenHere=" + beenHere);
        if (contents.size() > 0) {
            w.print(CONTENTS_STARTER);
            for (int i=0; i<contents.size()-1; i++) {
                w.print(contents.get(i).getPrimaryName() + ",");
            }
            w.println(contents.get(contents.size()-1).getPrimaryName());
        }
        if (characters.size() > 0) {
		w.print("Npcs: ");
		for (int i = 0; i < characters.size()-1; i++) {
			w.print(characters.get(i).getName() + ",");
		}
		w.println(characters.get(characters.size()-1).getName());
	}
	w.println(Dungeon.SECOND_LEVEL_DELIM);
    }

    /**
	Changes the state of the room to reflect the state of the room represented in the given scanner. Uses the given dungeon object to obtain the Items needed. Throws IllegalSaveFromatException if the file represented by the scanner is not properly formatted. 
    */
    void restoreState(Scanner s, Dungeon d) throws 
        GameState.IllegalSaveFormatException {

        String line = s.nextLine();
        if (!line.startsWith("beenHere")) {
            throw new GameState.IllegalSaveFormatException("No beenHere.");
        }
        beenHere = Boolean.valueOf(line.substring(line.indexOf("=")+1));

        line = s.nextLine();
        if (line.startsWith(CONTENTS_STARTER)) {
            String itemsList = line.substring(CONTENTS_STARTER.length());
            String[] itemNames = itemsList.split(",");
            for (String itemName : itemNames) {
                try {
                    add(d.getItem(itemName));
                } catch (Item.NoItemException e) {
                    throw new GameState.IllegalSaveFormatException(
                        "No such item '" + itemName + "'");
                }
            }
            s.nextLine();  // Consume "---" or grab Npcs.
        }
	if(line.startsWith("Npcs:")){
		String npcList = line.substring(6);
		String[] npcNames = npcList.split(",");
		for(String npcName : npcNames){
			add(d.getNpc(npcName));
		}
		s.nextLine(); // Consume "---".
	}
    }

    /**
	Returns the return value from describe(boolean) with an arguement of false.
    */
    public String describe() {
        return describe(false);
    }

    /**
	Returns a string containing the exits from this room, the items in this room, and a description of this room; If this
	Room is not lit, the returned String only contains the exits from this room and a statement saying that the Room is dark.
	 If the room has been visited already, then the description is only the title, else it is the title and the set
	 description. If the player typed look, then this is all overriden and the description is both the title and the
	 set description.
    */
    public String describe(boolean full) {
        String description;
        if (beenHere && !full) {
            description = title;
        } else {
			if(GameState.instance().getIsLit() || this.isLit || (this.litItems.size>0)){
        		description = title + "\n" + desc;
//			else
//				description = title + "\nIt is pitch black. You are likely to be eaten by a grue.";
        
				for (Npc npc : characters){
					description += "\nThere is a " + npc.getName() + " here!";
				}
				if(characters.size() > 0) { description += "\n";}
					for (Item item : contents) {
						description += "\nThere is a " + item.getPrimaryName() + " here.";
						}
						if (contents.size() > 0) { description += "\n"; }
						if (!beenHere || full) {
							for (Exit exit : exits) {
								description += "\n" + exit.describe();
							}
						}
						beenHere = true;
					}
				else
					description = title + "\nIt is pitch black. You are likely to be eaten by a grue.";
			}
        return description;
    }
    
    /**
	Returns the room object that a player would be in if they were to exit in the direction passed. If there is no room in the direction passed, returns null.
    */
    public Room leaveBy(String dir) {
        for (Exit exit : exits) {
            if (exit.getDir().equals(dir)) {
                return exit.getDest();
            }
        }
        return null;
    }

    /**
	Adds the passed Exit into this room's collection of exits. If null is passed there is no exit from the room from that direction.
    */
    void addExit(Exit exit) {
        exits.add(exit);
    }

    /**
	Adds the passed Item to this room's collection of items. If null is passed, it behaves the same way.
    */
    void add(Item item) {
        contents.add(item);
    }

    /**
	Adds the passed Npc to this room's collection of Npcs.
    */
    void add(Npc npc){
	characters.add(npc);
    }

    /**
	Removes the passed Npc from this room's collection of Npcs.
    */
    void remove(Npc npc){
	characters.remove(npc);
    }
    /**
	Removes the passed Item from this room's collection of items. If null is passed, behaves the same way.
    */
    void remove(Item item) {
        contents.remove(item);
		for(int j=0;j<litItems.size();j++){
			if(item.getPrimaryName().equals(litItems.get(j).getPrimaryName()))
				litItems.remove(j);
    }

 //   /**Lights up this room if it was dark, and returns a String containing what is now visible in the lit room. If the
//	room was already lit, does nothing.*/
//    String illuminate(){return "hello";}

 //   /**Darkens this room if it was lit, and retuns a String saying that the room is now dark. If this room was already dark
//	does nothing.*/
//    String darken(){return "hello";}

    /** Returns an ArrayList of Npcs that are in this room.*/
    ArrayList<Npc> getCharacters(){return null;}
    
    Npc getNpcNamed(String name){
	for(int i = 0; i < characters.size(); i++){
		//System.out.println(characters.get(0).getName());
		if(characters.get(i).getName().toLowerCase().equals(name.toLowerCase())){
			return characters.get(i);
		}
	}
	return null;
    }

   // /**Returns true if this room is lit, and the Player can see what is in this room, returns false if the room is dark
//	and the player can not see what is in this room.*/
//    boolean getIsLit(){return true;}
    /**
	Returns the Item from this room's collection of items whose name matches the given string. If no item matches, throws NoItemException.
    */
    Item getItemNamed(String name) throws Item.NoItemException {
        for (Item item : contents) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }

    /**
	Reuturns an ArrayList of items that contains all of the items contained in this room. If no items are contained in this room, then an empty ArrayList is returned.
    */
    ArrayList<Item> getContents() {
        return contents;
    }

	/**Returns the Exit in the direction passed.*/
	Exit getExit(String dir){
		for(int j = 0; j<exits.size();j++){
			if(dir.equals(exits.get(j).getDir()))
				return exits.get(j);
		}
			return null;
	}

	/**Adds the Item passed to this room's collection of lit Items.*/
	void addLitItem(Item it){
		litItems.add(it);
	}

	/**Removes the Item passed from this room's collection of lit Items*/
	void removeLitItem(Item it){
		litItems.remove(it);
	}
}
