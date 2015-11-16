
package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;

/**
Replaces one item with another item. 
@author Austin
*/
class TransformEvent extends Event{

	Item item;
	String sitem;

	/**
		Creates a new TransformEvent.
		Works the same regardless of String values.
	*/
	TransformEvent(Item i, String s){
		item = i;
		sitem = s;
	}

	/**
		Replaces stored item 'item' with stored item represented by 'sitem' and removes 'item' from the Dungeon.
		If either of the two stored items are null, does nothing.
	*/
	public void execute(){
		GameState g = GameState.instance();
		Dungeon d = g.getDungeon();
		ArrayList<String> al = g.getInventoryNames();
		if(al.contains(item.getPrimaryName())){	
			try{
				g.addToInventory(d.getItem(sitem));
				g.removeFromInventory(item);
				d.remove(item);
			}catch(Item.NoItemException e){}
		}else{
			try{
				g.getAdventurersCurrentRoom().add(d.getItem(sitem));
				g.getAdventurersCurrentRoom().remove(item);
				d.remove(item);
			}catch(Item.NoItemException e){}
		}
	}
}
