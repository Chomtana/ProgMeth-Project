package entity.monster;

import java.util.Random;

import controller.EventController;
import entity.HasHP;
import entity.Player;
import entity.UnmoveableException;
import gui.Block;
import javafx.application.Platform;
import rule.ThreadRule;

public class Boomer extends EnemyMonster {
	
	//public static int RADIUS = 15;
	
	
	
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
	public void attack(HasHP target) {
		// TODO Auto-generated method stub
		
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
		b.testLabel.setText("B");
	}

}
