package controller;

import java.util.ArrayList;

import entity.Direction;
import entity.Player;
import gui.Block;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

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
		registerMouse();
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
	
	private void registerMouse() {
		scene.setOnMouseMoved(e -> {
			double mousex = e.getSceneX();
			double mousey = e.getSceneY();
			Bounds playerBound = Player.mainPlayer.getBlock().localToScene(((StackPane)scene.getRoot()).getBoundsInLocal());
			//System.out.println(mousex);
			//System.out.println(playerBound.getMinX());
			double playerx = playerBound.getMinX()+Block.WIDTH/2;
			double playery = playerBound.getMinY()+Block.HEIGHT/2;
			
			double absx = Math.abs(mousex-playerx);
			double absy = Math.abs(mousey-playery);
			if (absx > absy) {
				//x base
				if (mousex < playerx) {
					Player.mainPlayer.setFacing(Direction.LEFT);
					//System.out.println("LEFT");
				} else {
					Player.mainPlayer.setFacing(Direction.RIGHT);
					//System.out.println("RIGHT");
				}
			} else {
				//y base
				if (mousey < playery) {
					Player.mainPlayer.setFacing(Direction.UP);
					//System.out.println("UP");
				} else {
					Player.mainPlayer.setFacing(Direction.DOWN);
					//System.out.println("DOWN");
				}
			}
		});
		
		scene.setOnMousePressed(e->{
	        if (e.isPrimaryButtonDown()) {
	            Player.mainPlayer.attack();
	            //System.out.println("dsadsasad");
	        } else {
	        	Player.mainPlayer.bomb();
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
