package item;

import java.util.HashMap;

import entity.HasInventory;

public class Crafter implements HasInventory {
	
	private Inventory inventory;
	
	private HashMap<String,Integer> items = new HashMap<String, Integer>();
	
	public Crafter(Inventory inv) {
		inventory = inv;
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}
	
	public void add(String name,Integer amount) throws NotEnoughItemException {
		getInventory().use(name,amount);
		if (!items.containsKey(name)) items.put(name, 0);
		items.put(name, items.get(name)+amount);
		System.out.println(name);
	}
	
	public void add(String name)  throws NotEnoughItemException {
		add(name,1);
	}
	
	private void use(String name,Integer amount) throws NotEnoughItemException {
		if (!items.containsKey(name)) throw new NotEnoughItemException(name);
		int target = items.get(name)-amount;
		if (target < 0) {
			throw new NotEnoughItemException(name);
		} else {
			items.put(name, target);
		}
	}
	
	private void use(String name) throws NotEnoughItemException {
		use(name,1);
	}
	
	public void giveBack(String name,Integer amount) throws NotEnoughItemException {
		use(name,amount);
		getInventory().add(name,amount);
	}
	
	public void giveBack(String name) throws NotEnoughItemException {
		giveBack(name,1);
	}
	
	public boolean has(String name,Integer amount) {
		if (!items.containsKey(name)) return false;
		return items.get(name) == amount;
	}
	
	public String getCraftTarget() {
		if (getInventory().getSwordLevel() == 0 && has("Iron", 3)) return "IronSword";
		if (getInventory().getSwordLevel() == 1 && has("Iron", 2) && has("Diamond", 3)) return "DiamondSword";
		if (getInventory().getArmorLevel() == 0 && has("Iron", 3) && has("Coal",1)) return "IronArmor";
		if (getInventory().getArmorLevel() == 1 && has("Diamond", 3) && has("Coal",2)) return "DiamondArmor";
		if (getInventory().getHelmetLevel() == 0 && has("Iron", 2) && has("Coal",2)) return "IronHelmet";
		if (getInventory().getHelmetLevel() == 1 && has("Diamond", 2) && has("Coal",4)) return "DiamondHelmet";
		if (getInventory().getBootLevel() == 0 && has("Iron", 2) && has("Coal",3)) return "IronBoot";
		if (getInventory().getBootLevel() == 1 && has("Diamond", 2) && has("Coal",6)) return "DiamondBoot";
		return "";
	}
	
	public void performCraft() {
		try {
			switch (getCraftTarget()) {
			case "IronSword": getInventory().setSwordLevel(1); use("Iron", 3); break;
			case "DiamondSword": getInventory().setSwordLevel(2); use("Iron", 2); use("Diamond", 3); break;
			case "IronArmor": getInventory().setArmorLevel(1); use("Iron", 3); use("Coal",1); break;
			case "DiamondArmor": getInventory().setArmorLevel(2); use("Diamond", 3); use("Coal",2); break;
			case "IronHelmet": getInventory().setHelmetLevel(1); use("Iron", 2); use("Coal",2); break;
			case "DiamondHelmet": getInventory().setHelmetLevel(2); use("Diamond", 2); use("Coal",4); break;
			case "IronBoot": getInventory().setBootLevel(1); use("Iron", 2); use("Coal",3); break;
			case "DiamondBoot": getInventory().setBootLevel(2); use("Diamond", 2); use("Coal",6); break;
			}
		} catch (NotEnoughItemException e) {
			
		}
	}
}
