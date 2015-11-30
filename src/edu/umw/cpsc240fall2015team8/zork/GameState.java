
package edu.umw.cpsc240fall2015team8.zork;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
Manages the state of the player and the game, allowing for both saving and loading the sate.
@author Austin
*/
public class GameState {

    /** Gets thrown whenever a text file representing a saved GameState is not properly formatted. */
    public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static int MAX_CARRY_WEIGHT = 40;

    static String DEFAULT_SAVE_FILE = "zork_save";
    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Zork v1.0 save data";

    static String ADVENTURER_MARKER = "Adventurer:";
    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String INVENTORY_LEADER = "Inventory: ";

    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private ArrayList<Item> litItems;
    private Room adventurersCurrentRoom;
    private int health = 100;
    private int score = 0;
    private int scoreWin = 10;
	private boolean isLit = false;
    /**
	Returns the GameState. If no GameState exists, it creates one and the returns it.
    */
    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    /**
	Creates a new GameState object by initializing the player's inventory.
    */
    private GameState() {
        inventory = new ArrayList<Item>();
	litItems = new ArrayList<Item>();
    }

    /**
	Loads the GameState from a file represented by the given string. Throws FileNotFoundException if there is no file with the same name as the given string.
	Throws IllegalSaveFormatException if the file is not properly formatted.
	Throws IllegalDungeonFormatException if the dungeon file contained in the save file is not formatted correctly.
    */
    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        String version = s.nextLine();
	if(version.equals(SAVE_FILE_VERSION)){
	
	}else if(version.equals("Zork v2.0 save data")){

	}else{
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()), false);
        dungeon.restoreState(s);

        s.nextLine();  // Throw away "Adventurer:".
        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
	s.next(); //throw away "health:"
	setHealth(s.nextInt());
	s.nextLine(); //move to the next line
	s.next(); //throw away "score: "
	setScore(s.nextInt());
	s.nextLine(); //move to the next line.
        if (s.hasNext()) {
            String inventoryList = s.nextLine().substring(
                INVENTORY_LEADER.length());
            String[] inventoryItems = inventoryList.split(",");
            for (String itemName : inventoryItems) {
                try {
                    addToInventory(dungeon.getItem(itemName));
                } catch (Item.NoItemException e) {
                    throw new IllegalSaveFormatException("No such item '" +
                        itemName + "'");
                }
            }
        }
    }

    /**
	Calls the other store method with the default save file as an arguement. The default save file is "zork_save".
    */
    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    /**
	Saves the current state of the game to a file represented by the given string. 
	Throws an IOException if PrintWriter throws an IOException
    */
    void store(String saveName) throws IOException {
        int version = dungeon.getVersion();
	String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
	if(version == 1){
        	w.println(SAVE_FILE_VERSION);
	}else{
		w.println("Zork v2.0 save data");
	}
        dungeon.storeState(w);
        w.println(ADVENTURER_MARKER);
        w.println(CURRENT_ROOM_LEADER + adventurersCurrentRoom.getTitle());
	w.println("Health: " + getHealth());
	w.println("Score: " + getScore());
        if (inventory.size() > 0) {
            	w.print(INVENTORY_LEADER);
            	for (int i=0; i<inventory.size()-1; i++) {
                	w.print(inventory.get(i).getPrimaryName() + ",");
           	}
           	w.println(inventory.get(inventory.size()-1).getPrimaryName());
        }
	
        w.close();
    }

    /**
	Initializes the GameState to hold the passed dungeon and the current room to be the dungeon's entry. If null is passed, a null pointer exception will be thrown.
    */
    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }

    /**
	Returns an ArrayList of strings containing the names of all of the items in the player's inventory. If no items are in the player's inventory, returns an empty ArrayList.
    */
    ArrayList<String> getInventoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item item : inventory) {
            names.add(item.getPrimaryName());
        }
        return names;
    }

    /**
	Adds the passed item to the player's inventory. If null is passed, nothing changes.
    */
    void addToInventory(Item item) /* throws TooHeavyException */ {
        inventory.add(item);
    }

    /**
	Removes the given item from the player's inventory. If null is passed, nothing changes.
    */
    void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    /**
	Returns the Item from the current Room or inventory which has a name equal to the string passed.
	If no Item is found, or if null is passed, throws NoItemException.
    */
    Item getItemInVicinityNamed(String name) throws Item.NoItemException {

        // First, check inventory.
        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : adventurersCurrentRoom.getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new Item.NoItemException();
    }

    /**
	Returns the item in the player's inventory whose name matches the given string. 
	If the item is not in the player's inventory or null is passed, throws NoItemException.
    */
    Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }

    /**
	Returns the total weight carried in the player's inventory.
    */
    int weightCarried() {
        int weight = 0;
        for (Item item : inventory) {
            weight += item.getWeight();
        }
        return weight;
    }

    /**
	Returns the room that the player is curently in.
    */
    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    /**
	Sets the current Room to the given Room.
    */
    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    /**
	Returns the Dungeon held by the GameState.
    */
    Dungeon getDungeon() {
        return dungeon;
    }
    
    /**Returns the player's current health*/
    int getHealth(){
	return health;
    }
   
    /**Subtracts the player's health by the number passed in the argument. If the player's health is lower than 1, the player dies.
	 Passing a negative number willincrease the player's health.*/
    void changeHealth(int h){
	health-=h;
	//if(health<=0){
	//	EventFactory.instance().parse("Die",null).execute();
	//}
    }

/**Sets the value passed to the player's health. If the player's health falls below 1, the player will die*/
    void setHealth(int h){
	health = h;
	//if(health<=0){
	//	EventFactory.instance().parse("Die", null).execute();
	//}
    }

    /**Returns the player's current score*/
    int getScore(){
	return score;
    }

    /**Addes the value passed to the player's score. If the player's score is greater than or equal to the 
	number of points needed to win, the player wins the game.*/
    void changeScore(int s){
	score+=s;
	//if(score>=scoreWin){
	//	EventFactory.instance().parse("Win",null).execute();
	//}
    }

/**Sets the value passed to the player's score. If the player's score is at least the score needed to win, The player will win the game.*/
    void setScore(int s){
	score = s;
	//if(score>=scoreWin)
	//	EventFactory.instance().parse("Win",null).execute();
    }

/**Returns the score needed to win the game.*/
   int getScoreWin(){
	return scoreWin;
    }

/**Returns if the adventurer is lit or not*/
   boolean getIsLit(){return isLit;}

   public void setIsLit(boolean b){isLit = b;}

   public void addLitItem(Item item){litItems.add(item);}

   public void removeLitItem(Item item){litItems.remove(item); }
}
