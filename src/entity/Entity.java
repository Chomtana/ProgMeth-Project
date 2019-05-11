package entity;

import controller.EventController;
import effect.Effect;
import gui.Block;
import gui.GameAreaInner;
import gui.ImageStore;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public abstract class Entity {
	protected boolean isAlive = true;
	
	protected int currRow;
	protected int currCol;
	
	public Entity() {
		Entity thiss = this;
		if (!(thiss instanceof Effect)) {
			EventController.onLoad(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Block.getBlock(getRow(), getCol()).setEntity(thiss);
				}
			});
		};
		
	}

	public Entity(int row,int col) {
		setRow(row);
		setCol(col);
		//if (! (this instanceof NullEntity))
		//System.out.println("ROW "+getRow()+"COL "+getCol());
		Entity thiss = this;
		if (!(thiss instanceof Effect)) {
			EventController.onLoad(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//System.out.println(getRow());
					//System.out.println(getCol());
					Block.getBlock(getRow(), getCol()).setEntity(thiss);
				}
			});
		}
	}
	
	public abstract String getIcon();
	public void render(Block block) {
		block.testLabel.setText("");
		//block.setBackground(new Background(new BackgroundImage(new Image(ImageStore.getInstance().blockBG), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		String icon = getIcon();
		if (icon != null && icon != "") {
			block.setStyle(
		            "-fx-background-image: url(" +
		            		icon +
		            "); " +
		            "-fx-background-size: 100%, 100%;"+
		            (this instanceof Effect ? "-fx-opacity: 0.6;" : "")
		        );
		} else {
			block.setStyle("-fx-background: none;");
		}
	};
	public int getRow() {
		return currRow;
	}
	
	public int getCol() {
		return currCol;
	}
	public void setRow(int row) {
		if (row >= GameAreaInner.NUM_ROW) {
			row = row - GameAreaInner.NUM_ROW;
		} else if (row < 0) {
			row = row + GameAreaInner.NUM_ROW;
		}
		this.currRow = row;
	}
	
	public void setCol(int col) {
		if (col >= GameAreaInner.NUM_COL) {
			col = col - GameAreaInner.NUM_COL;
		} else if (col < 0) {
			col = col + GameAreaInner.NUM_COL;
		}
		this.currCol = col;
	}
	
	public int distRowTo(Entity target) {
		return Math.min(Math.abs(getRow()-target.getRow()), Math.abs(Math.abs(getRow()-target.getRow())-GameAreaInner.NUM_ROW));
	}
	
	public int distColTo(Entity target) {
		return Math.min(Math.abs(getCol()-target.getCol()), Math.abs(Math.abs(getCol()-target.getCol())-GameAreaInner.NUM_COL));
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void kill() {
		isAlive = false;
		Entity thiss = this;
		if (!(this instanceof Effect)) {
			Block.getBlock(getRow(), getCol()).removeEntity();
		} else {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Block.getBlock(getRow(), getCol()).removeEffect((Effect) thiss); 
					} catch (NullPointerException e) {
						
					}
				}
			});
			
		}
	}
	
	public Block getBlock() {
		return Block.getBlock(getRow(), getCol());
	}
}
