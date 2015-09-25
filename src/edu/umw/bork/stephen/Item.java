
package edu.umw.bork.stephen;

import java.util.Scanner;
import java.util.Hashtable;

public class Item {

    static class NoItemException extends Exception {}

    private String name;
    private int weight;
    private Hashtable<String,String> messages;


    void init() {
        messages = new Hashtable<String,String>();
    }

    public Item(String name, int weight) {
        init();
        this.name = name;
        this.weight = weight;
    }

    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        init();

        // Read item name.
        name = s.nextLine();
        if (name.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
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
            messages.put(verbParts[0],verbParts[1]);
            
            verbLine = s.nextLine();
        }
    }

    boolean goesBy(String name) {
        // could have other aliases
        return this.name.equals(name);
    }

    String getPrimaryName() { return name; }

    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

    public String toString() {
        return "A " + name;
    }
}
