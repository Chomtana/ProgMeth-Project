package gui;

import entity.Player;
import item.NotEnoughItemException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class CrafterPerformer extends Button {
	public CrafterPerformer() {
		this.setPrefWidth(ControlPanel.BOX_SIZE);
		this.setPrefHeight(ControlPanel.BOX_SIZE);
		this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					Player.mainPlayer.getCrafter().performCraft();
				} catch (NotEnoughItemException e) {
					// TODO Auto-generated catch block
					ControlPanel.wrongSound();
				}
			}
		});
		update();
	}
	
	public String getIcon() {
		String craft = Player.mainPlayer.getCrafter().getCraftTarget();
		if (craft.equals("") || craft==null) return null;
		System.out.println(craft);
		return ClassLoader.getSystemResource("img/"+craft+".png").toString();
	}
	
	public void update() {
		String icon = getIcon();
		if (icon == null || icon.equals("")) {
			this.setStyle("");
		} else {
			//System.out.println(icon);
			this.setStyle(
		            "-fx-background-image: url(" +
		            		icon +
		            "); " +
		            "-fx-background-size: 100%, 100%;"
		        );
		}
		
	}
}
