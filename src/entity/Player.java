package entity;

import entity.monster.Monster;
import gui.Block;

public class Player extends Monster {
	
	public static Player mainPlayer = new Player();
	
	protected int moveDelay = 100;

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
	}
	
	public void moveRight() {
		this.moveTo(getRow(), getCol()+1);
	}
	
	public void moveUp() {
		this.moveTo(getRow()-1, getCol());
	}
	
	public void moveDown() {
		this.moveTo(getRow()+1, getCol());
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
		return 100;
	}

}
