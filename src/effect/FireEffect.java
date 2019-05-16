package effect;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.Main;
import entity.CanTakeBombDamage;
import entity.Entity;
import entity.GiveEXPOnDead;
import entity.HasLevel;
import entity.ore.Pickable;
import gui.ImageStore;

public class FireEffect extends AutokillEffect {
	
	private double atkDamage;
	private Entity attacker;
	
	public static synchronized void playSound() {
	  new Thread(new Runnable() {
	  // The wrapper thread is unnecessary, unless it blocks on the
	  // Clip finishing; see comments.
	    public void run() {
	      try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	        		ClassLoader.getSystemResource("sound/bomb.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e) {
	        System.err.println(e.getMessage());
	      }
	    }
	  }).start();
	}
	
	public FireEffect(Entity e, double atkDamage) {
		this(e.getRow(), e.getCol(), atkDamage, e);
		// TODO Auto-generated constructor stub
	}

	public FireEffect(int row, int col, double atkDamage, Entity attacker) {
		super(row, col,300);
		this.attacker = attacker;
		this.atkDamage = atkDamage;
		playSound();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollideWith(Entity target) {
		// TODO Auto-generated method stub
		if (target instanceof CanTakeBombDamage) {
			((CanTakeBombDamage) target).takeBombDamage(getAtkDamage());
			if (target instanceof Pickable) {
				Pickable ore = (Pickable) target;
				System.out.println("Pickable");
				ore.pick(attacker);
			}
			if (!target.isAlive()) {
				if (target instanceof GiveEXPOnDead && attacker instanceof HasLevel) {
					((HasLevel) attacker).receiveEXP(((GiveEXPOnDead) target).getExpGived());
				}
			}
		}
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return ImageStore.getInstance().fire;
	}

	public double getAtkDamage() {
		return atkDamage;
	}

	public void setAtkDamage(double atkDamage) {
		this.atkDamage = atkDamage;
	}
	
	public Entity getAttacker() {
		return attacker;
	}

	public void setAttacker(Entity attacker) {
		this.attacker = attacker;
	}
	
	

}
