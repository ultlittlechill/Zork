package edu.umw.cpsc240fall2015team8.zork;

/**
Determines which subclass of Event is needed for different events.
@author Austin
*/
public class EventFactory{

	/**
		Returns the one and only instance of EventFactory. In the one time only scenario where no EventFactory exists, it creates one, and then returns that.
	*/
	public static synchronized EventFactory instance(){
	}

	/**
		Instantiates a new EventFactory object.
	*/
	private EventFactory(){
	}

	/**
		Returns an different Event depending on which subclass of Event best suits the passed String.
	*/
	public Event parse(String event){
	}
}
