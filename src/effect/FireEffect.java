package effect;

import entity.CanTakeBombDamage;
import entity.CanTakePhysicalDamage;
import entity.Entity;
import entity.GiveEXPOnDead;
import entity.HasLevel;
import entity.ore.Pickable;
import gui.ImageStore;

public class FireEffect extends AutokillEffect {
	
	private double atkDamage;
	private Entity attacker;
	
	public FireEffect(Entity e, double atkDamage) {
		this(e.getRow(), e.getCol(), atkDamage, e);
		// TODO Auto-generated constructor stub
	}

	public FireEffect(int row, int col, double atkDamage, Entity attacker) {
		super(row, col,300);
		this.attacker = attacker;
		this.atkDamage = atkDamage;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollideWith(Entity target) {
		// TODO Auto-generated method stub
		if (target instanceof CanTakeBombDamage) {
			((CanTakeBombDamage) target).takeBombDamage(getAtkDamage());
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
		return ImageStore.getInstance().fire;
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
