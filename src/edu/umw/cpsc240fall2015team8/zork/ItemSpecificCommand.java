
package edu.umw.cpsc240fall2015team8.zork;


/** Allows the player to interact with an Item by giving a command specific to that Item. Items keep track of their own specific commands.

@author Lucas */
class ItemSpecificCommand extends Command {
    private String command;
                        
/** initializes a new command object */
    ItemSpecificCommand(String command) {
        this.command = command;
    }

/**Breaks down command given in this object's constructor into a verb and an Item, and attempts to match the verb with
one of the item's actions; returns the message that is associated with the Item's action as a String. If the verb does not match
 any of the item's actions a String is returned stating that action cannont be done to that item.*/
    public String execute() {
        
        String[] words = command.split(" ");

        // Consider every possible split point in the multi-word command. If
        // any split point corresponds to a verb/noun combo that makes sense,
        // assume that's what the player meant.
        for (int indexOfLastVerb=0; indexOfLastVerb<words.length-1;
            indexOfLastVerb++) {

            String verb = words[0];
            for (int i=1; i<=indexOfLastVerb; i++) {
                verb += " " + words[i];
            }
            String noun = words[indexOfLastVerb+1];
            for (int i=indexOfLastVerb+2; i<words.length; i++) {
                noun += " " + words[i];
            }

            Item itemReferredTo = null;
            try {
                itemReferredTo = 
                    GameState.instance().getItemInVicinityNamed(noun);
            } catch (Item.NoItemException e) {
                continue;  // Just try the next split point.
            }

            String msg = itemReferredTo.getMessageForVerb(verb);
            Event[] events = itemReferredTo.getEventsForVerb(verb);
	    if(events != null){
	    	for(int i = 0; i < events.length; i++){
			events[i].execute();
			//System.out.println("Done");
	    	}
	    }
	    return (msg == null ? 
                "Sorry, you can't " + verb + " the " + noun + "." : msg) +
                "\n";
        }

        return "Not sure what you mean?\n";
    }
}
