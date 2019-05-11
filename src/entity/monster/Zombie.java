package entity.monster;

import controller.TimeController;
import effect.AttackEffect;
import entity.Attackable;
import entity.CanTakePhysicalDamage;
import entity.Direction;
import entity.Entity;
import entity.MoveCollideException;
import entity.UnmoveableException;
import entity.ore.Ore;
import gui.ImageStore;

public class Zombie extends EnemyMonster implements Attackable {
	
	//public static int RADIUS = 15;
	
	private double atkDamage = 5;
	
	
	
	public double getAtkDamage() {
		return atkDamage+TimeController.getCurrentTime()/15000;
	}

	public void setAtkDamage(double atkDamage) {
		this.atkDamage = atkDamage;
	}

	public Zombie(int row,int col) {
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

	@Override
	public boolean canAttack(CanTakePhysicalDamage target) {
		// TODO Auto-generated method stub
		return super.canAttack(target);
	}
	
	public void handleUnmoveableException(UnmoveableException e) {
		if (e instanceof MoveCollideException) {
			MoveCollideException ee = (MoveCollideException) e;
			//System.out.println("dsasdasad");
			//ee.getWith().kill();
			Entity target = ee.getWith();
			if (target instanceof CanTakePhysicalDamage && !(target instanceof Ore) && !(target instanceof EnemyMonster)) {
				attack((CanTakePhysicalDamage) target);
			}
		}
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		if (getFacing() == Direction.UP) return ImageStore.getInstance().zombieU;
		else if (getFacing() == Direction.DOWN) return ImageStore.getInstance().zombieD;
		else if (getFacing() == Direction.LEFT) return ImageStore.getInstance().zombieL;
		else if (getFacing() == Direction.RIGHT) return ImageStore.getInstance().zombieR;
		return ImageStore.getInstance().zombieU;
	}

	@Override
	public int getExpGived() {
		// TODO Auto-generated method stub
		return 1;
	}

}
