package gui;

import entity.Player;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class EquipmentViewer extends GridPane {
	private StackPane armor = new StackPane();
	private StackPane helmet = new StackPane();
	private StackPane sword = new StackPane();
	private StackPane pant = new StackPane();
	private StackPane boot = new StackPane();
	
	private String borderCSS = "-fx-border-width: 1px; -fx-border-color: blue;";
	
	public EquipmentViewer() {
		this.setHgap(10);
		this.setVgap(10);
		armor.setPrefWidth(ControlPanel.BOX_SIZE); armor.setPrefHeight(ControlPanel.BOX_SIZE);
		helmet.setPrefWidth(ControlPanel.BOX_SIZE); helmet.setPrefHeight(ControlPanel.BOX_SIZE);
		sword.setPrefWidth(ControlPanel.BOX_SIZE); sword.setPrefHeight(ControlPanel.BOX_SIZE);
		pant.setPrefWidth(ControlPanel.BOX_SIZE); pant.setPrefHeight(ControlPanel.BOX_SIZE);
		boot.setPrefWidth(ControlPanel.BOX_SIZE); boot.setPrefHeight(ControlPanel.BOX_SIZE);
		this.add(armor, 0, 0);
		this.add(helmet, 1, 0);
		this.add(sword, 2, 0);
		this.add(pant, 0, 1);
		this.add(boot, 2, 1);
		
		update();
	}
	
	private String getArmorIcon() {
		if (Player.mainPlayer.getInventory().getArmorLevel() == 0) return null;
		if (Player.mainPlayer.getInventory().getArmorLevel() == 1) return ImageStore.getInstance().ironArmor;
		if (Player.mainPlayer.getInventory().getArmorLevel() == 2) return ImageStore.getInstance().diamondArmor;
		return null;
	}
	
	private String getHelmetIcon() {
		if (Player.mainPlayer.getInventory().getHelmetLevel() == 0) return null;
		if (Player.mainPlayer.getInventory().getHelmetLevel() == 1) return ImageStore.getInstance().ironHelmet;
		if (Player.mainPlayer.getInventory().getHelmetLevel() == 2) return ImageStore.getInstance().diamondHelmet;
		return null;
	}
	
	private String getSwordIcon() {
		if (Player.mainPlayer.getInventory().getSwordLevel() == 0) return ImageStore.getInstance().woodenSword;
		if (Player.mainPlayer.getInventory().getSwordLevel() == 1) return ImageStore.getInstance().ironSword;
		if (Player.mainPlayer.getInventory().getSwordLevel() == 2) return ImageStore.getInstance().diamondSword;
		return null;
	}
	
	private String getPantIcon() {
		if (Player.mainPlayer.getInventory().getPantLevel() == 0) return null;
		if (Player.mainPlayer.getInventory().getPantLevel() == 1) return ImageStore.getInstance().ironPant;
		if (Player.mainPlayer.getInventory().getPantLevel() == 2) return ImageStore.getInstance().diamondPant;
		return null;
	}
	
	private String getBootIcon() {
		if (Player.mainPlayer.getInventory().getBootLevel() == 0) return null;
		if (Player.mainPlayer.getInventory().getBootLevel() == 1) return ImageStore.getInstance().ironBoot;
		if (Player.mainPlayer.getInventory().getBootLevel() == 2) return ImageStore.getInstance().diamondBoot;
		return null;
	}
	
	public void update() {
		String icon;
		icon = getArmorIcon();
		if (icon == null || icon.equals("")) {
			armor.setStyle(borderCSS+"");
		} else {
			//System.out.println(icon);
			armor.setStyle(borderCSS+
		            "-fx-background-image: url(" +
		            		icon +
		            "); " +
		            "-fx-background-size: 100%, 100%;"
		        );
		}
		
		icon = getHelmetIcon();
		if (icon == null || icon.equals("")) {
			helmet.setStyle(borderCSS+"");
		} else {
			//System.out.println(icon);
			helmet.setStyle(borderCSS+
		            "-fx-background-image: url(" +
		            		icon +
		            "); " +
		            "-fx-background-size: 100%, 100%;"
		        );
		}
		
		icon = getSwordIcon();
		if (icon == null || icon.equals("")) {
			sword.setStyle(borderCSS+"");
		} else {
			//System.out.println(icon);
			sword.setStyle(borderCSS+
		            "-fx-background-image: url(" +
		            		icon +
		            "); " +
		            "-fx-background-size: 100%, 100%;"
		        );
		}
		
		icon = getPantIcon();
		if (icon == null || icon.equals("")) {
			pant.setStyle(borderCSS+"");
		} else {
			//System.out.println(icon);
			pant.setStyle(borderCSS+
		            "-fx-background-image: url(" +
		            		icon +
		            "); " +
		            "-fx-background-size: 100%, 100%;"
		        );
		}
		
		icon = getBootIcon();
		if (icon == null || icon.equals("")) {
			boot.setStyle(borderCSS+"");
		} else {
			//System.out.println(icon);
			boot.setStyle(borderCSS+
		            "-fx-background-image: url(" +
		            		icon +
		            "); " +
		            "-fx-background-size: 100%, 100%;"
		        );
		}
	}
	
	
}
