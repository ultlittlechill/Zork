
package edu.umw.cpsc240fall2015team8.zork;

/**
Resolves conflict between the player and an Npc with the player using a block attack.
Block attacks beat light attacks, and lose to heavy attacks.
@author Austin
*/
class BlockAttackCommand extends AttackCommand {

	Npc person;
	DurableItem item;
	
	/**
		Creates a new BlockAttackCommand.
	*/
	BlockAttackCommand(Npc n, DurableItem i){
		person = n;
		item = i;
		//super(n,i);
	}

	/**
		Resolves the conflict between the player and the Npc, deals damage to the loser, and returns a string containing the Npc's health.
		If Npc doesn't exist, returns a string saying so, dealing no damage.
		If the item doesn't exist, returns a string saying so, dealing no damage.
	*/
	public String execute(){
		string attack = person.attack();

		if(attack.equals("block")){
			return "You both took a defensive stance!\n";
		}
		else if(attack.equals("light")){
			person.wound(item.getDamage());
			item.takeDamage(1);
			return "You blocked and countered " + person.getName() + "'s attack!\n";
		}else{
			GameState.instance().changeHealth(person.getHeldItem().getDamage());
			item.takeDamage(1);
			return person.getName() + "'s attack overpowered you!\n";
		}
		//return "Hello";
	}
}
