

package edu.umw.cpsc240fall2015team8.zork;

import java.util.Scanner;

/**Acts like an Item, but a durable Item has a limited number of uses before it is 
broken or destroyed. Durable Items that have been broken can be repaired.Throws a no
 item exception if the Scanner does not represent a durable item. Returns an illegalDungeonFormat
 exception if the Scanner is not properly formatted.*/
public class DurableItem {

	private int health;//how  much recoil the item can take before it breaks
	private int durability;//The maximum ammount of health this item can have
	private int damage;//the ammount of damage this item will inflict on other players
	private int origDamage;
	private Hashtable<String,int>  recoil;//the ammount of damage this item takes when it is used. String = the command used; int = the recoil to be taken.
	private boolean broken;//keeps track of if this item is broken or not

	/**Creates a Durable Item from the Scanner passed in the arguments.*/
	DurableItem(Scanner s){
		super(s);
		this.init();
		//...
	}

	/**Some basic initialization tasks*/
	private void init(){
		recoil = new Hashtable<String,int>();
	}

	/** Deals damage to this item's health based on the int pased in the argument. If this item's health is below 0 calls {@link rupture()} or {@link destroy()}
	on this Item, whichever is aplicable.*/
	void takeDamage(int r){
	health-=r;

	if(health<1)
		this.rupture();
	}
	
	/**Deals damage to his item's health based on the recoil of the verb passed in the argument. If this item's health is below 0 calls {@link rupture()}*/
	void takeDamage(String verb){
		this.takeDamage(this.getRecoil(verb));
	}


	/**Returns the int value of the recoil of the verb passed in the argument.*/
	int getRecoil(String v){
		return recoil.get(v);
	}

	/**Returns the Hashtable of verbs and thier recoils for this item*/
	Hashtable getRecoil(){
		return recoil;
	}	

	/**Breaks this item, and transforms it into a broken version of this item. This broken Item cannont be used
	again until {@link repair()} has been called on it.*/
	private void rupture(){
		broken = true;
		damage = damage/4;
		this.primaryName= "X~" + this.primaryName;
	}

//	/**Destroys this Item if this item is destroyable, this item cannont be repaired and is removed from existence.*/
//	void destroy(){}
// this feature has been removed

	/**Repairs this item to its original state. This Item's health is reset to its origonal durability, and
	if the item was broken it is transformed back into the original not-broken item. repair() cannont be called
	on Items that have been destroyed.*/
	void repair(){
	health = durability;
	damage = origDamage;
	broken = false;
	this.primaryName= this.primaryName.substring(2,this.primaryName.length());
	//Transform event?
	}

	/**Returns how much health this Item has left, as in how many more times this item can be used before it breaks*/
	int getHealth(){return health;}
}

