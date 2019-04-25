package entity.monster;

import java.util.Random;

import controller.EventController;
import entity.Player;
import entity.UnmoveableException;
import gui.Block;
import javafx.application.Platform;
import rule.ThreadRule;

public class Zombie extends Monster {
	
	//public static int RADIUS = 15;
	
	public void handleUnmoveableException(UnmoveableException e) {
		
	}
	
	private ThreadRule<Integer> moveAI;
	
	public Zombie(int row,int col) {
		this.currRow = row;
		this.currCol = col;
		EventController.onLoad(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				moveAI = new ThreadRule<Integer>() {
					
					@Override
					public void onChange(Integer curr, Integer prev) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public Integer get() {
						// TODO Auto-generated method stub
						int rDist = Math.abs(Player.mainPlayer.getRow() - getRow());
						int cDist = Math.abs(Player.mainPlayer.getCol() - getCol());
						int distance = rDist + cDist;
						if (distance==0) return 0;
						int rDir = 0;
						int cDir = 0;
						
						if (Player.mainPlayer.getRow() < getRow()) rDir = -1;
						else if (Player.mainPlayer.getRow() > getRow()) rDir = 1;
						
						if (Player.mainPlayer.getCol() < getCol()) cDir = -1;
						else if (Player.mainPlayer.getCol() > getCol()) cDir = 1;
						
						int rand = (new Random()).nextInt(distance);
						
						try {
							if (rand < rDist) {
								moveTo(getRow()+rDir,getCol());
							} else {
								moveTo(getRow(),getCol()+cDir);
							}
						} catch (UnmoveableException e) {
							// TODO Auto-generated catch block
							handleUnmoveableException(e);
						}
						
						return null;
					}
				};
			}
		});
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
