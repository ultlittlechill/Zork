
package edu.umw.cpsc240fall2015team8.zork;

import java.util.Scanner;
/** @author Lucas*/
/** Contains the main for entire program, which interacts with the user via commands to do things in the dungeon. The main continuosly loops until the player decides to quit, wins, or dies.*/
public class Interpreter {

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton

    public static String USAGE_MSG = 
        "Usage: Interpreter zorkFile.zork|saveFile.sav.";
/** runs through the text file that holds the dungeon, prints the dungeon name, and if the player hits q then it quits the program. */
    public static void main(String args[]) {

        if (args.length < 1) {
            System.err.println(USAGE_MSG);
            System.exit(1);
        }

        String command;
        Scanner commandLine = new Scanner(System.in);

        try {
            state = GameState.instance();
            if (args[0].endsWith(".zork")) {
                state.initialize(new Dungeon(args[0]));
                System.out.println("\nWelcome to " + 
                    state.getDungeon().getName() + "!");
				if(GameState.instance().getDungeon.getVersion() == 2){
					System.out.println("A few things about our game engine:\n When an item is used too much it will break\n
							"this is represented with a 'x~' in front of that item. If you try to use a broken item it will do\n
							" about a quarter of it's original damage. You can trade with some Npcs, an example of how to do this is\n '
							"trade sword with merchant'. You can also choose to attack Npcs, an example of how to do that is \n
							"'attack goblin with sword' Which will print further instructions.\n");
            } else if (args[0].endsWith(".sav")) {
                state.restore(args[0]);
                System.out.println("\nWelcome back to " + 
                    state.getDungeon().getName() + "!");
            } else {
                System.err.println(USAGE_MSG);
                System.exit(2);
            }

            System.out.print("\n" + 
                state.getAdventurersCurrentRoom().describe() + "\n");

            command = promptUser(commandLine);

            while (!command.equals("q")) {

                System.out.print(
                    CommandFactory.instance().parse(command).execute());

                if(GameState.instance().getHealth() <= 0){
			System.out.println("You have died. Get good, noob.");
			break;
		}
		if(GameState.instance().getScore() >= GameState.instance().getScoreWin()){
			System.out.println("You win!!!");
			break;
		}
		command = promptUser(commandLine);
            }

            System.out.println("Bye!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }
/** prompts user for input, the letter or command that initiates an action */
    private static String promptUser(Scanner commandLine) {

        System.out.print("> ");
        return commandLine.nextLine();
    }

}
