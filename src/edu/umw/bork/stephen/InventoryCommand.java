
package edu.umw.bork.stephen;

import java.util.ArrayList;

class InventoryCommand extends Command {

    InventoryCommand() {
    }

    public String execute() {
        ArrayList<Item> inv = GameState.instance().getInventory();
        if (inv.size() == 0) {
            return "You are empty-handed.\n";
        }
        String retval = "You are carrying:\n";
        for (Item item : inv) {
            retval += "   " + item + "\n";
        }
        return retval;
    }
}
