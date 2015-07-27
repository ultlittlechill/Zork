
package edu.umw.bork.stephen;

public class CommandFactory {

    private static CommandFactory theInstance;

    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

    private CommandFactory() {
    }

    public Command parse(String command) {
        return new Command();
    }

}
