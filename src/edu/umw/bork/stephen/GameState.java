
package edu.umw.bork.stephen;


public class GameState {

    private static GameState theInstance;
    private Dungeon dungeon;
    private Room adventurersCurrentRoom;

    public static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
    }

    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }

    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    Dungeon getDungeon() {
        return dungeon;
    }
}
