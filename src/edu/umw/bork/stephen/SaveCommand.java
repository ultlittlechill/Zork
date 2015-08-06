
package edu.umw.bork.stephen;

class SaveCommand extends Command {

    private String saveFilename;

    SaveCommand(String saveFilename) {
        this.saveFilename = saveFilename;
    }

    public String execute() {
        try {
            GameState.instance().store(saveFilename);
            return "Data saved to " + saveFilename +
                GameState.SAVE_FILE_EXTENSION + ".\n";
        } catch (Exception e) {
            System.err.println("Couldn't save!");
            e.printStackTrace();
            return "";
        }
    }
}
