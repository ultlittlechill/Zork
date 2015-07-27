
package edu.umw.bork.stephen;

import java.io.*;
import java.util.*;


public class Interpreter {
    public static void main(String args[]) {
        try {
            new Interpreter().doIt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doIt() throws Exception {

        String command;
        BufferedReader commandLine = new BufferedReader(
            new InputStreamReader(System.in));
        System.out.print("> ");
        command = commandLine.readLine();
        while (!command.equals("quit")) {
            System.out.println(
                CommandFactory.instance().parse(command).execute());
            System.out.print("> ");
            command = commandLine.readLine();
        }
        System.out.println("Bye!");
    }
}
