package entity.ore;

import gui.Block;

public class Iron extends Ore
{

	public Iron(int row, int col)
	{
		super(row, col);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void render(Block b) {
		b.testLabel.setText("I");
	}

}
