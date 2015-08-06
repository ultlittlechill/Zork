
package edu.umw.bork.stephen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

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

    void restore(String filename) throws IOException, 
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        BufferedReader r = new BufferedReader(new FileReader(filename));

        r.readLine();   // Throw away version indicator.
        String dungeonFileLine = r.readLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()));
        dungeon.restoreState(r);

        Adventurer a = Adventurer.instance();
        a.initialize(r, dungeon);
    }

    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println("Bork v2.0 save data");
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
