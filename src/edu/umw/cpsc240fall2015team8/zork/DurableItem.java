

package edu.umw.cpsc240fall2015team8.zork;

import java.util.Scanner;

/**Acts like an Item, but a durable Item has a limited number of uses before it is 
broken or destroyed. Durrable Items that have been broken can be repaired.Throws a no item exception if the Scanner does not represent a durable item. Returns an illegalDungeonFormat exception if the Scanner is not properly formatted.*/
public class DurableItem {

	private int health;
	private int durablility;
	private int damage;
	private boolean destroyable;
	private boolean broken;

	/**Creates a Durable Item from the Scanner passed in the arguments.*/
	DurableItem(Scanner s){}

	/** Deals one damage to this item's health. If this item's health is below 0 calls {@link rupture()} or {@link destroy()}
	on this Item, whichever is aplicable.*/
	void takeDamage(){}

	/**Breaks this item, and transforms it into a broken version of this item. This broken Item cannont be used
	again until {@link repair()} has been called on it.*/
	void rupture(){}

	/**Destroys this Item if this item is destroyable, this item cannont be repaired and is removed from existence.*/
	void destroy(){}

	/**Repairs this item to its original state. This Item's health is reset to its origonal durability, and
	if the item was broken it is transformed back into the original not-broken item. repair() cannont be called
	on Items that have been destroyed.*/
	void repair(){}

	/**Returns how much health this Item has left, as in how many more times this item can be used before it breaks*/
	int getHealth(){return 0;}
}

