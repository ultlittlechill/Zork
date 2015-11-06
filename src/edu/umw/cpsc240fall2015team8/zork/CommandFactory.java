
package edu.umw.cpsc240fall2015team8.zork;

import java.util.List;
import java.util.Arrays;

/**determines what subclasses of Command are needed for different tasks.*/
public class CommandFactory {

    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );

/**returns the one and only instance of the CommandFactory*/
    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

/**Instantiates a new CommandFactory Object*/
    private CommandFactory() {
    }

/**Returns a different subclass of Command depending on what verb is found in the String passed.
If the verb can not be parsed from the String or a verb is not recognized returns an UnknownCommand*/
    public Command parse(String command) {
        String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equals("save")) {
            return new SaveCommand(noun);
        }
        if (verb.equals("take")) {
            return new TakeCommand(pasteSecondAndBeyond(parts));
        }
        if (verb.equals("drop")) {
            return new DropCommand(pasteSecondAndBeyond(parts));
        }
        if (verb.equals("look")) {
            return new LookCommand();
        }
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
        if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }
        if (parts.length >= 2) {
            return new ItemSpecificCommand(command);
        }
        return new UnknownCommand(command);
    }

/**converts and returns an array of Strings into one String with words seperated by spaces, skipping the first word in the array.
If the array is shorter than two returns an empty String*/
    private String pasteSecondAndBeyond(String[] parts) {
        if (parts.length < 2) {
            return "";
        }
        String string = parts[1];
        for (int i=2; i<parts.length; i++) {
            string += " " + parts[i];
        }
        return string;
    }
}
