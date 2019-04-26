package effect;

import entity.Entity;

public abstract class AutokillEffect extends Effect {
	
	private int duration;
	private Thread autokillTimer;
	
	public AutokillEffect(int row,int col,int duration) {
		super(row,col);
		this.duration = duration;
		autokillTimer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(duration);
					kill();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					kill();
				}
			}
		});
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	

}
