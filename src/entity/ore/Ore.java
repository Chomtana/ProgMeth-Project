package entity.ore;

import entity.Entity;
import entity.monster.Monster;

public abstract class Ore extends Entity
{

	public Ore(int row,int col) {
		super(row,col);
		Ore thiss = this;
		/*bouncer = new ThreadRule<Boolean>() {
			
			@Override
			public void onChange(Boolean curr, Boolean prev) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Boolean get() {
				// TODO Auto-generated method stub
				Block.getBlock(oldRow, oldCol).setEntity(thiss);
				return null;
			}
		};*/
	}

}
