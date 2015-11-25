package edu.umw.cpsc240fall2015team8.zork;

/**
Resolves conflict between the player and an Npc with the player using a light attack.
Light attacks beat heavy attacks, and lose to block attacks.
@author Austin
*/
class LightAttackCommand extends AttackCommand {

	Npc person;
	DurableItem item;	

	/**
		Creates a new LightAttackCommand.
	*/
	LightAttackCommand(Npc n, DurableItem i){
		//super(n,i);
		person = n;
		item = i;
	}

	/**
		Resolves conlflict between the player and the Npc, deals damage to the loser, and returns a string containing the Npc's health.
		If Npc doesn't exist, returns a string saying so, delaing no damage.
		If the item doesn't exist, returns a string saying so, dealing no damage.
	*/
	public String execute(){
		string attack = person.attack();
		
		if(attack.equals("light")){
			return "Your attacks were evenly matched!\n";
		}
		else if(attack.equals("heavy")){
			person.wound(item.getDamage());
			item.takeDamage(1);
			return "Your attack was quicker than  " + person.getName() + "'s.\n";
		}else{
			GameState.instance().changeHealth(person.getHeldItem().getDamage());
			item.takeDamage(1);
			return "Your attack was blocked and countered by " + person.getName() + ".\n";
		}
	}
}
