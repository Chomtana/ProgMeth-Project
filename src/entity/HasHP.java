package entity;

public interface HasHP {
	public void takeDamage(double damage);
	public double getHP();
	public void setHP(double hp);
	public boolean isAlive();
}
