
package edu.umw.bork.stephen;


public class GameState {

    private Dungeon dungeon;
    private Room adventurersCurrentRoom;

    GameState(Dungeon dungeon) {
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
