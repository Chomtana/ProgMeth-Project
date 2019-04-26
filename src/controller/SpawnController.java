package controller;

import java.util.Random;

import entity.Entity;
import entity.Player;
import entity.monster.Boomer;
import entity.monster.EnemyMonster;
import entity.monster.Zombie;
import gui.Block;
import gui.GameAreaInner;
import javafx.application.Platform;
import rule.ThreadRule;

public class SpawnController {
	public static double spawnDelayConstant = 100000;
	public static double spawnDelayStart = 5000;
	public static int spawnRadius = 6;
	
	private ThreadRule<Boolean> spawnRule = new ThreadRule<Boolean>() {
		
		@Override
		public void onChange(Boolean curr, Boolean prev) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Boolean get() {
			// TODO Auto-generated method stub
			spawn();
			try {
				Thread.sleep(spawnDelay.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return null;
			}
			return null;
		}
	};
	private ThreadRule<Long> spawnDelay = new ThreadRule<Long>() {
		
		@Override
		public void onChange(Long curr, Long prev) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Long get() {
			// TODO Auto-generated method stub
			return Math.round(spawnDelayStart/Math.exp(TimeController.getCurrentTime()/spawnDelayConstant));
		}
	};
	
	private ThreadRule<Boolean> moveAI = new ThreadRule<Boolean>() {
		
		@Override
		public void onChange(Boolean curr, Boolean prev) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Boolean get() {
			// TODO Auto-generated method stub
			
			Player p = Player.mainPlayer;
			int sr = p.getRow();
			int sc = p.getCol();
			
			for(int i = sr-spawnRadius;i<=sr+spawnRadius;i++) {
				for (int j = sc-spawnRadius;j<=sc+spawnRadius;j++) {
					if (i<0 || j<0 || i>=GameAreaInner.NUM_ROW || j>=GameAreaInner.NUM_COL) continue;
					Entity b = Block.getBlock(i,j).getEntity();
					if (b instanceof EnemyMonster) {
						((EnemyMonster) b).moveAI();
						return null;
					}
					
				}
			}
			return null;
		}
	};
	
	public SpawnController() {
		
	}
	
	private void spawn() {
		Player p = Player.mainPlayer;
		int sr = p.getRow();
		int sc = p.getCol();
		Random random = new Random();
		System.out.println("Spawn");
		
		int loopcount = 100;
		
		while(loopcount-- > 0) {
			int r = random.nextInt(spawnRadius*2+1)-spawnRadius;
			int c = random.nextInt(spawnRadius*2+1)-spawnRadius;
			if (r<0 || c<0 || r>=GameAreaInner.NUM_ROW || c>=GameAreaInner.NUM_COL) continue;
			if (Block.getBlock(r, c).hasEntity()) {
				continue;
			}
			System.out.println("Row "+r+" Col "+c);
			if (random.nextInt(100)<50) {
				new Zombie(r, c);
			} else {
				new Boomer(r, c);
			}
			break;
		}
		
		/*for(int i = sr-spawnRadius;i<=sr+spawnRadius;i++) {
			for (int j = sc-spawnRadius;j<=sc+spawnRadius;j++) {
				if (i<0 || j<0 || i>=GameAreaInner.NUM_ROW || j>=GameAreaInner.NUM_COL) continue;
				//System.out.println("Spawn Inner");

			}
		}*/
	}
}
