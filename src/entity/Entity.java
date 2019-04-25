package entity;

import gui.Block;
import javafx.application.Platform;

public abstract class Entity {
	public void Entity() {
		Entity thiss = this;
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Block.getBlock(getRow(), getCol()).setEntity(thiss);
			}
		});
		
	}
	
	public abstract String getIcon();
	public void render(Block block) {
		block.testLabel.setText("");
	};
	public abstract int getRow();
	public abstract int getCol();
}
