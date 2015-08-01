
package edu.umw.bork.stephen;

import java.io.*;
import java.util.*;


public class Interpreter {

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton

    public static void main(String args[]) {

        try {
            GameState.instance().initialize(buildTrinkleDungeon());
            state = GameState.instance();

            String command;
            BufferedReader commandLine = new BufferedReader(
                new InputStreamReader(System.in));
            System.out.println("Welcome to " + state.getDungeon().getName() +
                "!");

            command = promptUser(commandLine);

            while (!command.equals("q")) {

                System.out.println(
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

        System.out.println(state.getAdventurersCurrentRoom().describe());
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
        rotunda.addExit(basement,"d");
        basement.addExit(rotunda,"u");
        basement.addExit(b6,"w");
        basement.addExit(stephensOffice,"e");
        stephensOffice.addExit(basement,"w");
        b6.addExit(basement,"e");
        
        return new Dungeon("Trinkle", rotunda);
    }

}
