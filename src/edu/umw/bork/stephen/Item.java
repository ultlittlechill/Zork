
package edu.umw.bork.stephen;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;

public class Item {

    class NoItemException extends Exception {}

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

    Item(BufferedReader r) throws IOException, NoItemException,
        Dungeon.IllegalDungeonFormatException {

        init();

        // Read item name.
        name = r.readLine();
        if (name.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }

        // Read item weight.
        weight = Integer.valueOf(r.readLine());

        // Read and parse verbs lines, as long as there are more.
        String verbLine = r.readLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
            String[] verbParts = verbLine.split(":");
            messages.put(verbParts[0],verbParts[1]);
            
            verbLine = r.readLine();
        }
    }

    boolean goesBy(String name) {
        // could have other aliases
        return this.name.equals(name);
    }

    String getName() { return name; }

    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }
}
