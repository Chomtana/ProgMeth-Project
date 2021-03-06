package controller;

import application.Main;
import entity.Entity;
import entity.Player;
import gui.Block;
import gui.BlockView;
import gui.GameArea;
import gui.GameAreaInner;
import javafx.application.Platform;
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
		double hval = ((xpos-(double)area.getWidthReal()/2 + 15)/(double)totalx);
		double vval = ((ypos-(double)area.getHeightReal()/2 + 15)/(double)totaly);
		//if(hval>25) hval = 1.0164883162262504;
		//area.setHvalue( hval );
		//area.setVvalue( vval );
		//System.out.println(xpos);
		//System.out.println(hval*totalx);
		
		
		double blocksinrow = area.getHeight()/Block.HEIGHT;
		double blocksincol = area.getWidth()/Block.WIDTH;
		int topleftrow = (int) Math.min(9999, Math.max(-9999, center.getRow() - Math.round((blocksinrow-1)/2)));
		int topleftcol = (int) Math.min(9999, Math.max(-9999, center.getCol() - Math.round((blocksincol-1)/2)));
		
		/*area.getInner().setPadding(new Insets(
			Math.max(0, topleftrow*Block.HEIGHT),
			0,//totalx - Math.max(0, topleftcol*Block.WIDTH), 
			0,//totaly - Math.max(0, topleftrow*Block.HEIGHT), 
			Math.max(0, topleftcol*Block.WIDTH)
		));*/

		//System.out.println(topleftcol);
		//System.out.println(GameAreaInner.NUM_COL - blocksincol);
		if (topleftrow != BlockView.getBlockView(0, 0).getRealRow() || topleftcol != BlockView.getBlockView(0, 0).getRealCol()) {
			//System.out.println(topleftrow); System.out.println(topleftcol);
			//System.out.println(hval);
			for(int i = 0;i<GameAreaInner.VIEW_ROW;i++) {
				for(int j = 0;j<GameAreaInner.VIEW_COL;j++) {
					BlockView bv = BlockView.getBlockView(i, j);
					int targetrow = i+topleftrow;
					int targetcol = j+topleftcol;
					
					if (targetcol >= GameAreaInner.NUM_COL) {
						targetcol = targetcol - GameAreaInner.NUM_COL;
					} else if (targetcol < 0) {
						targetcol = targetcol + GameAreaInner.NUM_COL;
					}
					
					if (targetrow >= GameAreaInner.NUM_ROW) {
						targetrow = targetrow - GameAreaInner.NUM_ROW;
					} else if (targetrow < 0) {
						targetrow = targetrow + GameAreaInner.NUM_ROW;
					}
					bv.setBlock(Block.getBlock(targetrow, targetcol),i+topleftrow,j+topleftcol);

				}
			}
			//performSetCenter();
			if (topleftcol == 94 || topleftrow == 94) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								performSetCenter();
							}
						});
						
					}
				}).start();
				
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
