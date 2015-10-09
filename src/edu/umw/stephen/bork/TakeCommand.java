
package edu.umw.stephen.bork;

class TakeCommand extends Command {

    private String itemName;

    TakeCommand(String itemName) {
        this.itemName = itemName;
    }

    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
        try {
            Room currentRoom = 
                GameState.instance().getAdventurersCurrentRoom();
            Item theItem = currentRoom.getItemNamed(itemName);

            if (GameState.instance().weightCarried() + theItem.getWeight()
                > GameState.MAX_CARRY_WEIGHT) {
                return "Your load is too heavy.\n";
            }

            GameState.instance().addToInventory(theItem);
            currentRoom.remove(theItem);
            return capitalize(itemName) + " taken.\n";

        } catch (Item.NoItemException e) {
            return "There's no " + itemName + " here.\n";
        }
    }

    static String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
