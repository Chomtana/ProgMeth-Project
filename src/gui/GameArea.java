package gui;

import javafx.scene.control.ScrollPane;

public class GameArea extends ScrollPane {
	private GameAreaInner inner;
	
	public GameArea() {
		inner = new GameAreaInner();
		this.setContent(inner);
	}
	
	public GameAreaInner getInner() {
		return inner;
	}
}
