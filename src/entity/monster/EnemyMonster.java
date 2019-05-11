package entity.monster;

import java.util.Random;

import controller.EventController;
import entity.Direction;
import entity.Player;
import entity.UnmoveableException;
import gui.Block;
import rule.ThreadRule;

public abstract class EnemyMonster extends Monster {

	private Direction facing = Direction.UP;
	
	protected int moveDelay = 400;
	private ThreadRule<Boolean> moveAI;
	
	public EnemyMonster(int row,int col) {
		super(row,col);
		moveAI = new ThreadRule<Boolean>() {
			
			@Override
			public void onChange(Boolean curr, Boolean prev) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Boolean get() {
				// TODO Auto-generated method stub

				
				return null;
			}
		};
	}
	
	public void handleUnmoveableException(UnmoveableException e) {
		
	}
	
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
