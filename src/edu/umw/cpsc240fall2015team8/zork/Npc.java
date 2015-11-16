
package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;
import java.util.Scanner;

/**A non player character, Npcs can be hostile and attack when a player walks in a room, or they can be friendly and
pass along helpful messages or trade an Item with you. If an Npc is attacked it will fight back. 
@author Jeff Wallhermfechtel*/
public class Npc{

	private boolean hostile;
	private int health;
	private Item heldItem;
	private String scriptBt;
	private String scriptAt
	private String name;
	private String itemWanted;
	private Room location;
	private boolean isDead;
	private ArrayList<Item> inventory;

	/**Creates a Npc object when passed a Scanner.*/
	Npc(Scanner s){}

	/**Randomly selects an attack command to be used in combat against a player,
	also determines how much damage this Npc takes in combat and applies it to its health.*/
	void attack(){}

	/**Trades the Item offered in the argument with the Npc's heldItem if the offered item and the
	Npc's itemWanted have the same name. If the Items do not have the same name the trade is refused.*/ 
	void trade(Item offer){}

	/**Kills the Npc, thier items are dropped in the current room.*/
	void die(){}

	/**Drops all the Items in the Npc's inventory, and returns a String containing what Items were dropped.*/
	String dropInventory(){return "";}
	
	/**Returns a String containing a description of this Npc to describe their health state.*/
	String getFuzzyHealth(){return "";}

	/**Retuns this Npc's script, retuns the first script if the npc has already traded an item
	should the Npc trade items, and only returns the second script if the npc has already traded.*/

	/**Returns one of this Npc's scripts, if this Npc wants to make a trade the sriptBt is returned, if
	this Npc doesn't want to make a trade or has already made a trade the scriptAt si returned.*/
	String getScript(){return "";}

	/**Returns how much health the Npc has left.*/
	int getHealth(){return 0;}

	/**Returns the item the Npc is holding.*/
	Item getHeldItem(){return null;}

	/**Retuns the name of the Npc.*/
	String getName(){return "";}

	/**Returns the Item the Npc wants as an outcome of a trade, returns null if the npc does not want to trade.*/
	Item getItemWanted(){return null;}

	/**Returns the Room the Npc is located in.*/
	Room getLocation(){return null;}
}
