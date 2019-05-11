package item;

import java.util.HashMap;

public class Inventory {
	private HashMap<String,Integer> items = new HashMap<String, Integer>();
	
	public void add(String name,Integer amount) {
		if (!items.containsKey(name)) items.put(name, 0);
		items.put(name, items.get(name)+amount);
		System.out.println(name);
	}
	
	public void add(String name) {
		add(name,1);
	}
	
	public void use(String name,Integer amount) throws NotEnoughItemException {
		if (!items.containsKey(name)) throw new NotEnoughItemException(name);
		int target = items.get(name)-amount;
		if (target < 0) {
			throw new NotEnoughItemException(name);
		} else {
			items.put(name, target);
		}
	}
	
	public void use(String name) throws NotEnoughItemException {
		use(name,1);
	}
}
