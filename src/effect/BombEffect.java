package effect;

import entity.CanTakePhysicalDamage;
import entity.Entity;
import gui.GameAreaInner;
import gui.ImageStore;

public class BombEffect extends AutokillEffect {
	
	private double atkDamage;
	private Thread bombStarter;
	private int radius;
	private Entity attacker;
	
	public BombEffect(Entity e, double atkDamage, int radius) {
		this(e.getRow(), e.getCol(), atkDamage, radius, e);
		// TODO Auto-generated constructor stub
	}

	public BombEffect(int row, int col, double atkDamage,int radius, Entity attacker) {
		super(row, col,2000);
		this.attacker = attacker;
		this.atkDamage = atkDamage;
		this.radius = radius;
		// TODO Auto-generated constructor stub
		bombStarter = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
					//System.out.println(radius);
					new FireEffect(row, col, atkDamage, attacker);
					for(int i = row-1;i>=row-radius;i--) {
						if (i>=0) {
							new FireEffect(i, col, atkDamage, attacker);
						}
					}
					for(int i = row+1;i<=row+radius;i++) {
						if (i<GameAreaInner.NUM_ROW) {
							new FireEffect(i, col, atkDamage, attacker);
						}
					}
					for(int i = col-1;i>=col-radius;i--) {
						if (i>=0) {
							new FireEffect(row, i, atkDamage, attacker);
						}
					}
					for(int i = col+1;i<=col+radius;i++) {
						if (i<GameAreaInner.NUM_COL) {
							new FireEffect(row, i, atkDamage, attacker);
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
			}
		});
		bombStarter.start();
	}

	@Override
	public void onCollideWith(Entity target) {
		// TODO Auto-generated method stub
		/*if (target instanceof CanTakePhysicalDamage) {
			((CanTakePhysicalDamage) target).takePhysicalDamage(getAtkDamage());
		}*/
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return ImageStore.getInstance().bomb;
	}

	public double getAtkDamage() {
		return atkDamage;
	}

	public void setAtkDamage(double atkDamage) {
		this.atkDamage = atkDamage;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Entity getAttacker() {
		return attacker;
	}

	public void setAttacker(Entity attacker) {
		this.attacker = attacker;
	}
	
	

}
