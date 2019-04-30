package gui;

import java.util.HashMap;
import java.util.Random;

import application.Main;
import controller.EventController;
import effect.TestEffect;
import entity.Player;
import entity.monster.Zombie;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

public class GameAreaInner extends GridPane {
	
	public static int NUM_COL = 50;
	public static int NUM_ROW = 50;
	private HashMap<Integer, HashMap<Integer, Block>> blocks = new HashMap<Integer, HashMap<Integer,Block>>();
	
	public GameAreaInner() {
		super();
		//Block b1 = new Block();
		
		for(int i = 0;i<NUM_COL;i++) {
			for (int j = 0;j<NUM_ROW;j++) {
				Block block = new Block(j,i);
				if (i==0 && j==0) {
					
					block.setEntity(Player.mainPlayer);
				}
				if (i==5 && j==5) {
					block.setEntity(new Zombie(j,i));
				}
				if (!blocks.containsKey(j)) blocks.put(j, new HashMap());
				blocks.get(j).put(i, block);
				this.add(block, i, j);
			}
		}
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Player.mainPlayer.moveDown();
				
			}
		});
		
		new TestEffect(0,0);

		EventController.onLoad(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				for(int i = 0;i<NUM_ROW;i++) {
					for (int j = 0;j<NUM_COL;j++) {
						if (!Block.getBlock(i, j).hasEntity()) {
							if (Main.random.nextInt(100) < 50) {
								new Zombie(i,j);
							}
						}
					}
				}
				
				
			}
		});

		
		//this.setPrefWidth(270);
	}

	public HashMap<Integer, HashMap<Integer, Block>> getBlocks() {
		return blocks;
	}
	
	
}
