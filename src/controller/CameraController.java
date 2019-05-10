package controller;

import application.Main;
import entity.Entity;
import entity.Player;
import gui.Block;
import gui.BlockView;
import gui.GameArea;
import gui.GameAreaInner;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;

public class CameraController
{
	private Entity center;
	private GameArea area;
	private static CameraController cam = null;
	
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
		//double totalx = Block.WIDTH * GameAreaInner.NUM_COL - area.getWidth();
		//double totaly = Block.HEIGHT * GameAreaInner.NUM_ROW - area.getHeight();
		double xpos = center.getCol() * Block.WIDTH + Block.WIDTH/2;
		double ypos = center.getRow() * Block.HEIGHT + Block.HEIGHT/2;
		//System.out.println(playerBound.getMinX()+" "+playerBound.getMinY());
		double hval = ((xpos-(double)area.getWidthReal()/2)/(double)totalx);
		double vval = ((ypos-(double)area.getHeightReal()/2)/(double)totaly);
		area.setHvalue( hval );
		area.setVvalue( vval );
		//System.out.println(xpos);
		//System.out.println(hval*totalx);
		
		
		double blocksinrow = area.getHeight()/Block.HEIGHT;
		double blocksincol = area.getWidth()/Block.WIDTH;
		int topleftrow = (int) Math.min(GameAreaInner.NUM_ROW - blocksinrow, Math.max(0, center.getRow() - Math.round((blocksinrow-1)/2)));
		int topleftcol = (int) Math.min(GameAreaInner.NUM_COL - blocksincol, Math.max(0, center.getCol() - Math.round((blocksincol-1)/2)));
		
		area.getInner().setPadding(new Insets(
			Math.max(0, topleftrow*Block.HEIGHT),
			0,//totalx - Math.max(0, topleftcol*Block.WIDTH), 
			0,//totaly - Math.max(0, topleftrow*Block.HEIGHT), 
			Math.max(0, topleftcol*Block.WIDTH)
		));

		//System.out.println(topleftcol);
		//System.out.println(GameAreaInner.NUM_COL - blocksincol);
		if (topleftrow != BlockView.getBlockView(0, 0).getRealRow() || topleftcol != BlockView.getBlockView(0, 0).getRealCol()) {
			//System.out.println(topleftrow); System.out.println(topleftcol);
			for(int i = 0;i<GameAreaInner.VIEW_ROW;i++) {
				for(int j = 0;j<GameAreaInner.VIEW_COL;j++) {
					BlockView.getBlockView(i, j).setBlock(Block.getBlock(i+topleftrow, j+topleftcol),i+topleftrow,j+topleftcol);
				}
			}
		}
		
		
	}
	
	public static CameraController getInstance() {
		return cam;
	}
	
	public static void registerInstance(CameraController camm) {
		cam = camm;
	}
	
}
