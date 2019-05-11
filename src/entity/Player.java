package entity;

import java.util.ArrayList;

import application.Main;
import effect.AttackEffect;
import effect.BombEffect;
import entity.monster.EnemyMonster;
import entity.monster.Monster;
import gui.Block;
import gui.GameAreaInner;

public class Player extends Monster implements HasHP, HasArmor, CanTakePhysicalDamage, CanTakeBombDamage {
	
	public static int START_ROW = 0;
	public static int START_COL = 0;
	
	public static Player mainPlayer = new Player(START_ROW,START_COL);
	private Direction facing = Direction.UP;
	
	
	protected int moveDelay = 100;
	
	private double hp = 100;
	private double armor = 0;
	private double atkDamage = 5;
	private double bombDamage = 10;
	private int bombRadius = 3;
	
	public Player(int row,int col) {
		super(row,col);
	}

	@Override
	public void attack(CanTakePhysicalDamage target) {
		// TODO Auto-generated method stub
		if (!canAttack(target)) return;
		
		if (target instanceof Entity) {
			new AttackEffect((Entity) target, atkDamage);
		} else {
			target.takePhysicalDamage(atkDamage);
		}
		
		super.attack(target);
	}
	
	public void attack() {
		Direction d =getFacing();
		try {
			if (d == Direction.LEFT) {
				Entity e = Block.getBlock(getRow(), getCol()-1).getEntity();
				if (e instanceof CanTakePhysicalDamage) {
					attack((CanTakePhysicalDamage) e);
				}
			} else if (d == Direction.RIGHT) {
				Entity e = Block.getBlock(getRow(), getCol()+1).getEntity();
				if (e instanceof CanTakePhysicalDamage) {
					attack((CanTakePhysicalDamage) e);
				}
			} else if (d == Direction.UP) {
				Entity e = Block.getBlock(getRow()-1, getCol()).getEntity();
				if (e instanceof CanTakePhysicalDamage) {
					attack((CanTakePhysicalDamage) e);
				}
			} else if (d == Direction.DOWN) {
				Entity e = Block.getBlock(getRow()+1, getCol()).getEntity();
				if (e instanceof CanTakePhysicalDamage) {
					attack((CanTakePhysicalDamage) e);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			
		} catch (NullPointerException e) {
			
		}
	}
	
	public void bomb() {
		new BombEffect(getRow(),getCol(),bombDamage,bombRadius);
	}

	@Override
	public boolean canAttack(CanTakePhysicalDamage target) {
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
			//System.out.println("dsasdasad");
			//ee.getWith().kill();
			Entity target = ee.getWith();
			/*if (target instanceof CanTakePhysicalDamage && !(target instanceof Player)) {
				attack((CanTakePhysicalDamage) target);
			}*/
		}
	}
	
	public void moveLeft() {
		this.moveTo(getRow(), getCol()-1);
		//setFacing(Direction.LEFT);
	}
	
	public void moveRight() {
		this.moveTo(getRow(), getCol()+1);
		//setFacing(Direction.RIGHT);
	}
	
	public void moveUp() {
		this.moveTo(getRow()-1, getCol());
		//setFacing(Direction.UP);
	}
	
	public void moveDown() {
		this.moveTo(getRow()+1, getCol());
		//setFacing(Direction.DOWN);
	}
	
	@Override
	public boolean moveTo(int row,int col) {
		try {
			if (super.moveTo(row, col)) {
				try {
					Main.cameraController.performSetCenter();
				} catch (NullPointerException e) {
					
				}
				return true;
			} else {
				return false;
			}
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

	@Override
	public double getArmor() {
		// TODO Auto-generated method stub
		return armor;
	}

	@Override
	public void setArmor(double armor) {
		// TODO Auto-generated method stub
		this.armor = armor;
	}
	
	
	
	public double getAtkDamage() {
		return atkDamage;
	}

	public void setAtkDamage(double atkDamage) {
		this.atkDamage = atkDamage;
	}

	public double getBombDamage() {
		return bombDamage;
	}

	public void setBombDamage(double bombDamage) {
		this.bombDamage = bombDamage;
	}
	
	

	public int getBombRadius() {
		return bombRadius;
	}

	public void setBombRadius(int bombRadius) {
		this.bombRadius = bombRadius;
	}

	@Override
	public double takePhysicalDamage(double damage) {
		// TODO Auto-generated method stub
		damage -= armor;
		hp -= damage;
		
		if (!isAlive()) {
			kill();
		}
		
		return damage;
	}

	@Override
	//Bomb ignore player armor
	public double takeBombDamage(double damage) {
		// TODO Auto-generated method stub
		hp -= damage;
		
		if (!isAlive()) {
			kill();
		}
		
		return damage;
	}

}
