
package edu.umw.cpsc240fall2015team8.zork;

/**
Replaces one item with another item. 
@author Austin
*/
class TransformEvent extends Event{

	Item item;
	String sitem;

	/**
		Creates a new TransformEvent.
		Works the same regardless of String values.
	*/
	TransformEvent(Item i, String s){
		item = i;
		sitem = s;
	}

	/**
		Replaces stored item 'a' with stored item 'b'.
		If either of the two stored items are null, does nothing.
	*/
	public void execute(){
	}
}
