package entity;

import controller.EventController;
import effect.Effect;
import gui.Block;
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
		            "-fx-background-size: 100%, 100%;"
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
		this.currRow = row;
	}
	
	public void setCol(int col) {
		this.currCol = col;
	}
	
	public boolean getIsAlive() {
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
					Block.getBlock(getRow(), getCol()).removeEffect((Effect) thiss); 
				}
			});
			
		}
	}
	
	public Block getBlock() {
		return Block.getBlock(getRow(), getCol());
	}
}
