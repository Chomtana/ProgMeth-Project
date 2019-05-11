package entity.monster;

import entity.Direction;
import entity.GiveEXPOnDead;
import entity.UnmoveableException;

public abstract class EnemyMonster extends Monster implements GiveEXPOnDead {

	private Direction facing = Direction.UP;
	
	protected int moveDelay = 400;
	
	public EnemyMonster(int row,int col) {
		super(row,col);
	}
	
	public abstract void handleUnmoveableException(UnmoveableException e);
	
	public void moveLeft() {
		if (this.moveTo(getRow(), getCol()-1))
			setFacing(Direction.LEFT);
	}
	
	public void moveRight() {
		if (this.moveTo(getRow(), getCol()+1))
			setFacing(Direction.RIGHT);
	}
	
	public void moveUp() {
		if (this.moveTo(getRow()-1, getCol()))
			setFacing(Direction.UP);
	}
	
	public void moveDown() {
		if (this.moveTo(getRow()+1, getCol()))
			setFacing(Direction.DOWN);
	}
	
	@Override
	public boolean moveTo(int row,int col) {
		try {
			return super.moveTo(row, col);
		} catch (UnmoveableException e) {
			// TODO Auto-generated catch block
			handleUnmoveableException(e);
		}
		return false;
	}
	
	@Override
	public int getMoveDelay() {
		return moveDelay;
	}

	public Direction getFacing() {
		return facing;
	}

	public void setFacing(Direction facing) {
		this.facing = facing;
		this.render(getBlock());
	}

}
