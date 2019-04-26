package entity;

import java.util.ArrayList;

import entity.monster.EnemyMonster;
import entity.monster.Monster;
import gui.Block;
import gui.GameAreaInner;

public class Player extends Monster {
	
	public static int START_ROW = 0;
	public static int START_COL = 0;
	
	public static Player mainPlayer = new Player(START_ROW,START_COL);
	private Direction facing = Direction.UP;
	
	protected int moveDelay = 250;
	
	public Player(int row,int col) {
		super(row,col);
	}

	@Override
	public void attack(HasHP target) {
		// TODO Auto-generated method stub
		if (!canAttack(target)) return;
		
		target.takeDamage(1);
		
		super.attack(target);
	}

	@Override
	public boolean canAttack(HasHP target) {
		// TODO Auto-generated method stub
		return super.canAttack(target);
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
		if (e instanceof MoveCollideException) {
			MoveCollideException ee = (MoveCollideException) e;
			System.out.println("dsasdasad");
			//ee.getWith().kill();
			Entity target = ee.getWith();
			if (target instanceof HasHP) {
				attack((HasHP) target);
			}
		}
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
	
	public ArrayList<EnemyMonster> getSurroundingEnemies() {
		ArrayList<EnemyMonster> res = new ArrayList<EnemyMonster>();
		
		Player p = this;
		int sr = p.getRow();
		int sc = p.getCol();
		
		for(int i = sr-15;i<=sr+15;i++) {
			for (int j = sc-15;j<=sc+15;j++) {
				if (i<0 || j<0 || i>=GameAreaInner.NUM_ROW || j>=GameAreaInner.NUM_COL) continue;
				Entity ee = Block.getBlock(i, j).getEntity();
				if (!(ee instanceof EnemyMonster)) continue;
				EnemyMonster e = (EnemyMonster) ee;
				res.add(e);
			}
		}
		
		return res;
	}

}
