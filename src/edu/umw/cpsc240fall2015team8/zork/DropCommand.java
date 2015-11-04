
package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;

class DropCommand extends Command {

    private String itemName;

    DropCommand(String itemName) {
        this.itemName = itemName;
    }

    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Drop what?\n";
        }
        if (itemName.equals("all")) {
            ArrayList<String> inventory = (ArrayList<String>) 
                GameState.instance().getInventoryNames();
            if (inventory.size() == 0) {
                return "You're not carrying anything.\n";
            }
            String retval = "";
            for (String name : inventory) {
                retval += dropItemNamed(name);
            }
            return retval;
        } else {
            return dropItemNamed(itemName);
        }
    }

    private String dropItemNamed(String name) {
        try {
            Item theItem = GameState.instance().getItemFromInventoryNamed(
                name);
            GameState.instance().removeFromInventory(theItem);
            GameState.instance().getAdventurersCurrentRoom().add(theItem);
            return TakeCommand.capitalize(name) + " dropped.\n";
        } catch (Item.NoItemException e) {
            return "You don't have a " + name + ".\n";
        }
    }
}
