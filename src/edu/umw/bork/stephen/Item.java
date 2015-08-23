
package edu.umw.bork.stephen;

import java.io.BufferedReader;
import java.io.IOException;

public class Item {

    class NoItemException extends Exception {}

    private String name;
    private int weight;

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    Item(BufferedReader r) throws IOException, NoItemException,
        Dungeon.IllegalDungeonFormatException {

        // Read item name.
        name = r.readLine();
        if (name.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }

        // Read item weight.
        weight = Integer.valueOf(r.readLine());

        // throw away delimiter
        if (!r.readLine().equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after item.");
        }
    }

    boolean goesBy(String name) {
        // could have other aliases
        return this.name.equals(name);
    }

    String getName() { return name; }
}
