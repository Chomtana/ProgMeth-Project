package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.Main;
import entity.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ControlPanel extends VBox
{
	public static int BOX_SIZE = 50;

	private CrafterBox crafterBox;
	private InventoryBox inventoryBox;
	private CrafterPerformer craftPerformer;
	private EquipmentViewer equipmentViewer;
	private LevelPanel levelPanel;

	public static synchronized void wrongSound() {
		new Thread(new Runnable()
		{
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run() {
				try
				{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem
							.getAudioInputStream(ClassLoader.getSystemResource("/sound/wrong.wav"));
					clip.open(inputStream);
					clip.start();
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public ControlPanel()
	{
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		levelPanel = new LevelPanel();
		this.getChildren().add(levelPanel);

		Label inventoryTitle = new Label("Inventory");
		inventoryTitle.setFont(new Font(18));
		HBox div91 = new HBox(inventoryTitle);
		div91.setAlignment(Pos.CENTER);
		this.getChildren().add(div91);

		inventoryBox = new InventoryBox(Player.mainPlayer.getInventory());
		this.setPrefWidth(300);
		this.getChildren().add(inventoryBox);

		Label craftTitle = new Label("Crafting Table");
		craftTitle.setFont(new Font(18));
		HBox div92 = new HBox(craftTitle);
		div92.setAlignment(Pos.CENTER);
		this.getChildren().add(div92);

		crafterBox = new CrafterBox(Player.mainPlayer.getCrafter());
		this.getChildren().add(crafterBox);

		craftPerformer = new CrafterPerformer();
		HBox div1 = new HBox(craftPerformer);
		div1.setAlignment(Pos.CENTER);
		this.getChildren().add(div1);

		Label equipmentTitle = new Label("Equipment");
		equipmentTitle.setFont(new Font(18));
		HBox div93 = new HBox(equipmentTitle);
		div93.setAlignment(Pos.CENTER);
		this.getChildren().add(div93);

		equipmentViewer = new EquipmentViewer();
		HBox div2 = new HBox(equipmentViewer);
		div2.setAlignment(Pos.CENTER);
		this.getChildren().add(div2);

	}

	public void update() {
		try
		{
			inventoryBox.update();
			crafterBox.update();
			craftPerformer.update();
			equipmentViewer.update();
			levelPanel.update();
		}
		catch (NullPointerException e)
		{
		}
	}
}
