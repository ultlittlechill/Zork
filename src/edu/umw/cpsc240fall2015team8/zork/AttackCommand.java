package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;

/**
Resolves conflict between the player and an NPC. Damage is dealt to the loser. For actual use, please use BlockAttackCommand, LightAttackCommand, or HeavyAttackCommand.
@author Austin
*/
class AttackCommand extends Command {

	/**
		Creates a new AttackCommand when passed an npc to target, and an item to attack with.
	*/
	AttackCommand(Npc n, DurableItem i) {
	}

	/**
		Resolves the conflict and deals damage to the loser, returns a string containing the health of the Npc.
		If the Npc is not in the room, returns a string saying so, deals no damage.
		If the item is not in the player's inventory, returns a string saying so, and deals no damage.
		The Item is the weapon used by the player and if the NPC has an item in their inventory, it will be the one they use.
	*/

	public String execute(){
		return "Please choose lightAttack, heavyAttack, or blockAttack.";
	}
}
