package gui;

import application.Main;
import effect.Effect;
import entity.HasInventory;
import entity.Player;
import item.Inventory;
import item.NotEnoughItemException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventoryBox extends HBox implements HasInventory {
	private Inventory inventory;
	
	private Button coalPic = new Button();
	private Label coalAmount = new Label();
	private Button ironPic = new Button();
	private Label ironAmount = new Label();
	private Button diamondPic = new Button();
	private Label diamondAmount = new Label();
	
	private VBox genBox(Button pic, Label amount) {
		VBox V = new VBox();
		V.setAlignment(Pos.CENTER);
		V.getChildren().add(pic);
		V.getChildren().add(amount);
		return V;
	}
	
	public InventoryBox(Inventory inventory) {
		this.inventory = inventory;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		coalPic.setPrefWidth(ControlPanel.BOX_SIZE);
		coalPic.setPrefHeight(ControlPanel.BOX_SIZE);
		coalPic.setStyle(
	            "-fx-background-image: url(" +
	            		ImageStore.getInstance().coal +
	            "); " +
	            "-fx-background-size: 100%, 100%;"
	        );
		coalPic.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Player.mainPlayer.getCrafter().add("Coal");
				} catch (NotEnoughItemException e) {
					// TODO Auto-generated catch block
					ControlPanel.wrongSound();
				}
			}
		});
		ironPic.setPrefWidth(ControlPanel.BOX_SIZE);
		ironPic.setPrefHeight(ControlPanel.BOX_SIZE);
		ironPic.setStyle(
	            "-fx-background-image: url(" +
	            		ImageStore.getInstance().iron +
	            "); " +
	            "-fx-background-size: 100%, 100%;"
	        );
		ironPic.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Player.mainPlayer.getCrafter().add("Iron");
				} catch (NotEnoughItemException e) {
					// TODO Auto-generated catch block
					ControlPanel.wrongSound();
				}
			}
		});
		diamondPic.setPrefWidth(ControlPanel.BOX_SIZE);
		diamondPic.setPrefHeight(ControlPanel.BOX_SIZE);
		diamondPic.setStyle(
	            "-fx-background-image: url(" +
	            		ImageStore.getInstance().diamond +
	            "); " +
	            "-fx-background-size: 100%, 100%;"
	        );
		diamondPic.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Player.mainPlayer.getCrafter().add("Diamond");
				} catch (NotEnoughItemException e) {
					// TODO Auto-generated catch block
					ControlPanel.wrongSound();
				}
			}
		});
		
		this.getChildren().add(genBox(coalPic,coalAmount));
		this.getChildren().add(genBox(ironPic,ironAmount));
		this.getChildren().add(genBox(diamondPic,diamondAmount));
		
		update();
	}
	
	public void update() {
		coalAmount.setText(getInventory().get("Coal").toString());
		ironAmount.setText(getInventory().get("Iron").toString());
		diamondAmount.setText(getInventory().get("Diamond").toString());
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}
	
	
}
