
package edu.umw.bork.stephen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GameState {

    static String DEFAULT_SAVE_FILE = "/tmp/bork_save";
    static String SAVE_FILE_EXTENSION = ".sav";
    static String DUNGEON_FILENAME_LEADER = "Dungeon file: ";
    static String CURRENT_ROOM_LEADER = "Current room: ";

    private static GameState theInstance;
    private Dungeon dungeon;
    private Room adventurersCurrentRoom;

    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
    }

    void restore(String filename) {

    }

    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println("Bork v2.0 save data");
        w.println(DUNGEON_FILENAME_LEADER + dungeon.getFilename());
        w.println(CURRENT_ROOM_LEADER + 
            getAdventurersCurrentRoom().getTitle());
        w.close();
    }

    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }

    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    Dungeon getDungeon() {
        return dungeon;
    }
}
