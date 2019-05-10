package entity.monster;

import entity.Attackable;
import entity.CanTakePhysicalDamage;
import entity.Entity;
import entity.HasHP;
import entity.MoveCollideException;
import entity.MoveOutOfBoundException;
import entity.Moveable;
import entity.UnmoveableException;
import gui.Block;
import gui.GameAreaInner;
import javafx.application.Platform;
import rule.ThreadRule;

public abstract class Monster extends Entity implements Moveable, Attackable, HasHP, CanTakePhysicalDamage {
	
	protected int oldRow = -1;
	protected int oldCol = -1;
	
	private Thread moveThrottle = null;
	private ThreadRule<Boolean> bouncer;
	private Thread attackThrottle = null;
	
	private double hp = 10;
	
	public Monster(int row,int col) {
		super(row,col);
		Monster thiss = this;
		/*bouncer = new ThreadRule<Boolean>() {
			
			@Override
			public void onChange(Boolean curr, Boolean prev) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Boolean get() {
				// TODO Auto-generated method stub
				Block.getBlock(oldRow, oldCol).setEntity(thiss);
				return null;
			}
		};*/
	}
	
	public boolean canMoveTo(int row,int col) throws UnmoveableException {
		if (row < 0 || row >= GameAreaInner.NUM_ROW || col < 0 || col >= GameAreaInner.NUM_COL) {
			throw new MoveOutOfBoundException();
		}
		
		if (Block.getBlock(row, col).hasEntity()) {
			//System.out.println("sdasda");
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
		
		Monster thiss = this;
		
		
		if (moveThrottle == null || !moveThrottle.isAlive()) {
			oldRow = currRow;
			oldCol = currCol;
			
			currRow = row;
			currCol = col;
			
			Block.getBlock(oldRow, oldCol).removeEntity();
			Block.getBlock(currRow, currCol).setEntity(thiss);
			
			
			moveThrottle = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//Block.getBlock(oldRow, oldCol).removeEntity();
								//Block.getBlock(currRow, currCol).setEntity(thiss);
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
	
	public int getAttackDelay() {
		return 250;
	}
	
	
	public double takePhysicalDamage(double damage) {
		setHP(getHP()-damage);
		
		System.out.println(getHP());
		
		if (!isAlive()) {
			kill();
		}
		
		return damage;
	}
	
	public double getHP() {
		return hp;
	}
	public void setHP(double hp) {
		this.hp = hp;
	}
	public boolean isAlive() {
		return getHP()>0.0;
	}
	
	@Override
	public void attack(CanTakePhysicalDamage target) {
		// TODO Auto-generated method stub
		if (!canAttack(target)) return;
			
		attackThrottle = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(getAttackDelay());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		});
		attackThrottle.start();
		
		moveThrottle = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							//Block.getBlock(oldRow, oldCol).removeEntity();
							//Block.getBlock(currRow, currCol).setEntity(thiss);
						}
					});

					Thread.sleep(getAttackDelay());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		});
		moveThrottle.start();
	}

	@Override
	public boolean canAttack(CanTakePhysicalDamage target) {
		// TODO Auto-generated method stub
		//System.out.println("xxx");
		//if (attackThrottle!=null) System.out.println(attackThrottle.isAlive());
		return (attackThrottle == null || !attackThrottle.isAlive()) && (moveThrottle == null || !moveThrottle.isAlive());
	}

}
