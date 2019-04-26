package entity;

public interface Attackable {
	public abstract void attack(HasHP target);
	public abstract boolean canAttack(HasHP target);
}
