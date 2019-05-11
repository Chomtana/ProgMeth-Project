package entity.monster;

import entity.CanTakePhysicalDamage;
import entity.Direction;
import gui.Block;
import gui.ImageStore;

public class Zombie extends EnemyMonster {
	
	//public static int RADIUS = 15;
	
	
	
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
		super.attack(target);
	}

	@Override
	public boolean canAttack(CanTakePhysicalDamage target) {
		// TODO Auto-generated method stub
		return super.canAttack(target);
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

}
