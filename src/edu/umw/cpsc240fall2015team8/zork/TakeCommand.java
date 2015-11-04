
package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;

/**
Moves an Item from the current Room and places it into the player's inventory.
*/
class TakeCommand extends Command {

    private String itemName;

    /**
	Creates a new TakeCommand that is bound to the item represented by the given string. If null or an empty string are given, then it still works the same way.
    */
    TakeCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
	Trys to move the Item from the current Room into the players inventory, returning a string which claims it was taken.
	If the item name is either null or an empty string, the returned string asks for clarification. 
	If the room contains nothing, the returned string relects that. 
	If the Room does contain items, but not the specific one, returns a string stating that the item in question is not here. 
	If the Room does contain the specific item, but the player can not carry the item due to weight restrictions, returns a string informing the player that the item weighs too much.
    */
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
        if (itemName.equals("all")) {
            ArrayList<Item> roomContents = new ArrayList<Item>(
                GameState.instance().getAdventurersCurrentRoom().
                getContents());
            if (roomContents.size() == 0) {
                return "There's nothing here to take.\n";
            }
            String retval = "";
            for (Item item : roomContents) {
                retval += tryToTakeItemNamed(item.getPrimaryName());
            }
            return retval;
        } else {
            return tryToTakeItemNamed(itemName);
        }
    }

    /**
	Attempts to move the item represented by the given string from the current Room into the player's inventory, returning a string which says it has been moved.
	If the Item is too heavy to pick up, returns a string which says the item is too heavy. 
	If the Item does not exist in the current Room, returns a string which says that the Item does not exist in the current Room.
    */
    private String tryToTakeItemNamed(String name) {
        try {
            Room currentRoom = 
                GameState.instance().getAdventurersCurrentRoom();
            Item theItem = currentRoom.getItemNamed(name);

            if (GameState.instance().weightCarried() + theItem.getWeight()
                > GameState.MAX_CARRY_WEIGHT) {
                return "Your load is too heavy.\n";
            }

            GameState.instance().addToInventory(theItem);
            currentRoom.remove(theItem);
            return capitalize(name) + " taken.\n";

        } catch (Item.NoItemException e) {
            return "There's no " + name + " here.\n";
        }
    }

    /**
	Returns a string which is the same as the given string, but with the first character uppercased. If either null, or a string with a length less then 2, an out of bounds error will occur, terminating any running code. 
    */
    static String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
