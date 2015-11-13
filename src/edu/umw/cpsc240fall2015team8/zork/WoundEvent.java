
package edu.umw.cpsc240fall2015team8.zork;

/**
Deals damage to the player. 
If a negative number is added, then the player is healed by that many points.
@author Austin
*/
class WoundEvent extends Event{

	int damage;

	/**
		Creates a new WoundEvent. 
		Works the same way for all values of i.
	*/
	WoundEvent(int i){
		damage = i;
	}

	/**
		Deals damage to the player equal to the stored number.
		If the integer is negative, then heals the player.
		If null, the health remains unchanged. 
	*/
	public void execute(){
		GameState.instance().setHealth(GameState.instance().getHealth() - damage);
	}
}
