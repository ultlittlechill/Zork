
package edu.umw.cpsc240fall2015team8.zork;

/**Gives a response from a specific Npc in the current Room.
@author Jeff Wallhermfechtel*/
class TalkCommand{

	/**Creates a new TalkCommand which allows the player to talk to the Npc represented by the String passed.*/
	TalkCommand(String npcName){}

	/**Returns a String containing what an Npc will say when they are talked to.
	If no Npc exists with the name passed in the constructor, returns a String which tells the player that the npc does not exist.*/
	public String execute(){return "hello";}
}
