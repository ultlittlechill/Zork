
package edu.umw.stephen.bork;

import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;

public class Item {

    static class NoItemException extends Exception {}

    private String primaryName;
    private ArrayList<String> aliases;
    private int weight;
    private Hashtable<String,String> messages;


    private void init() {
        messages = new Hashtable<String,String>();
        aliases = new ArrayList<String>();
    }

    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        init();

        // Read item name.
        String namesLine = s.nextLine();
        if (namesLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }

        String[] names = namesLine.split(",");
        primaryName = names[0];
        for (int i=1; i<names.length; i++) {
            aliases.add(names[i]);
        }
        

        // Read item weight.
        weight = Integer.valueOf(s.nextLine());

        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
            String[] verbParts = verbLine.split(":");
            String[] verbAliases = verbParts[0].split(",");
            for (String verbAlias : verbAliases) {
                messages.put(verbAlias,verbParts[1]);
            }
            
            verbLine = s.nextLine();
        }
    }

    boolean goesBy(String name) {
        return primaryName.equals(name) || aliases.contains(name);
    }

    String getPrimaryName() { return primaryName; }

    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

    public String toString() {
        return primaryName;
    }
}
