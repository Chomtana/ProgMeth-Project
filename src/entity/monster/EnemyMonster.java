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
	
	protected int moveDelay = 250;
	protected ThreadRule<Integer> moveAI;
	
	public EnemyMonster(int row,int col) {
		super(row,col);
		EventController.onLoad(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				moveAI = new ThreadRule<Integer>() {
					
					@Override
					public void onChange(Integer curr, Integer prev) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public Integer get() {
						// TODO Auto-generated method stub
						int rDist = Math.abs(Player.mainPlayer.getRow() - getRow());
						int cDist = Math.abs(Player.mainPlayer.getCol() - getCol());
						int distance = rDist + cDist;
						if (distance==0) return 0;
						int rDir = 0;
						int cDir = 0;
						
						if (Player.mainPlayer.getRow() < getRow()) rDir = -1;
						else if (Player.mainPlayer.getRow() > getRow()) rDir = 1;
						
						if (Player.mainPlayer.getCol() < getCol()) cDir = -1;
						else if (Player.mainPlayer.getCol() > getCol()) cDir = 1;
						
						int rand = (new Random()).nextInt(distance);

						if (rand < rDist) {
							moveTo(getRow()+rDir,getCol());
						} else {
							moveTo(getRow(),getCol()+cDir);
						}
						
						return null;
					}
				};
			}
		});
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
