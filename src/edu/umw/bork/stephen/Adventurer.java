
package edu.umw.bork.stephen;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class Adventurer {

    class UnknownItemException extends Exception {}

    static String CURRENT_ROOM_LEADER = "Current room: ";

    private int health;
    private ArrayList<Item> inventory;
    private Room currentRoom;

    private static Adventurer theInstance;

    public static synchronized Adventurer instance() {
        if (theInstance == null) {
            theInstance = new Adventurer();
        }
        return theInstance;
    }

    private Adventurer() {
        health = 100;
        inventory = new ArrayList<Item>();
    }

    void setRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    Room getRoom() {
        return currentRoom;
    }

    void initialize(Scanner s, Dungeon d) {
        String currentRoomLine = s.nextLine();
        setRoom(d.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length())));
    }

    void storeState(PrintWriter w) throws IOException {
        w.println(CURRENT_ROOM_LEADER + currentRoom.getTitle());
    }

    Item getItemNamed(String name) throws UnknownItemException {
        // First, check inventory.
        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : currentRoom.getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new UnknownItemException();
    }
}
