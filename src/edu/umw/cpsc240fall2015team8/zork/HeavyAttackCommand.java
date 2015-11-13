package edu.umw.cpsc240fall2015team8.zork;

/**
Resolves confilct between the player and an Npc with the player using a heavy attack.
@author Austin
*/
class HeavyAttackCommand extends AttackCommand {
	
	/**
		Creates a new HeavyAttackCommand.
	*/
	HeavyAttackCommand(Npc n, Item i){
		super(n,i);
	}

	/**
		Reseoves the conflict between the player and the Npc, deals damage to the loser, and returns a string containing the Npc's health.
		If Npc doesn't exist, returns a string saying so, dealing no damage.
		If the item doesn't exist, returns a string saying so, dealing no damage.
	*/
	public String execute(){
		return "hello";
	}
}
