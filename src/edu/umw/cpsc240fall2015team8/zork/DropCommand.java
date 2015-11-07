
package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;

/**Leaves an item from the player's inventory in the current Room
@Jeff Wallhermfechtel*/
class DropCommand extends Command {

    private String itemName;

/**Creates a new DropCommand that is unique to the Item passed in the argument(the item to be dropped).
If the String passed is null or empty, then it still works the same way*/
    DropCommand(String itemName) {
        this.itemName = itemName;
    }

/**
Drops the Item from the player's inventory with the name given in this DropCommand's constructor.
If the name is null or contains an empty String the returned String asks for clarification
If the name is "all" returns a String containing all the items the player was carrying and
drops them in the current room. If the Player is not carrying anything when the item name 
is "all" the returned String expresses that the player is not carrying anything. 
*/
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

/**
Removes the item in the player's inventory with the name passed in the argument,
 and places the item in the current room.
If the Item naemd in the argument is not in the player's inventory returns a
String expressing that they do not have that item.
*/
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
