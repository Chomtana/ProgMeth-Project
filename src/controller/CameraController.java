package controller;

import application.Main;
import entity.Entity;
import entity.Player;
import gui.Block;
import gui.GameArea;
import gui.GameAreaInner;
import javafx.geometry.Bounds;

public class CameraController
{
	private Entity center;
	private GameArea area;
	
	/*private ThreadRule<Boolean> updater = new ThreadRule<Boolean>()
	{
		
		@Override
		public void onChange(Boolean curr, Boolean prev) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Boolean get() {
			// TODO Auto-generated method stub
			performSetCenter();
			return null;
		}
	};*/
	
	public CameraController(Entity center, GameArea area) {
		this.center = center;
		this.area = area;
	}
	
	public CameraController() {
		this(Player.mainPlayer, Main.gameArea);
		
	}

	public Entity getCenter() {
		return center;
	}

	public void setCenter(Entity center) {
		this.center = center;
	}
	
	
	public void performSetCenter() {
		//Bounds playerBound = center.getBlock().localToScene(area.getInner().getBoundsInLocal());
		
		double totalx = area.getInner().getWidth() - area.getWidthReal();
		double totaly = area.getInner().getHeight() - area.getHeightReal();
		double xpos = center.getCol() * Block.WIDTH + Block.WIDTH/2;
		double ypos = center.getRow() * Block.HEIGHT + Block.HEIGHT/2;
		//System.out.println(playerBound.getMinX()+" "+playerBound.getMinY());
		area.setHvalue( ((xpos-(double)area.getWidthReal()/2)/(double)totalx) );
		area.setVvalue( ((ypos-(double)area.getHeightReal()/2)/(double)totaly) );
		//System.out.println(xpos);
	}
	
}
