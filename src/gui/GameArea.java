package gui;

import javafx.scene.control.ScrollPane;

public class GameArea extends ScrollPane {
	private GameAreaInner inner;
	
	public static double SCROLLBAR_WIDTH = 19;
	public static double SCROLLBAR_HEIGHT = 19;
	
	public GameArea() {
		inner = new GameAreaInner();
		this.setContent(inner);
	}
	
	public GameAreaInner getInner() {
		return inner;
	}
	
	public double getWidthReal() {
		return super.getWidth() - SCROLLBAR_WIDTH;
	}
	
	public double getHeightReal() {
		return super.getWidth() - SCROLLBAR_HEIGHT;
	}
}
