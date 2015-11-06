
package edu.umw.cpsc240fall2015team8.zork;

import java.util.Scanner;
/**Conects two rooms, and allows passage between them. keeps track of this exit's source room,
 destination room, wether the exit is locked or not the direction from the source room that the exits exist*/
public class Exit {


    class NoExitException extends Exception {}

   // private boolean isLocked;
   // private ArrayList keysNeeded<Item>;
    private String dir;
    private Room src, dest;
    
   /**Creates a Exit object given a String for the direction, Room for the source,
 Room for the Destination, and a boolean of wether the Room is locked or not. */
    Exit(String dir, Room src, Room dest, boolean locked){
    }

	/**Creates a Exit object given a String for the dierection, Room for the source,
and  a room for the destination. If no boolean is given it defaults to false, so that the exit is unlicked.*/
    Exit(String dir, Room src, Room dest) {
        init();
        this.dir = dir;
        this.src = src;
        this.dest = dest;
        src.addExit(this);
    }

    /** Given a Scanner object positioned at the beginning of an "exit" file
        entry, read and return an Exit object representing it. 
        @param d The dungeon that contains this exit (so that Room objects 
        may be obtained.)
        @throws NoExitException The reader object is not positioned at the
        start of an exit entry. A side effect of this is the reader's cursor
        is now positioned one line past where it was.
        @throws IllegalDungeonFormatException A structural problem with the
        dungeon file itself, detected when trying to read this room.
     */
    Exit(Scanner s, Dungeon d) throws NoExitException,
        Dungeon.IllegalDungeonFormatException {

        init();
        String srcTitle = s.nextLine();
        if (srcTitle.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoExitException();
        }
        src = d.getRoom(srcTitle);
        dir = s.nextLine();
        dest = d.getRoom(s.nextLine());
        
        // I'm an Exit object. Great. Add me as an exit to my source Room too,
        // though.
        src.addExit(this);

        // throw away delimiter
        if (!s.nextLine().equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after exit.");
        }
    }

    /** Common object initialization tasks.*/
    private void init() {
    }

/** returns a String containig a sentence stating what direction this exit is, and what room it leads to*/
    String describe() {
        return "You can go " + dir + " to " + dest.getTitle() + ".";
    }

/**returns the direction from the source room that this Exit is located*/
    String getDir() { return dir; }

/**returns the room that you can access this Exit from*/
    Room getSrc() { return src; }

/**returns the destination that this Exit leads to*/
    Room getDest() { return dest; }
}
