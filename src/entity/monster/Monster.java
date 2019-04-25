package entity.monster;

import entity.Attackable;
import entity.Entity;
import entity.MoveCollideException;
import entity.MoveOutOfBoundException;
import entity.Moveable;
import entity.UnmoveableException;
import gui.Block;
import gui.GameAreaInner;
import javafx.application.Platform;
import rule.ThreadRule;

public abstract class Monster extends Entity implements Moveable, Attackable {

	protected int currRow = 0;
	protected int currCol = 0;
	
	private Thread moveThrottle = null;
	
	public Monster(int row,int col) {
		this.currRow = row;
		this.currCol = col;
	}
	
	public int getRow() {
		return currRow;
	}
	
	public int getCol() {
		return currCol;
	}
	
	public boolean canMoveTo(int row,int col) throws UnmoveableException {
		if (row < 0 || row >= GameAreaInner.NUM_ROW || col < 0 || col >= GameAreaInner.NUM_COL) {
			throw new MoveOutOfBoundException();
		}
		
		if (Block.getBlock(row, col).hasEntity()) {
			throw new MoveCollideException(Block.getBlock(row, col).getEntity());
		}
		
		return true;
	}
	
	public boolean moveTo(int row,int col) throws UnmoveableException {
		//throw exception
		//System.out.println(row+" "+col+" "+canMoveTo(row,col));
		if (!canMoveTo(row,col)) {
			throw new UnmoveableException("Cannot move to ("+row+","+col+")");
		}
		
		if (moveThrottle == null || !moveThrottle.isAlive()) {
			int oldRow = currRow;
			int oldCol = currCol;
			
			currRow = row;
			currCol = col;
			
			Monster thiss = this;
			moveThrottle = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Block.getBlock(oldRow, oldCol).removeEntity();
								Block.getBlock(currRow, currCol).setEntity(thiss);
							}
						});

						Thread.sleep(getMoveDelay());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}
			});
			moveThrottle.start();
		}
		return true;
	}
	
	public double getRenderRow() {
		return currRow;
	}
	
	public double getRenderCol() {
		return currCol;
	}

	public int getMoveDelay() {
		return 500;
	}
	
	

}
