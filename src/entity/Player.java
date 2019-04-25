package entity;

import entity.monster.Monster;
import gui.Block;

public class Player extends Monster {
	
	public static int START_ROW = 0;
	public static int START_COL = 0;
	
	public static Player mainPlayer = new Player(START_ROW,START_COL);
	private Direction facing = Direction.UP;
	
	protected int moveDelay = 100;
	
	public Player(int row,int col) {
		super(row,col);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canAttack() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void render(Block b) {
		b.testLabel.setText("P");
	}
	
	public void handleUnmoveableException(UnmoveableException e) {
		
	}
	
	public void moveLeft() {
		this.moveTo(getRow(), getCol()-1);
		setFacing(Direction.LEFT);
	}
	
	public void moveRight() {
		this.moveTo(getRow(), getCol()+1);
		setFacing(Direction.RIGHT);
	}
	
	public void moveUp() {
		this.moveTo(getRow()-1, getCol());
		setFacing(Direction.UP);
	}
	
	public void moveDown() {
		this.moveTo(getRow()+1, getCol());
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
	}

}
