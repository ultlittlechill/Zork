
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
	private String scriptAt;
	private String name;
	private String itemWanted;
	private Room location;
	private String favAttack;
	private boolean isDead;
	private boolean wantsTrade;
	private ArrayList<Item> inventory;

	/**Creates a Npc object when passed a Scanner.*/
	Npc(Scanner s){
		this.name = s.nextLine();
		this.health = s.nextLine();
		this.heldItem = GameState.instance().getDungeon().getItem(s.nextLine());
		this.scriptBt = s.nextLine();

		if(scriptBt.equals(""))
			wantsTrade = false;
		else
			wantsTrade = true;

		this.scriptAt = s.nextLine();
		this.itemWanted = s.nextLine();
		this.hostile = s.nextLine();
		this.location = s.nextLine();
		s.nextLine();//throw away "Inventory:"
		String temp = s.nextLine();
		while(!temp.equals("===")){
			this.inventory.add(temp);
			temp = s.nextLine();
		}
	}

	/**Initializes basic variables to be used.*/
	private void init(){
		isDead = false;
	}

	/**Randomly selects an attack command to be used in combat against a player, this Npc's favorite Attack is 50% more likely to be returned.*/
	String  attack(){
		int rand = (int)(Math.random()*5)+1;//hopefuly makes a number between 1 and 5 inclusive
		if(rand == 1)
			return "block";
		else if(rand == 2)
			return "light";
		else if(rand == 3)
			return "heavy";
		else
			return this.favAttack;
	}

	/**Trades the Item offered in the argument with the Npc's heldItem if the offered item and the
	Npc's itemWanted have the same name. If the Items do not have the same name the trade is refused.*/ 
	String trade(Item offer){
		if(getItemWanted().equals(offer.getPrimaryName()) && wantsTrade){
			GameState.instance().addToInventory(heldItem);
			GameState.instance().removeFromInventory(offer);
			this.heldItem = offer;
			this.wantsTrade = false;
			return this.getScript();
		}

		else if(wantsTrade)
			return "The Npc refused your offer";
		
		else
			return "The Npc does not want to trade with you";
	}


	/**Kills the Npc, thier items are dropped in the current room.*/
	void die(){
		for(int j = 0; j<inventory.size(); j++){
			location.add(inventory.get(j))
		}
		inventory.clear();
		location.add(heldItem);
		health = 0;
		heldItem = null;
		location.remove(this);
	}


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
	String getItemWanted(){return null;}

	/**Returns the Room the Npc is located in.*/
	Room getLocation(){return null;}
}
