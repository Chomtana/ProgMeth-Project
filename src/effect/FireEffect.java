package effect;

import entity.CanTakeBombDamage;
import entity.CanTakePhysicalDamage;
import entity.Entity;

public class FireEffect extends AutokillEffect {
	
	private double atkDamage;
	
	public FireEffect(Entity e, double atkDamage) {
		this(e.getRow(), e.getCol(), atkDamage);
		// TODO Auto-generated constructor stub
	}

	public FireEffect(int row, int col, double atkDamage) {
		super(row, col,300);
		this.atkDamage = atkDamage;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollideWith(Entity target) {
		// TODO Auto-generated method stub
		if (target instanceof CanTakeBombDamage) {
			((CanTakeBombDamage) target).takeBombDamage(getAtkDamage());
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
	
	

}
