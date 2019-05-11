package effect;

import controller.EventController;
import entity.Entity;
import gui.Block;

public abstract class Effect extends Entity {
	
	private Block effectBlock;
	private int row,col;
	
	public Effect(int row,int col) {
		super(row,col);
		
		Effect thiss = this;
		
		EventController.onLoad(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//System.out.println(getRow());
				//System.out.println(getCol());
				try { 
					if (Block.getBlock(row, col).hasEntity()) {
						onCollideWith(Block.getBlock(row, col).getEntity());
					}
					
					Block.getBlock(getRow(), getCol()).addEffect(thiss);
				} catch (NullPointerException e) {
					
				}
			}
		});
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return col;
	}

	@Override
	public void setRow(int row) {
		// TODO Auto-generated method stub
		this.row = row;
	}

	@Override
	public void setCol(int col) {
		// TODO Auto-generated method stub
		this.col = col;
	}
	
	public abstract void onCollideWith(Entity target);

	public Block getEffectBlock() {
		return effectBlock;
	}

	public void setEffectBlock(Block effectBlock) {
		this.effectBlock = effectBlock;
	}
	
	public void render(Block block) {
		block.testLabel.setText("EF");
	}
	
	

}
