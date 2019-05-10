package entity.ore;

import entity.CanTakeBombDamage;
import entity.CanTakePhysicalDamage;
import entity.Entity;
import entity.HasArmor;
import entity.HasHP;
import entity.monster.Monster;

public abstract class Ore extends Entity implements HasHP, HasArmor, CanTakePhysicalDamage, CanTakeBombDamage
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
		
		setHP(getHP()-damage);
		
		System.out.println(getHP());
		
		if (!isAlive()) {
			kill();
		}
		
		return damage;
	}

}
