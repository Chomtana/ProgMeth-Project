package gui;

import application.Main;
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

public class Block extends StackPane {
	private int WIDTH = 30;
	private int HEIGHT = 30;
	
	private Entity entity;
	
	public Label testLabel = new Label();
	
	public Block() {
		this.setStyle("-fx-border-width: 1px;");
		this.setStyle("-fx-border-color: blue;");
		this.setPrefWidth(WIDTH);
		this.setPrefHeight(HEIGHT);
		
		setEntity(null);
		
		this.getChildren().add(testLabel);
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
		if (entity != null) {
			this.entity.render(this);
		} else {
			this.entity = new NullEntity();
			this.entity.render(this);
		}
	}
	
	public void removeEntity() {
		setEntity(null);
	}
	
	public void setIcon(String icon) {
		this.setBackground(new Background(new BackgroundImage(new Image(icon), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
	
	public static Block getBlock(int row,int col) {
		return Main.gameArea.getInner().getBlocks().get(row).get(col);
	}
}
