package entity;

import java.util.ArrayList;

import application.Main;
import effect.AttackEffect;
import effect.BombEffect;
import entity.monster.EnemyMonster;
import entity.monster.Monster;
import gui.Block;
import gui.ImageStore;
import item.Crafter;
import item.Inventory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Player extends Monster implements HasHP, HasArmor, CanTakePhysicalDamage, CanTakeBombDamage, HasInventory, HasLevel, Attackable {
	
	public static int START_ROW = 50;
	public static int START_COL = 50;
	
	public static Player mainPlayer = new Player(START_ROW,START_COL);
	private Direction facing = Direction.UP;
	
	
	private double hp = 100;
	private double maxhp = 100;
	private double regenhp = 0;
	private double armor = 0;
	private double atkDamage = 5;
	private double bombDamage = 10;
	private int bombRadius = 3;
	private Inventory inventory = new Inventory();
	private Crafter crafter = new Crafter(inventory);
	private int level = 1;
	private static int exptable[] = new int[100];
	private int exp = 0;
	
	static {
		exptable[0] = 1;
		exptable[1] = 1;
		for(int i = 2;i<100;i++) {
			exptable[i] = exptable[i-1] + exptable[i-2];
		}
	}
	
	
	
	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getCurrentLevelEXPNeeded() {
		return exptable[getLevel()];
	}
	
	public void receiveEXP(int exp) {
		setExp(getExp()+exp);
		while (getExp() >= getCurrentLevelEXPNeeded()) {
			setLevel(getLevel()+1);
			setExp(getExp() - exptable[getLevel()-1]);
			setHP(getHP()+10);
		}
		System.out.println(getExp());
		System.out.println(getLevel());
		Main.controlPanel.update();
	}
	
	



	public int getExp() {
		return exp;
	}



	public void setExp(int exp) {
		this.exp = exp;
	}



	public Player(int row,int col) {
		super(row,col);
		
		Timeline regenHP = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
			setHP(Math.min(getHP()+getRegenhp(), getMaxhp()));
			System.out.println(getHP());
		}));
		regenHP.setCycleCount(Timeline.INDEFINITE);
		regenHP.play();
	}
	
	

	public double getMaxhp() {
		return maxhp + (getLevel()-1)*10;
	}



	public void setMaxhp(double maxhp) {
		this.maxhp = maxhp;
		Main.controlPanel.update();
	}



	public double getRegenhp() {
		return regenhp + (getLevel()-1)*0.25;
	}



	public void setRegenhp(double regenhp) {
		this.regenhp = regenhp;
	}



	@Override
	public void attack(CanTakePhysicalDamage target) {
		// TODO Auto-generated method stub
		if (!canAttack(target)) return;
		
		if (target instanceof Entity) {
			new AttackEffect((Entity) target, getAtkDamage(), this);
		} else {
			target.takePhysicalDamage(getAtkDamage());
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
	
	private Thread bombThrottle = null;
	
	public void bomb() {
		if (bombThrottle == null || !bombThrottle.isAlive()) {
			new BombEffect(getRow(),getCol(),bombDamage,bombRadius,this);
			bombThrottle = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			bombThrottle.start();
		}
	}

	@Override
	public boolean canAttack(CanTakePhysicalDamage target) {
		// TODO Auto-generated method stub
		return super.canAttack(target);
	}

	@Override
	public String getIcon() {
		if (getFacing() == Direction.UP) return ImageStore.getInstance().playerU;
		else if (getFacing() == Direction.DOWN) return ImageStore.getInstance().playerD;
		else if (getFacing() == Direction.LEFT) return ImageStore.getInstance().playerL;
		else if (getFacing() == Direction.RIGHT) return ImageStore.getInstance().playerR;
		return ImageStore.getInstance().playerU;
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
		if (getInventory().getBootLevel() == 0) return 400;
		if (getInventory().getBootLevel() == 1) return 350;
		if (getInventory().getBootLevel() == 2) return 300;
		return 500;
	}

	public Direction getFacing() {
		return facing;
	}

	public void setFacing(Direction facing) {
		this.facing = facing;
		this.render(getBlock());
	}
	
	public ArrayList<EnemyMonster> getSurroundingEnemies() {
		ArrayList<EnemyMonster> res = new ArrayList<EnemyMonster>();
		
		Player p = this;
		int sr = p.getRow();
		int sc = p.getCol();
		
		for(int i = sr-15;i<=sr+15;i++) {
			for (int j = sc-15;j<=sc+15;j++) {
				//if (i<0 || j<0 || i>=GameAreaInner.NUM_ROW || j>=GameAreaInner.NUM_COL) continue;
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
		return armor + getLevel()-1 + getInventory().getArmorLevel()*exptable[getInventory().getArmorLevel()+2] + getInventory().getHelmetLevel()*exptable[getInventory().getHelmetLevel()] + getInventory().getBootLevel() + getInventory().getPantLevel() ;
	}

	@Override
	public void setArmor(double armor) {
		// TODO Auto-generated method stub
		this.armor = armor;
	}
	
	
	
	public double getAtkDamage() {
		return atkDamage + getLevel()-1 + getInventory().getSwordLevel()*exptable[getInventory().getSwordLevel()+2];
	}

	public void setAtkDamage(double atkDamage) {
		this.atkDamage = atkDamage;
	}

	public double getBombDamage() {
		return bombDamage + getLevel()-1;
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
		damage -= getArmor();
		setHP(getHP()-damage);
		
		System.out.println(getHP());
		
		if (!isAlive()) {
			Main.endGame();
			kill();
		}
		
		return damage;
	}

	@Override
	//Bomb ignore player armor
	public double takeBombDamage(double damage) {
		// TODO Auto-generated method stub
		setHP(getHP()-damage);
		
		System.out.println(getHP());
		
		if (!isAlive()) {
			System.out.println("player dead");
			Main.endGame();
			kill();
		}
		
		return damage;
	}

	public double getHP() {
		return hp;
	}
	public void setHP(double hp) {
		this.hp = hp;
		try {
			Main.controlPanel.update();
		} catch (Exception e) {}
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}
	
	public Crafter getCrafter() {
		// TODO Auto-generated method stub
		return crafter;
	}
	
}
