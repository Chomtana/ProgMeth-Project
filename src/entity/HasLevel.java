package entity;

public interface HasLevel {
	public int getLevel();
	public void setLevel(int level);
	public int getCurrentLevelEXPNeeded();
	public void receiveEXP(int exp);
	public int getExp();
	public void setExp(int exp);
}
