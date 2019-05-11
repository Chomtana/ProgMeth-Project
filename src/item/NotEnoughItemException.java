package item;

public class NotEnoughItemException extends Exception {
	private String itemname;
	
	public NotEnoughItemException() {
		super("Not enough item in inventory");
	}
	
	public NotEnoughItemException(String itemname) {
		super("Not enough "+itemname+" in inventory");
		this.itemname = itemname;
		
	}

	public String getItemname() {
		return itemname;
	}
	
	
}
