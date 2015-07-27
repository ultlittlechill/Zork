
package edu.umw.bork.stephen;

import java.io.*;
import java.util.*;


public class Interpreter {

    private Room currentRoom = null;

    public static void main(String args[]) {
        try {
            new Interpreter().doIt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doIt() throws Exception {

        Dungeon trinkle = buildDungeon();

        String command;
        BufferedReader commandLine = new BufferedReader(
            new InputStreamReader(System.in));
        System.out.println("Welcome to " + trinkle.getName() + "!");

        currentRoom = trinkle.getEntry();

        command = promptUser(commandLine);

        while (!command.equals("quit")) {

            System.out.println(
                CommandFactory.instance().parse(command).execute());

            command = promptUser(commandLine);
        }

        System.out.println("Bye!");
    }

    private String promptUser(BufferedReader commandLine) throws IOException {
        System.out.println(currentRoom.describe());
        System.out.print("> ");
        return commandLine.readLine();
    }

    private Dungeon buildDungeon() {
        Room rotunda = new Room("Rotunda");
        rotunda.setDesc(
"You are in a beautiful round room, with a ceiling that seemingly reaches\n" +
"to the skies.");
        return new Dungeon("Trinkle", rotunda);
    }

}
