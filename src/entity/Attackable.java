package entity;

public interface Attackable {
	public abstract void attack(CanTakePhysicalDamage target);
	public abstract boolean canAttack(CanTakePhysicalDamage target);
}
