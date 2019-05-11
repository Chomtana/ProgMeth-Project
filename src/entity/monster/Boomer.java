package entity.monster;

import effect.FireEffect;
import entity.CanTakePhysicalDamage;
import entity.Direction;
import entity.Player;
import gui.Block;
import gui.ImageStore;
import javafx.application.Platform;

public class Boomer extends EnemyMonster {
	
	//public static int RADIUS = 15;
	
	private double bombDmg = 20;
	private int bombRadius = 2;
	private Thread bombThrottle = null;
	
	public Boomer(int row,int col) {
		super(row,col);
	}
	
	/*private ThreadRule<Boolean> isMovingToPlayer = new ThreadRule<Boolean>() {
		
		@Override
		public void onChange(Boolean curr, Boolean prev) {
			// TODO Auto-generated method stub
			if (curr.equals(false)) {
				moveAI.kill();
			} else {
				moveAI.restart();
			}
		}
		
		@Override
		public Boolean get() {
			return Math.abs(Player.mainPlayer.getRow() - getRow()) <= RADIUS || Math.abs(Player.mainPlayer.getCol() - getCol()) <= RADIUS;
		}
	};*/
	
	@Override
	public boolean moveTo(int row,int col) {
		boolean res = super.moveTo(row, col);
		Boomer thiss = this;
		if (res) {
			if (bombThrottle != null && bombThrottle.isAlive()) return res;
			boolean inradius = Math.abs(Player.mainPlayer.getRow() - getRow()) <= bombRadius && Math.abs(Player.mainPlayer.getCol() - getCol()) <= bombRadius;
			if (inradius) {
				bombThrottle = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
						boolean stillinradius = Math.abs(Player.mainPlayer.getRow() - getRow()) <= bombRadius && Math.abs(Player.mainPlayer.getCol() - getCol()) <= bombRadius;
						if (stillinradius && thiss.isAlive()) {
							for(int i = getRow()-bombRadius ; i<= getRow()+bombRadius ; i++) {
								for (int j = getCol()-bombRadius ; j <= getCol()+bombRadius; j++) {
									final int ii = i;
									final int jj = j;
									Platform.runLater(new Runnable() {
										
										@Override
										public void run() {
											// TODO Auto-generated method stub
											new FireEffect(ii,jj,bombDmg,thiss);
										}
									});
									
								}
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									setHP(0);
									kill();
								}
							});

						}
					}
				});
				bombThrottle.start();
			}
		}
		return res;
	}

	@Override
	//cannot do physical attack
	public void attack(CanTakePhysicalDamage target) {
		// TODO Auto-generated method stub
		//super.attack(target);
		
	}

	@Override
	public boolean canAttack(CanTakePhysicalDamage target) {
		// TODO Auto-generated method stub
		return super.canAttack(target);
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		if (getFacing() == Direction.UP) return ImageStore.getInstance().boomerU;
		else if (getFacing() == Direction.DOWN) return ImageStore.getInstance().boomerD;
		else if (getFacing() == Direction.LEFT) return ImageStore.getInstance().boomerL;
		else if (getFacing() == Direction.RIGHT) return ImageStore.getInstance().boomerR;
		return ImageStore.getInstance().boomerU;
	}

}
