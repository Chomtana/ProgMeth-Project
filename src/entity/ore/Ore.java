package entity.ore;

import entity.CanTakeBombDamage;
import entity.CanTakePhysicalDamage;
import entity.Entity;
import entity.HasArmor;
import entity.HasHP;
import entity.HasInventory;

public abstract class Ore extends Entity implements HasHP, HasArmor, CanTakePhysicalDamage, CanTakeBombDamage, Pickable
{

	public Ore(int row,int col) {
		super(row,col);
		Ore thiss = this;
		/*bouncer = new ThreadRule<Boolean>() {
			
			@Override
			public void onChange(Boolean curr, Boolean prev) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Boolean get() {
				// TODO Auto-generated method stub
				Block.getBlock(oldRow, oldCol).setEntity(thiss);
				return null;
			}
		};*/
	}
	
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return getHP() > 0;
	}
	
	@Override
	public double takePhysicalDamage(double damage) {
		// TODO Auto-generated method stub
		
		damage -= getArmor();
		damage = Math.max(0, damage);
		
		setHP(getHP()-damage);
		
		System.out.println(getHP());
		
		if (!isAlive()) {
			kill();
		}
		
		return damage;
	}

	@Override
	public double takeBombDamage(double damage) {
		// TODO Auto-generated method stub
		
		damage -= getArmor();
		damage = Math.max(0, damage);
		
		setHP(getHP()-damage);
		
		System.out.println(getHP());
		
		if (!isAlive()) {
			kill();
		}
		
		return damage;
	}
	
	public abstract String getItem();
	
	public void pick(Entity target) {
		if (!isAlive()) {
			System.out.println("Perform pick");
			if (target instanceof HasInventory) {
				System.out.println("Has inventory");
				((HasInventory) target).getInventory().add(getItem());
			}
		}
	}

}
