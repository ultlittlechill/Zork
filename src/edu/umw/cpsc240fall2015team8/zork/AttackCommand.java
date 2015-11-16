package edu.umw.cpsc240fall2015team8.zork;

import java.util.ArrayList;

/**
Resolves conflict between the player and an NPC. Damage is dealt to the loser.
@author Austin
*/
class AttackCommand extends Command {

	/**
		Creates a new AttackCommand.
	*/
	AttackCommand(Npc n, Item i) {
	}

	/**
		Resolves the conflict and deals damage to the loser, returns a string containing the health of the Npc.
		If the Npc is not in the room, returns a string saying so, deals no damage.
		If the item is not in the player's inventory, returns a string saying so, deals no damage.
		The Item is the weapon used by the player and if the NPC has an item in their inventory, it will be the one they use.
	*/

	public String execute(){
		return "Hello";
	}
}
