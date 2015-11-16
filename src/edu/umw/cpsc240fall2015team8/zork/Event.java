package edu.umw.cpsc240fall2015team8.zork;

/**
Handles different events triggered by item specific commands. Since this is an abstract class, be sure to use one of the subclasses.
@author Austin
*/
abstract class Event{

	/**
	Executes what this Event is meant to do. This is an abstract meathod, sub classes of Event will have more specific descriptions.	
	*/
	abstract void execute();
}
