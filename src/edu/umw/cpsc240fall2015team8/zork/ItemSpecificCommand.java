
package edu.umw.cpsc240fall2015team8.zork;


/** This class will allow certain verb or commands related to specific items be used if they are included in the text file.

@author Lucas */
class ItemSpecificCommand extends Command {
    private String command;
                        
/** initializes a new command object */
    ItemSpecificCommand(String command) {
        this.command = command;
    }
/** Moves through the command the player input and checks to see if the verb noun combo makes sense, and then implements whatever command is input. 
*/
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
	    for(int i = 0; i < events.length; i++){
		events[i].execute();
	    }
	    return (msg == null ? 
                "Sorry, you can't " + verb + " the " + noun + "." : msg) +
                "\n";
        }

        return "Not sure what you mean?\n";
    }
}
