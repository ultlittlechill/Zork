package edu.umw.cpsc240fall2015team8.zork;

/**
Determines which subclass of Event is needed for different events.
@author Austin
*/
public class EventFactory{

	private static EventFactory theInstance;
	
	/**
		Returns the one and only instance of EventFactory. In the one time only scenario where no EventFactory exists, it creates one, and then returns that.
	*/
	public static synchronized EventFactory instance(){
		if(theInstance == null) {
			theInstance = new EventFactory();
		}
		return theInstance;
	}

	/**
		Instantiates a new EventFactory object.
	*/
	private EventFactory(){
	}

	/**
		Returns an different Event depending on which subclass of Event best suits the passed String.
		If the passed String does not represent an Event, returns null.
	*/
	public Event parse(String event, Item item){
		String pass = "";
		if(event.contains("Score")){
			pass = event.substring(6,event.length());
			pass = pass.split(")")[0];
			return new ScoreEvent(Integer.parseInt(pass));
		}
		if(event.contains("Wound")){
			pass = event.substring(6, event.length());
			pass = pass.split(")")[0];
			return new WoundEvent(Integer.parseInt(pass));
		}
		if(event.contains("Die")){
			return new DieEvent();
		}
		if(event.contains("Win")){
			return new WinEvent();
		}
		if(event.contains("Disappear")){
			return new DisappearEvent(item);
		}
		if(event.contains("Teleport")){
			return new TeleportEvent();
		}
		if(event.contains("Transform")){
			pass = event.substring(10, event.length());
			pass = pass.split(")")[0];
			return new TransformEvent(item, pass);
		}
		return null;	
	}
}
