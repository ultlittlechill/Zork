
package edu.umw.cpsc240fall2015team8.zork;

/**
Saves the current state of the game to a file in order to be loaded and resumed later.
@author Austin
*/
class SaveCommand extends Command {

    private static String DEFAULT_SAVE_FILENAME = "zork";

    private String saveFilename;

    /**
	Creates a new SaveCommand bound to the file represented by the strin gpassed. If either null or an empty string were passed, the SaveCommand is bound to a default save name.
    */
    SaveCommand(String saveFilename) {
        if (saveFilename == null || saveFilename.length() == 0) {
            this.saveFilename = DEFAULT_SAVE_FILENAME;
        } else {
            this.saveFilename = saveFilename;
        }
    }

    /**
	Saves the state of the game to the target file, and returns a string stating so.
	If an error occurs while attempting to save, a System error is printed and the method returns an empty string.
    */
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
