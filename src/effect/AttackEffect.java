package effect;

import entity.CanTakePhysicalDamage;
import entity.Entity;
import entity.GiveEXPOnDead;
import entity.HasInventory;
import entity.HasLevel;
import entity.ore.Ore;
import entity.ore.Pickable;

public class AttackEffect extends AutokillEffect {
	
	private double atkDamage;
	private Entity attacker;
	
	public AttackEffect(Entity target, double atkDamage, Entity attacker) {
		this(target.getRow(), target.getCol(), atkDamage, attacker);
		// TODO Auto-generated constructor stub
	}

	public AttackEffect(int row, int col, double atkDamage, Entity attacker) {
		super(row, col,200);
		this.atkDamage = atkDamage;
		this.attacker = attacker;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollideWith(Entity target) {
		// TODO Auto-generated method stub
		if (target instanceof CanTakePhysicalDamage) {
			((CanTakePhysicalDamage) target).takePhysicalDamage(getAtkDamage());
			if (target instanceof Pickable) {
				Pickable ore = (Pickable) target;
				System.out.println("Pickable");
				ore.pick(attacker);
			}
			if (!target.isAlive()) {
				if (target instanceof GiveEXPOnDead && attacker instanceof HasLevel) {
					((HasLevel) attacker).receiveEXP(((GiveEXPOnDead) target).getExpGived());
				}
			}
		}
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getAtkDamage() {
		return atkDamage;
	}

	public void setAtkDamage(double atkDamage) {
		this.atkDamage = atkDamage;
	}

	public Entity getAttacker() {
		return attacker;
	}

	public void setAttacker(Entity attacker) {
		this.attacker = attacker;
	}
	
	

}
