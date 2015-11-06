
package edu.umw.cpsc240fall2015team8.zork

/**A non player character, Npcs can be hostile and attack when a player walks in a room, or they can be friendly and
pass along helpful messages or trade an Item with you. If an Npc is attacked it will fight back. */
public class Npc{

	private boolean hostile;
	private int health;
	private Item heldItem;
	private String script;
	private String script2;
	private String name;
	private String itemWanted;
	private Room location;

	/**Creates a Npc object when passed a Scanner.*/
	Npc(Scanner s){}

	/**Randomly selects an attack command to be used in combat against a player,
	also determines how much damage this Npc takes in combat and applies it to its health.*/
	void attack(){}

	/**Trades the Item offered in the argument with the Npc's heldItem if the offered item and the
	Npc's itemWanted match. If the Items do not match the trade is refused.*/ 
	void trade(Item offer)[]
