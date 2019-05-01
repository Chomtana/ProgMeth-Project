package controller;

import java.util.ArrayList;
import java.util.Random;

import application.Main;
import entity.Player;
import entity.monster.Boomer;
import entity.monster.EnemyMonster;
import entity.monster.Zombie;
import gui.Block;
import gui.GameAreaInner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import rule.ThreadRule;

public class SpawnController {
	public static double spawnDelayConstant = 100000;
	public static double spawnDelayStart = 5000;
	public static int spawnRadius = 6;
	
	/*private ThreadRule<Boolean> spawnRule = new ThreadRule<Boolean>() {
		
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
			

			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	};*/
	
	void doMoveAI() {
		ArrayList<EnemyMonster> enemies = Player.mainPlayer.getSurroundingEnemies();
		if (enemies.size() <= 0) return;
		EnemyMonster e = enemies.get((new Random()).nextInt(enemies.size()));
		/*Player p = Player.mainPlayer;
		int sr = p.getRow();
		int sc = p.getCol();
		
		for(int i = sr-spawnRadius;i<=sr+spawnRadius;i++) {
			for (int j = sc-spawnRadius;j<=sc+spawnRadius;j++) {
				if (i<0 || j<0 || i>=GameAreaInner.NUM_ROW || j>=GameAreaInner.NUM_COL) continue;
				Entity ee = Block.getBlock(i, j).getEntity();
				if (!(ee instanceof EnemyMonster)) continue;
				EnemyMonster e = (EnemyMonster) ee;*/
				
				int rDist = Math.abs(Player.mainPlayer.getRow() - e.getRow());
				int cDist = Math.abs(Player.mainPlayer.getCol() - e.getCol());
				int distance = rDist + cDist;
				if (distance==0) return;
				int rDir = 0;
				int cDir = 0;
				
				if (Player.mainPlayer.getRow() < e.getRow()) rDir = -1;
				else if (Player.mainPlayer.getRow() > e.getRow()) rDir = 1;
				
				if (Player.mainPlayer.getCol() < e.getCol()) cDir = -1;
				else if (Player.mainPlayer.getCol() > e.getCol()) cDir = 1;
				
				int rand = (new Random()).nextInt(distance);
		
				if (rand < rDist) {
					e.moveTo(e.getRow()+rDir,e.getCol());
				} else {
					e.moveTo(e.getRow(),e.getCol()+cDir);
				}
				
				/*return;
			}
		}*/
	}
	
	public SpawnController() {
		Timeline moveAI = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			doMoveAI();
		}));
		moveAI.setCycleCount(Timeline.INDEFINITE);
		moveAI.play();
		
		Timeline spawnAI = new Timeline(new KeyFrame(Duration.millis(spawnDelayStart), e -> {
			spawn();
		}));
		spawnAI.setCycleCount(Timeline.INDEFINITE);
		spawnAI.play();
	}
	
	private void spawn() {
		Player p = Player.mainPlayer;
		int sr = p.getRow();
		int sc = p.getCol();
		Random random = Main.random;
		System.out.println("Spawn");
		
		int loopcount = 100;
		
		while(loopcount-- > 0) {
			int r = -1;
			int c = -1;
			while (r<0 || c<0 || r>=GameAreaInner.NUM_ROW || c>=GameAreaInner.NUM_COL) {
				r = sr + random.nextInt(spawnRadius*2+1)-spawnRadius;
				c = sc + random.nextInt(spawnRadius*2+1)-spawnRadius;
			}
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
