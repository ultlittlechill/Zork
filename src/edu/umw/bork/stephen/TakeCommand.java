
package edu.umw.bork.stephen;

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
            Item theItem = Item.getItemNamed(itemName);
            GameState.instance().addToInventory(theItem);
            GameState.instance().getAdventurersCurrentRoom().
                remove(theItem);
            return itemName + " taken.\n";
        } catch (Item.NoItemException e) {
            return "There's no " + itemName + " here.\n";
        }
    }
}
