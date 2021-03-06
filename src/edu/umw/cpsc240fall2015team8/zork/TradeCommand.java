
package edu.umw.cpsc240fall2015team8.zork;

/**
Moves an item from the player's inventory and places it in an Npc's hand, and
moves the item that was in the Npc's hand to the Players inventory.
@author Jeff Wallhermfechtel
*/

class TradeCommand extends Command{

	private String offer;
	private String merchant;

	/**Creates a new TradeCommand that is specific to the two items passed in the argument.*/
	TradeCommand(String o, String m){
		this.offer = o;
		this.merchant = m;
	}

	/**Moves the offer item from the player's inventory to the Npc's, and moves the product
	item from the Npc's inventory to the player's. @return String claiming that the trade was made.
	If the offer Item name is null or an empty string, the returned String asks for clarification.
	If the product String is null or an empty string, the returned String asks for clarification.
	If the Player does not have the offer Item, the returned String states such.
	If the player can not carry the item because of weight restrictions, returns a String informing
	the player that the Item weighs too much. It cancels the trade and outputs a string asking them if they would like to drop an item.*/
	public String execute(){
		Npc Jorge = GameState.instance().getAdventurersCurrentRoom().getNpcNamed(merchant);
		try{
			DurableItem i = (DurableItem)GameState.instance().getItemInVicinityNamed(offer);
			if(Jorge != null){
				return Jorge.trade(i) + "\n";
			}else{
				return merchant + " is not in this room.\n";
			}
		}catch(Item.NoItemException e){return "You don't have a " + offer + ".\n";}
		//Jorge.trade(


	//	GameState state = GameState.instance();
	//	try{/*something with offer*/}
	//	catch(Item.NoItemException e){}
	//	try{/*Scomething with product*/}
	//	catch(Item.NoItemException e){}
	//	//^^Checks to make sure Items are actually Items.
	//	return "I don't know -Jeff";
	}
}
