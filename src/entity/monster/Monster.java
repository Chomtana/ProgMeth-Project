package entity.monster;

import entity.Entity;
import entity.Moveable;
import entity.UnmoveableException;
import rule.ThreadRule;

public abstract class Monster extends Entity implements Moveable {

	protected int currRow = 0;
	protected int currCol = 0;
	
	public abstract void attack();
	
	public int getRow() {
		return currRow;
	}
	
	public int getCol() {
		return currCol;
	}
	
	public boolean moveTo(int row,int col) throws UnmoveableException {
		
		return true;
	}

}
