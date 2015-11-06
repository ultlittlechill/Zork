package edu.umw.cpsc240fall2015team8.zork;

/**
Handles different events triggered by item specific commands. Since this is an abstract class, be sure to use one of the subclasses.
@author Austin
*/
abstract class Event{

	/**
		Executes the event. If null, works the exact same way.
	*/
	abstract String execute();
}
