package entity.monster;

import java.util.Random;

import controller.EventController;
import entity.Player;
import entity.UnmoveableException;
import gui.Block;
import javafx.application.Platform;
import rule.ThreadRule;

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
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canAttack() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void render(Block b) {
		b.testLabel.setText("Z");
	}

}