
package edu.umw.cpsc240fall2015team8.zork;

import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//import java.util.regex.*;
/** Contains the methods that initialize the item Hashtables and Arraylists, and the methods to get items from the dungeon text file, their descriptions, and weight. 

@Author Lucas */
public class Item {

    /** Gets thrown when a text file representing an Item is not properly formatted. */
    static class NoItemException extends Exception {}
    static Random rng = new Random();

    private String primaryName;
    private ArrayList<String> aliases;
    private int weight;
    private Hashtable<String,String[]> messages;
    private Hashtable<String,Event[]> events;

/** Initializes a new Hashtable to hold the item descriptions and an array list for the aliases
*/
    private void init() {
        messages = new Hashtable<String,String[]>();
        aliases = new ArrayList<String>();
	events = new Hashtable<String,Event[]>();
    }
/** Call init and then parses through the dungeon text file, adding any items to the Array list and their descriptions to the Hashtable, also gets their weight and checks for verb lines. */
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
            String[] verbAliases;// = verbParts[0].split(",");
            String[] messageTexts = verbParts[1].split("\\|");
	    //Check for events
	    if(verbParts[0].contains("[")){
		//String[] es1 = verbAliases[verbAliases.length - 1].split("\\[");
		//System.out.println(es1[0]);
		//String es = es1[1];
		int thisguy = verbParts[0].indexOf("[");
		String thatguy = verbParts[0];
		String es = thatguy.substring(thisguy+1, thatguy.length()-1);
		//System.out.println(thatguy);
		//System.out.println(thatguy.substring(0,thisguy));
		//System.out.println(verbAliases[0]);
		verbAliases = thatguy.substring(0,thisguy).split(",");
		//for(int i = 0; i < verbAliases.length; i++){
		//	System.out.println(verbAliases[i]);
		//}
		//System.out.println(verbAliases[0]);
		//verbAliases[verbAliases.length - 1].split("[")[0];
	    	//es = es.substring(0,es.length()-1);
		String[] ess = es.split(",");
		ArrayList<Event> evprm = new ArrayList<Event>();
		for(int i = 0; i < ess.length; i++){
			Event evtmp = EventFactory.instance().parse(ess[i],this);
			evprm.add(evtmp);
		}
		
		for(String verbAlias : verbAliases) {
			events.put(verbAlias, evprm.toArray(new Event[0]));
		}
	    }else{
		verbAliases = verbParts[0].split(",");
	    }
            for (String verbAlias : verbAliases) {
                messages.put(verbAlias, messageTexts);
		System.out.println(verbAlias);
            }
            
            verbLine = s.nextLine();
        }
    }
/** checks to see if the name matches that of the item */
    boolean goesBy(String name) {
        return primaryName.equals(name) || aliases.contains(name);
    }
/** returns the primary name variable */
    String getPrimaryName() { return primaryName; }
/** returns the verb message for the indicated item */
    public String getMessageForVerb(String verb) {
        String[] possibleMessages = messages.get(verb);
	//System.out.println(possibleMessages.length);
        if(possibleMessages == null){
		//System.out.println("Im here");
		return null;
	}
	return possibleMessages[rng.nextInt(possibleMessages.length)];
    }

    public Event[] getEventsForVerb(String verb) {
	//Event a[] = new Event[0];
	//return events.values().toArray(a);
	return events.get(verb);
    }

/** return primary name; */
    public String toString() {
        return primaryName;
    }
/** returns the weight variable for an item */
    public int getWeight() {
        return weight;
    }
}
