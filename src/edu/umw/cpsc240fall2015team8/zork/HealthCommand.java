package edu.umw.cpsc240fall2015team8.zork;


/**
Allows the player to check their fuzzy health.
@author Lucas
*/
class HealthCommand extends Command {

    /**Creates a new HealthCommand.*/
    HealthCommand() {}

    /** Returns a string that describes the player's health level.*/
    String execute(){
	int health = GameState.instance().getHealth();
	if(health>=70)
		return "You stand tall and strong";
	else if(health>=50)
		return "You feel weak, but are still putting up a fight";
	else if(health>=25)
		return "You are exhausted and bleeding";
	else if(health>=10)
		return "You are leaving a blood trail everywhere you walk.";
	else if(health>=0)
		return "You have lost a lot of blood. You may be missing a limb or two, but you're too disoriented to know for sure. You're about to die.";
	else
		return "You are dead. You shouldn't be able to read this."; 
    }

}
