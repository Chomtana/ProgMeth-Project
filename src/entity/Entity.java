package entity;

import gui.Block;

public abstract class Entity {
	public abstract String getIcon();
	public void render(Block block) {
		block.testLabel.setText("");
	};
}
