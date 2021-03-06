
package edu.umw.cpsc240fall2015team8.zork;

/**
Removes the specific item from the game in its entirety. 
@author Austin
*/
class DisappearEvent extends Event{

	Item item;

	/**
		Creates a new DisappearEvent.
		Works the same way for all values of i.
	*/
	DisappearEvent(Item i){
		item = i;
	}

	/**
		Removes the stored item from the game.
		If the held item is null, does nothing.
	*/
	public void execute(){
		Dungeon d = GameState.instance().getDungeon();
		d.remove(item);
		GameState.instance().removeFromInventory(item);
		GameState.instance().getAdventurersCurrentRoom().remove(item);
	}
}
