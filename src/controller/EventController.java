package controller;

import java.util.ArrayList;

import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
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
	private static boolean onLoadRunned = false;
	public static void onLoad(Runnable r) {
		if (!onLoadRunned) {
			onLoadRunnable.add(r);
		} else {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					r.run();
				}
			});
		}
	}
	
	public static void performOnLoad() {
		TimeController.resetCurrentTime();
		for(Runnable r: onLoadRunnable) {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					r.run();
				}
			});
			
		}
		onLoadRunned = true;
	}
}
