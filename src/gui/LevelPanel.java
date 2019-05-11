package gui;

import entity.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LevelPanel extends VBox {
	private Label levelLabel = new Label();
	private Label expLabel = new Label();
	private ProgressBar expBar = new ProgressBar();
	private Label hpLabel = new Label();
	private ProgressBar hpBar = new ProgressBar();
	
	public LevelPanel() {
		this.setSpacing(2);
		levelLabel.setFont(new Font(20));
		expBar.setPrefWidth(280);
		hpBar.setPrefWidth(280);
		HBox div1 = new HBox(levelLabel);
		div1.setAlignment(Pos.CENTER);
		this.getChildren().add(div1);
		this.getChildren().add(expLabel);
		this.getChildren().add(expBar);
		this.getChildren().add(hpLabel);
		this.getChildren().add(hpBar);
		update();
	}
	
	public void update() {
		levelLabel.setText("Level "+Player.mainPlayer.getLevel());
		expLabel.setText("EXP: "+Player.mainPlayer.getExp()+" / "+Player.mainPlayer.getCurrentLevelEXPNeeded());
		expBar.setProgress((double)Player.mainPlayer.getExp()/(double)Player.mainPlayer.getCurrentLevelEXPNeeded());
		hpLabel.setText("HP: "+Player.mainPlayer.getHP()+" / "+Player.mainPlayer.getMaxhp());
		hpBar.setProgress((double)Player.mainPlayer.getHP()/(double)Player.mainPlayer.getMaxhp());
		//System.out.println((double)Player.mainPlayer.getHP()/(double)Player.mainPlayer.getMaxhp());
		if ((double)Player.mainPlayer.getHP()/(double)Player.mainPlayer.getMaxhp() <= 0.33) {
			hpBar.setStyle("-fx-accent: red; ");
		} else if ((double)Player.mainPlayer.getHP()/(double)Player.mainPlayer.getMaxhp() <= 0.66) {
			hpBar.setStyle("-fx-accent: yellow; ");
		} else {
			hpBar.setStyle("-fx-accent: green; ");
		}
	}
}
