package edu.umw.cpsc240fall2015team8.zork;

/**
Resolves conflict between the player and an Npc with the player using a light attack.
@author Austin
*/
class LightAttackCommand extends AttackCommand {

	/**
		Creates a new LightAttackCommand.
	*/
	LightAttackCommand(Npc n, Item i){
		super(n,i);
	}

	/**
		Resolves conlflict between the player and the Npc, deals damage to the loser, and returns a string containing the Npc's health.
		If Npc doesn't exist, returns a string saying so, delaing no damage.
		If the item doesn't exist, returns a string saying so, dealing no damage.
	*/
	public String execute(){
		return "";
	}
}
