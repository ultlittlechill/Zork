
package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;
import java.util.Scanner;

/**A non player character, Npcs can be hostile and attack when a player walks in a room, or they can be friendly and
pass along helpful messages or trade an Item with you. If an Npc is attacked it will fight back. 
@author Jeff Wallhermfechtel*/
public class Npc{

	private boolean hostile;
	private int health;
	private int maxHealth;
	private Item heldItem;
	private String scriptBt;
	private String scriptAt;
	private String scriptHostile;
	private String name;
	private String itemWanted;
	private Room location;
	private String favAttack;
	private boolean isDead;
	private boolean wantsTrade;
	private ArrayList<DurableItem> inventory;

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
		this.scriptHostile = s.nextLine();
		this.itemWanted = s.nextLine();
		this.hostile = s.nextLine();
		this.location = s.nextLine();
		s.nextLine();//throw away "Inventory:"
		String temp = s.nextLine();
		while(!temp.equals("===")){
			this.inventory.add(temp);
			temp = s.nextLine();
		}
		this.init();
	}

	/**Initializes basic variables to be used.*/
	private void init(){
		isDead = false;
		maxHealth = health;
	}

	/**Randomly selects an attack command to be used in combat against a player, this Npc's favorite Attack is 50% more likely to be returned.*/
	String  attack(){
		wantsTrade = false;
		hostile = true;
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
		this.dropInventory();
		health = 0;
		location.remove(this);
	}


	/**Drops all the Items in the Npc's inventory, and returns a String containing what Items were dropped.*/
	private void dropInventory(){
		for(int j = 0; j<inventory.size(); j++){
			location.add(inventory.get(0));
		}
		inventory.clear();
		location.add(heldItem);
		heldItem = null;
	}
	
	/**Returns a String containing a description of this Npc to describe their health state.*/
	String getFuzzyHealth(){
		if(health == maxHealth)
			return this.name + " doesn't even have a scratch on it";
		else if(health> (maxHealth*.75))
			return  this.name + " stands tall and Strong";
		else if(health> (maxHealth*.5))
			return  this.name + " is starting to show some weakness";
		else if(health> (maxHealth*.35))
			return  this.name + " is looking a little wobbly";
		else if (health> (maxHealth*.25))
			return this.name + "is looking a little slower, but still putting up a fight";
		else if (health> (maxHealth*.10))
			return "A pool of blood is starting to form around " + this.name;
		else if(health>0)
			return this.name + " is down on a knee";
		else
			return this.name + " appears to be dead";
	}

	/**Deals the damage passed in the argument to this Npc. If this Npc's health falls below 1, calls {@link die()}.*/
	void wound(int dam){
		health-=dam;
		if(health<1)
			this.die();
	}

	///**Retuns this Npc's script, retuns the first script if the npc has already traded an item
	//should the Npc trade items, and only returns the second script if the npc has already traded.*/

	/**Returns one of this Npc's scripts, if this Npc wants to make a trade the sriptBt is returned, if
	this Npc doesn't want to make a trade or has already made a trade the scriptAt si returned.*/
	String getScript(){
		if(wantsTrade && (hostile == false))
			return scriptBt;
		else if(hostile){
			return scriptHostile;
		}
		else{
			return scriptAt;
		}
	}

	/**Returns how much health the Npc has left.*/
	int getHealth(){return health;}

	/**Returns the item the Npc is holding.*/
	Item getHeldItem(){return heldItem;}

	/**Retuns the name of the Npc.*/
	String getName(){return name;}

	/**Returns the Item the Npc wants as an outcome of a trade, returns null if the npc does not want to trade.*/
	String getItemWanted(){return itemWanted;}

	/**Returns the Room the Npc is located in.*/
	Room getLocation(){return location;}

	/**Returns the boolean for if this Npc is dead or not.*/
	Boolean getIsDead(){return isDead;}

	/**Returns the boolean for if this Npc wants to trade or not.*/
	Boolean getWantsTrade(){return wantsTrade;}
}
