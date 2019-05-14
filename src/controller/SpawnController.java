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

public class SpawnController {
	public static double spawnDelayStart = 5000;
	public static int spawnRadius = 6;
	
	private void doMoveAI() {
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
				
				int rDist = Player.mainPlayer.distRowTo(e);
				int cDist = Player.mainPlayer.distColTo(e);
				int rDistRaw = Math.abs(Player.mainPlayer.getRow()-e.getRow());
				int cDistRaw = Math.abs(Player.mainPlayer.getCol()-e.getCol());
				int distance = rDist + cDist;
				if (distance==0) return;
				int rDir = 0;
				int cDir = 0;
				
				if (Player.mainPlayer.getRow() < e.getRow()) rDir = -1;
				else if (Player.mainPlayer.getRow() > e.getRow()) rDir = 1;
				
				if (rDistRaw > rDist) rDir = -rDir;
				
				if (Player.mainPlayer.getCol() < e.getCol()) cDir = -1;
				else if (Player.mainPlayer.getCol() > e.getCol()) cDir = 1;
				
				if (cDistRaw > cDist) cDir = -cDir;
				
				int rand = (new Random()).nextInt(distance);
		
				if (rand < rDist) {
					//e.moveTo(e.getRow()+rDir,e.getCol());
					if (rDir < 0) {
						e.moveUp();
					} else {
						e.moveDown();
					}
				} else {
					//e.moveTo(e.getRow(),e.getCol()+cDir);
					if (cDir < 0) {
						e.moveLeft();
					} else {
						e.moveRight();
					}
				}
				
				/*return;
			}
		}*/
	}
	
	public SpawnController() {
		Timeline moveAI = new Timeline(new KeyFrame(Duration.millis(200), e -> {
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
		
		ArrayList<EnemyMonster> enemies = Player.mainPlayer.getSurroundingEnemies();
		if (enemies.size()>=5) {
			return;
		}
		
		int loopcount = 10;
		
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
