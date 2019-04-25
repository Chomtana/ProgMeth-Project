package controller;

import java.util.ArrayList;

import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class EventController {
	private Scene scene;
	private AnimationTimer timer;
	
	private boolean LEFT = false;
	private boolean RIGHT = false;
	private boolean UP = false;
	private boolean DOWN = false;
	
	public EventController(Scene s) {
		scene = s;
		registerOnKey();
		registerAnimationTimer();
	}
	
	private void registerAnimationTimer() {
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (LEFT) {
					Player.mainPlayer.moveLeft();
				} else if (RIGHT) {
					Player.mainPlayer.moveRight();
				} else if (UP) {
					Player.mainPlayer.moveUp();
				} else if (DOWN) {
					Player.mainPlayer.moveDown();
				}
			}
		};
		
		timer.start();
	}
	
	private void registerOnKey() {
    	scene.setOnKeyPressed(e -> {
    	    if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
    	        LEFT = true;
    	    } else if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
    	    	RIGHT = true;
    	    } else if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
    	    	UP = true;
    	    } else if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
    	    	DOWN = true;
    	    }
    	});
    	
    	scene.setOnKeyReleased(e -> {
    	    if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
    	        LEFT = false;
    	    } else if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
    	    	RIGHT = false;
    	    } else if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
    	    	UP = false;
    	    } else if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
    	    	DOWN = false;
    	    }
    	});
	}
	
	public Scene getScene() {
		return scene;
	}
	
	private static ArrayList<Runnable> onLoadRunnable = new ArrayList<Runnable>();
	public static void onLoad(Runnable r) {
		onLoadRunnable.add(r);
	}
	
	public static void performOnLoad() {
		for(Runnable r: onLoadRunnable) r.run();
	}
}
