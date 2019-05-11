package effect;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.Main;
import entity.CanTakePhysicalDamage;
import entity.Entity;
import entity.GiveEXPOnDead;
import entity.HasInventory;
import entity.HasLevel;
import entity.ore.Ore;
import entity.ore.Pickable;
import gui.ImageStore;

public class AttackEffect extends AutokillEffect {
	
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
	          Main.class.getResourceAsStream("/sound/attack.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e) {
	        System.err.println(e.getMessage());
	      }
	    }
	  }).start();
	}
	
	public AttackEffect(Entity target, double atkDamage, Entity attacker) {
		this(target.getRow(), target.getCol(), atkDamage, attacker);
		// TODO Auto-generated constructor stub
	}

	public AttackEffect(int row, int col, double atkDamage, Entity attacker) {
		super(row, col,200);
		this.atkDamage = atkDamage;
		this.attacker = attacker;
		playSound();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollideWith(Entity target) {
		// TODO Auto-generated method stub
		if (target instanceof CanTakePhysicalDamage) {
			((CanTakePhysicalDamage) target).takePhysicalDamage(getAtkDamage());
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
		return ImageStore.getInstance().attack;
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
