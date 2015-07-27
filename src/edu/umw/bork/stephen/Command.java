
package edu.umw.bork.stephen;

public class Command {
    public static void main(String args[]) {
        try {
            new Command().doIt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doIt() throws Exception {
         
    }

    public String execute() {
        return "That was good, thanks.";
    }
}
