
package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;
/** Holds the method used to open the players inventory and allow them to look at it's contents */
/**  @author Lucas */
class InventoryCommand extends Command {

    InventoryCommand() {
    }
/** opens the players inventory, if it is empty then it returns a message that mentions this, otherwise it returns the names of the items in their inventory */
    public String execute() {
        ArrayList<String> names = GameState.instance().getInventoryNames();
        if (names.size() == 0) {
            return "You are empty-handed.\n";
        }
        String retval = "You are carrying:\n";
        for (String itemName : names) {
            retval += "   A " + itemName + "\n";
        }
        return retval;
    }
}
