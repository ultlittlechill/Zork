package edu.umw.cpsc240fall2015team8.zork;

/**
Resolves conflict between the player and an Npc with the player using a heavy attack.
Heavy attacks beat block attacks, and lose to light attacks.
@author Austin
*/
class HeavyAttackCommand extends AttackCommand {

	Npc person;
	DurableItem item;
	
	/**
		Creates a new HeavyAttackCommand.
	*/
	HeavyAttackCommand(Npc n, DurableItem i){
		super(n,i);
		person = n;
		item = i;
	}

	/**
		Resolves the conflict between the player and the Npc, deals damage to the loser, and returns a string containing the Npc's health.
		If Npc doesn't exist, returns a string saying so, dealing no damage.
		If the item doesn't exist, returns a string saying so, dealing no damage.
	*/
	public String execute(){
		String attack = person.attack();

		if(attack.equals("heavy")){
			return "Your attacks were evenly matched!\n";
		}
		else if(attack.equals("block")){
			person.wound(item.getDamage());
			item.takeDamage(1);
			return "Your attack overpowered " + person.getName() + ".\n";
		}else{
			GameState.instance().changeHealth(person.getHeldItem().getDamage());
			item.takeDamage(1);
			return "Your attack was slower than " + person.getName() + "'s.\n";
		}
		//return "hello";
	}
}
