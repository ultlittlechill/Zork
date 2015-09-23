
package edu.umw.bork.stephen;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GameState {

    public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static String DEFAULT_SAVE_FILE = "/tmp/bork_save";
    static String SAVE_FILE_EXTENSION = ".sav";

    private static GameState theInstance;
    private Dungeon dungeon;

    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
    }

    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        s.nextLine();   // Throw away version indicator.
        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()));
        dungeon.restoreState(s);

        Adventurer a = Adventurer.instance();
        a.restoreState(s, dungeon);
    }

    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println("Bork v3.0 save data");
        dungeon.storeState(w);
        Adventurer.instance().storeState(w);
        w.close();
    }

    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        Adventurer.instance().setRoom(dungeon.getEntry());
    }

    Dungeon getDungeon() {
        return dungeon;
    }
}
