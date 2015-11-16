
package edu.umw.cpsc240fall2015team8.zork;

/**
Resolves conflict between the player and an Npc with the player using a block attack.
Block attacks beat light attacks, and lose to heavy attacks.
@author Austin
*/
class BlockAttackCommand extends AttackCommand {
	
	/**
		Creates a new BlockAttackCommand.
	*/
	BlockAttackCommand(Npc n, Item i){
		super(n,i);
	}

	/**
		Resolves the conflict between the player and the Npc, deals damage to the loser, and returns a string containing the Npc's health.
		If Npc doesn't exist, returns a string saying so, dealing no damage.
		If the item doesn't exist, returns a string saying so, dealing no damage.
	*/
	public String execute(){
		return "Hello";
	}
}
