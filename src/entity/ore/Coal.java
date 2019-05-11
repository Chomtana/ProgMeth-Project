package entity.ore;

import gui.Block;
import gui.ImageStore;

public class Coal extends Ore
{
	
	private double hp = 4;
	private double armor = 0;

	public Coal(int row, int col)
	{
		super(row, col);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return ImageStore.getInstance().coalBlock;
	}

	@Override
	public double getHP() {
		// TODO Auto-generated method stub
		return hp;
	}

	@Override
	public void setHP(double hp) {
		// TODO Auto-generated method stub
		this.hp = hp;
	}

	@Override
	public double getArmor() {
		// TODO Auto-generated method stub
		return armor;
	}

	@Override
	public void setArmor(double armor) {
		// TODO Auto-generated method stub
		this.armor = armor;
	}
	
	@Override
	public String getItem() {
		// TODO Auto-generated method stub
		return "Coal";
	}

}
