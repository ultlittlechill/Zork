
package edu.umw.cpsc240fall2015team8.zork;

/**Completes an action in the Dungeon that the user entered. 
This class is purely abstract, so please refer to one of the many subclasses for specific uses.
@author Jeff Wallhermfechtel*/
abstract class Command {

/**Executes what this Command is intended to do, and returns a String 
that tells what was done.*/
    abstract String execute();

}
