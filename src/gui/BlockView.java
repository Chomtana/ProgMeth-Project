package gui;

import application.Main;
import javafx.scene.layout.StackPane;

public class BlockView extends StackPane {
	private Block block = null;
	private int realRow,realCol;
	
	public BlockView() {
		this.setPrefWidth(Block.WIDTH);
		this.setPrefHeight(Block.HEIGHT);
	}
	
	public BlockView(int realrow,int realcol) {
		this();
		setRealCol(realcol);
		setRealRow(realrow);
	}
	
	private void render() {
		while (this.getChildren().size() > 0) {
			this.getChildren().remove(0);
		}
		if (block==null) {
			
		} else {
			this.getChildren().add(block);
		}
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
		render();
	}
	
	public void setBlock(Block block, int realrow,int realcol) {
		this.block = block;
		setRealCol(realcol);
		setRealRow(realrow);
		render();
	}
	
	public static BlockView getBlockView(int row,int col) {
		return Main.gameArea.getInner().getBlockViews().get(row).get(col);
	}

	public int getRealRow() {
		return realRow;
	}

	public void setRealRow(int realRow) {
		this.realRow = realRow;
	}

	public int getRealCol() {
		return realCol;
	}

	public void setRealCol(int realCol) {
		this.realCol = realCol;
	}
	
	
}
