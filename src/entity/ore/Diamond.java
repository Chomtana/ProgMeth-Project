package entity.ore;

import gui.Block;
import gui.ImageStore;

public class Diamond extends Ore
{
	
	private double hp = 20;
	private double armor = 4;

	public Diamond(int row, int col)
	{
		super(row, col);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return ImageStore.getInstance().diamondBlock;
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
		return "Diamond";
	}

}
