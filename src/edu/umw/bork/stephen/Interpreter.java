
package edu.umw.bork.stephen;

import java.io.*;
import java.util.*;


public class Interpreter {

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton

    public static void main(String args[]) {

        try {
            state = GameState.instance();
            state.initialize(buildTrinkleDungeon());

            String command;
            BufferedReader commandLine = new BufferedReader(
                new InputStreamReader(System.in));
            System.out.println("\nWelcome to " + state.getDungeon().getName() +
                "!");

            command = promptUser(commandLine);

            while (!command.equals("q")) {

                System.out.print(
                    CommandFactory.instance().parse(command).execute());

                command = promptUser(commandLine);
            }

            System.out.println("Bye!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }

    private static String promptUser(BufferedReader commandLine) 
        throws IOException {

        System.out.println("\n" + 
            state.getAdventurersCurrentRoom().describe());
        System.out.print("> ");
        return commandLine.readLine();
    }

    private static Dungeon buildTrinkleDungeon() {
        Room rotunda = new Room("Rotunda");
        rotunda.setDesc(
"You are in a beautiful round room, with a ceiling that seemingly reaches\n" +
"to the skies.");
        Room basement = new Room("Basement hallway");
        basement.setDesc(
"You are in a dusty, decrepit basement with the faint smell of body odor.");
        Room stephensOffice = new Room("Stephen's office");
        stephensOffice.setDesc(
"This is a cluttered office, with many geeky toys.");
        Room b6 = new Room("B6");
        b6.setDesc(
"Sunlight streams through tall windows and illuminates a brilliant\n" +
"classroom.");
        new Exit("d",rotunda,basement);
        new Exit("u",basement,rotunda);
        new Exit("w",basement,b6);
        new Exit("e",basement,stephensOffice);
        new Exit("w",stephensOffice,basement);
        new Exit("e",b6,basement);
            
        return new Dungeon("Trinkle", rotunda);
    }

}
