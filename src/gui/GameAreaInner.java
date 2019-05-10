package gui;

import java.util.HashMap;

import application.Main;
import controller.EventController;
import effect.TestEffect;
import entity.Player;
import entity.monster.Zombie;
import entity.ore.Coal;
import entity.ore.Diamond;
import entity.ore.Iron;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

public class GameAreaInner extends GridPane
{

	public static int NUM_COL = 100;
	public static int NUM_ROW = 100;
	
	public static int VIEW_COL = 20;
	public static int VIEW_ROW = 20;
	
	private HashMap<Integer, HashMap<Integer, Block>> blocks = new HashMap<Integer, HashMap<Integer, Block>>();
	private HashMap<Integer, HashMap<Integer, BlockView>> block_views = new HashMap<Integer, HashMap<Integer, BlockView>>();
	private boolean[][] isDiamondOre = new boolean[NUM_ROW][NUM_COL];
	private boolean[][] isIronOre = new boolean[NUM_ROW][NUM_COL];
	private boolean[][] isCoalOre = new boolean[NUM_ROW][NUM_COL];
	private int dirI[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private int dirJ[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public GameAreaInner()
	{
		super();
		// Block b1 = new Block();
		
		GameAreaInner thiss =this;
		
		//this.setPrefWidth(NUM_COL * Block.WIDTH);
		//this.setPrefHeight(NUM_ROW * Block.HEIGHT);
		this.setWidth(NUM_COL * Block.WIDTH);
		this.setHeight(NUM_ROW * Block.HEIGHT);

		for (int i = 0; i < NUM_COL; i++)
		{
			for (int j = 0; j < NUM_ROW; j++)
			{
				Block block = new Block(j, i);
				if (i == 0 && j == 0)
				{

					block.setEntity(Player.mainPlayer);
				}
				if (i == 5 && j == 5)
				{
					block.setEntity(new Zombie(j, i));
				}
				if (!blocks.containsKey(j))
					blocks.put(j, new HashMap());
				blocks.get(j).put(i, block);
				//this.add(block, i, j);
				
			}
		}
		
		EventController.onLoad(new Runnable()
		{

			@Override
			public void run() {
		
				for (int i = 0; i < VIEW_COL; i++)
				{
					for (int j = 0; j < VIEW_ROW; j++)
					{
						BlockView bv = new BlockView(j,i);
						if (i>=0 && j>=0) bv.setBlock(Block.getBlock(j, i),j,i);
						if (!block_views.containsKey(j))
							block_views.put(j, new HashMap());
						block_views.get(j).put(i, bv);
						thiss.add(bv, i, j);
					}
				}
			}
		});
		

		Platform.runLater(new Runnable()
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Player.mainPlayer.moveDown();

			}
		});

		new TestEffect(0, 0);

		EventController.onLoad(new Runnable()
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < NUM_ROW; i++)
					for (int j = 0; j < NUM_COL; j++)
						isDiamondOre[i][j] = isIronOre[i][j] = isCoalOre[i][j] = false;

				// Gen Diamond Ore
				for (int i = 0; i < NUM_ROW; i += 25)
				{
					for (int j = 0; j < NUM_COL; j += 25)
					{
						int nowRow = Main.random.nextInt(25) + i;
						int nowCol = Main.random.nextInt(25) + j;
						isDiamondOre[nowRow][nowCol] = true;

						for (int k = 0; k < 8; k++)
						{
							int nextRow = nowRow + dirI[k];
							int nextCol = nowCol + dirJ[k];
							if (nextRow < 0 || nextCol < 0 || nextRow >= NUM_ROW || nextCol >= NUM_COL)
								continue;
							if (Main.random.nextInt(2) == 1)
								isDiamondOre[nextRow][nextCol] = true;
							else
								isDiamondOre[nextRow][nextCol] |= false;
						}
					}
				}

				// Gen Iron Ore
				for (int i = 0; i < NUM_ROW; i += 20)
				{
					for (int j = 0; j < NUM_COL; j += 20)
					{
						int nowRow = Main.random.nextInt(20) + i;
						int nowCol = Main.random.nextInt(20) + j;
						isIronOre[nowRow][nowCol] = true;

						int temp = Main.random.nextInt(8);
						while (nowRow + dirI[temp] < 0 || nowRow + dirI[temp] >= NUM_ROW || nowCol + dirJ[temp] < 0
								|| nowCol + dirJ[temp] >= NUM_COL)
							temp = Main.random.nextInt(8);
						int tempRow = nowRow + dirI[temp];
						int tempCol = nowCol + dirJ[temp];
						isIronOre[tempRow][tempCol] = true;

						for (int k = 0; k < 8; k++)
						{
							int nextRow = nowRow + dirI[k];
							int nextCol = nowCol + dirJ[k];
							if (nextRow < 0 || nextCol < 0 || nextRow >= NUM_ROW || nextCol >= NUM_COL)
								continue;
							if (Main.random.nextInt(2) == 1)
								isIronOre[nextRow][nextCol] = true;
							else
								isIronOre[nextRow][nextCol] |= false;
						}

						for (int k = 0; k < 8; k++)
						{
							int nextRow = tempRow + dirI[k];
							int nextCol = tempCol + dirJ[k];
							if (nextRow < 0 || nextCol < 0 || nextRow >= NUM_ROW || nextCol >= NUM_COL)
								continue;
							if (Main.random.nextInt(2) == 1)
								isIronOre[nextRow][nextCol] = true;
							else
								isIronOre[nextRow][nextCol] |= false;
						}

						nowRow = Main.random.nextInt(20) + i;
						nowCol = Main.random.nextInt(20) + j;
						isIronOre[nowRow][nowCol] = true;

						temp = Main.random.nextInt(8);
						while (nowRow + dirI[temp] < 0 || nowRow + dirI[temp] >= NUM_ROW || nowCol + dirJ[temp] < 0
								|| nowCol + dirJ[temp] >= NUM_COL)
							temp = Main.random.nextInt(8);
						tempRow = nowRow + dirI[temp];
						tempCol = nowCol + dirJ[temp];
						isIronOre[tempRow][tempCol] = true;

						for (int k = 0; k < 8; k++)
						{
							int nextRow = nowRow + dirI[k];
							int nextCol = nowCol + dirJ[k];
							if (nextRow < 0 || nextCol < 0 || nextRow >= NUM_ROW || nextCol >= NUM_COL)
								continue;
							if (Main.random.nextInt(2) == 1)
								isIronOre[nextRow][nextCol] = true;
							else
								isIronOre[nextRow][nextCol] |= false;
						}

						for (int k = 0; k < 8; k++)
						{
							int nextRow = tempRow + dirI[k];
							int nextCol = tempCol + dirJ[k];
							if (nextRow < 0 || nextCol < 0 || nextRow >= NUM_ROW || nextCol >= NUM_COL)
								continue;
							if (Main.random.nextInt(2) == 1)
								isIronOre[nextRow][nextCol] = true;
							else
								isIronOre[nextRow][nextCol] |= false;
						}
					}
				}

				// Gen Coal Ore
				for (int i = 0; i < NUM_ROW; i += 10)
				{
					for (int j = 0; j < NUM_COL; j += 10)
					{
						int nowRow = Main.random.nextInt(10) + i;
						int nowCol = Main.random.nextInt(10) + j;
						isCoalOre[nowRow][nowCol] = true;

						for (int k = 0; k < 8; k++)
						{
							int nextRow = nowRow + dirI[k];
							int nextCol = nowCol + dirJ[k];
							if (nextRow < 0 || nextCol < 0 || nextRow >= NUM_ROW || nextCol >= NUM_COL)
								continue;
							if (Main.random.nextInt(2) == 1)
								isCoalOre[nextRow][nextCol] = true;
							else
								isCoalOre[nextRow][nextCol] |= false;
						}
					}
				}

				// Display it
				for (int i = 0; i < NUM_ROW; i++)
				{
					for (int j = 0; j < NUM_COL; j++)
					{
						if (isDiamondOre[i][j])
							new Diamond(i, j);
						else if (isIronOre[i][j])
							new Iron(i, j);
						else if (isCoalOre[i][j])
							new Coal(i, j);
					}
				}

			}
		});

		// this.setPrefWidth(270);
	}

	public HashMap<Integer, HashMap<Integer, Block>> getBlocks() {
		return blocks;
	}
	
	public HashMap<Integer, HashMap<Integer, BlockView>> getBlockViews() {
		return block_views;
	}

}
