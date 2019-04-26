package entity;

import controller.EventController;
import effect.Effect;
import gui.Block;

public abstract class Entity {
	protected boolean isAlive = true;
	
	public Entity() {
		Entity thiss = this;
		if (!(thiss instanceof Effect)) {
			EventController.onLoad(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Block.getBlock(getRow(), getCol()).setEntity(thiss);
				}
			});
		};
		
	}

	public Entity(int row,int col) {
		setRow(row);
		setCol(col);
		//if (! (this instanceof NullEntity))
		//System.out.println("ROW "+getRow()+"COL "+getCol());
		Entity thiss = this;
		if (!(thiss instanceof Effect)) {
			EventController.onLoad(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//System.out.println(getRow());
					//System.out.println(getCol());
					Block.getBlock(getRow(), getCol()).setEntity(thiss);
				}
			});
		}
	}
	
	public abstract String getIcon();
	public void render(Block block) {
		block.testLabel.setText("");
	};
	public abstract int getRow();
	public abstract int getCol();
	public abstract void setRow(int row);
	public abstract void setCol(int col);
	
	public boolean getIsAlive() {
		return isAlive;
	}
	
	public void kill() {
		isAlive = false;
		if (!(this instanceof Effect)) {
			Block.getBlock(getRow(), getCol()).removeEntity();
		} else {
			Block.getBlock(getRow(), getCol()).removeEffect((Effect) this); 
		}
	}
}
