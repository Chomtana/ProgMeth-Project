package item;

import java.util.HashMap;

public class Inventory {
	private HashMap<String,Integer> items = new HashMap<String, Integer>();
	
	private int swordLevel = 0;
	private int armorLevel = 0;
	private int helmetLevel = 0;
	private int bootLevel = 0;
	
	public void add(String name,Integer amount) {
		if (!items.containsKey(name)) items.put(name, 0);
		items.put(name, items.get(name)+amount);
		System.out.println(name);
	}
	
	public void add(String name) {
		add(name,1);
	}
	
	public void use(String name,Integer amount) throws NotEnoughItemException {
		if (!has(name,amount)) throw new NotEnoughItemException(name);
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
	
	public boolean has(String name,Integer amount) {
		if (!items.containsKey(name)) return false;
		int target = items.get(name)-amount;
		if (target<0) return false; else return true;
	}

	public int getSwordLevel() {
		return swordLevel;
	}

	public void setSwordLevel(int swordLevel) {
		this.swordLevel = swordLevel;
	}

	public int getArmorLevel() {
		return armorLevel;
	}

	public void setArmorLevel(int armorLevel) {
		this.armorLevel = armorLevel;
	}

	public int getHelmetLevel() {
		return helmetLevel;
	}

	public void setHelmetLevel(int helmetLevel) {
		this.helmetLevel = helmetLevel;
	}

	public int getBootLevel() {
		return bootLevel;
	}

	public void setBootLevel(int bootLevel) {
		this.bootLevel = bootLevel;
	}

	public HashMap<String, Integer> getItems() {
		return items;
	}
	
	
}
