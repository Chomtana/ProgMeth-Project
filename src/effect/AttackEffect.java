package effect;

import entity.CanTakePhysicalDamage;
import entity.Entity;

public class AttackEffect extends AutokillEffect {
	
	private double atkDamage;
	
	public AttackEffect(Entity e, double atkDamage) {
		this(e.getRow(), e.getCol(), atkDamage);
		// TODO Auto-generated constructor stub
	}

	public AttackEffect(int row, int col, double atkDamage) {
		super(row, col,100);
		this.atkDamage = atkDamage;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollideWith(Entity target) {
		// TODO Auto-generated method stub
		if (target instanceof CanTakePhysicalDamage) {
			((CanTakePhysicalDamage) target).takePhysicalDamage(getAtkDamage());
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
