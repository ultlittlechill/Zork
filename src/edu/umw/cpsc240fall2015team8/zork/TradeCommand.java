
package edu.umw.cpsc240fall2015team8.zork;

/**
Moves an item from the player's inventory and places it in an Npc's hand, and
moves the item that was in the Npc's hand to the Players inventory.
@author Jeff Wallhermfechtel
*/

class TradeCommand extends Command{

	private String offer;
	private String product;

	/**Creates a new TradeCommand that is specific to the two items passed in the argument.*/
	TradeCommand(String o, String p){}

	/**Moves the offer item from the player's inventory to the Npc's, and moves the product
	item from the Npc's inventory to the player's. @return String claiming that the trade was made.
	If the offer Item name is null or an empty string, the returned String asks for clarification.
	If the product String is null or an empty string, the returned String asks for clarification.
	If the Player does not have the offer Item, the returned String states such.
	If the player can not carry the item because of weight restrictions, returns a String informing
	the player that the Item weighs too much. */
	public String execute(){return "hello";}
}
