package edu.umw.cpsc240fall2015team8.zork;

class LightEvent extends Event{

	String item;

	LightEvent(Item i){
		item = i.getPrimaryName();
	}

	public void execute(){
		GameState g = GameState.instance();
		try{
			Item item2 = g.getItemFromInventoryNamed(item);
			g.setIsLit(true);
			g.addLitItem(item2);
		}catch(Item.NoItemException e){
			try{
				Item item2 = g.getItemInVicinityNamed(item);
				g.getAdventurersCurrentRoom().addLitItem(item2);
			}catch(Item.NoItemException f){}
		}
	}
}
