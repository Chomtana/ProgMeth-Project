package gui;

import application.Main;
import effect.Effect;
import entity.Entity;
import entity.NullEntity;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Block extends StackPane {
	public static int WIDTH = 50;
	public static int HEIGHT = 50;
	
	private Entity entity;
	
	public Label testLabel = new Label();
	
	private int row;
	private int col;
	
	private boolean hasentity = false;
	
	public Block(int row,int col) {
		//this.setStyle("-fx-border-width: 1px;");
		//this.setStyle("-fx-border-color: blue;");
		this.setPrefWidth(WIDTH);
		this.setPrefHeight(HEIGHT);
		
		setEntity(null);
		
		testLabel.setTextFill(Color.web("#FFFFFF"));
		
		this.getChildren().add(testLabel);
		
		this.row = row;
		this.col = col;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
		if (entity != null) {
			this.entity.render(this);
			//this.hasentity = true;
		} else {
			this.entity = new NullEntity(getRow(),getCol());
			this.entity.render(this);
			//this.hasentity = false;
		}
	}
	
	public void removeEntity() {
		setEntity(null);
		//this.hasentity = false;
	}
	
	public boolean hasEntity() {
		return entity!=null && !(entity instanceof NullEntity);
		//return hasentity;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setIcon(String icon) {
		this.setBackground(new Background(new BackgroundImage(new Image(icon), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
	
	public static Block getBlock(int row,int col) {
		if (col >= GameAreaInner.NUM_COL) {
			col = col - GameAreaInner.NUM_COL;
		} else if (col < 0) {
			col = col + GameAreaInner.NUM_COL;
		}
		
		if (row >= GameAreaInner.NUM_ROW) {
			row = row - GameAreaInner.NUM_ROW;
		} else if (row < 0) {
			row = row + GameAreaInner.NUM_ROW;
		}
		return Main.gameArea.getInner().getBlocks().get(row).get(col);
	}
	
	public void addEffect(Effect e) {
		Block b = new Block(e.getRow(), e.getCol());
		e.render(b);
		this.getChildren().add(b);
		e.setEffectBlock(b);
	}
	
	public void removeEffect(Effect e) {
		int sizeb = this.getChildren().size();
		
		this.getChildren().remove(e.getEffectBlock());
		//System.out.println("Remove effect "+e.getClass().getName()+ " "+sizeb+" "+this.getChildren().size());
		//System.out.println(this.getChildren().contains(e.getEffectBlock()));
	}
}
