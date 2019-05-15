package application;

import java.util.Random;

import controller.CameraController;
import controller.EventController;
import controller.SpawnController;
import entity.Player;
import gui.ControlPanel;
import gui.GameArea;
import gui.ImageStore;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
 
public class Main extends Application {
	
	public static GameArea gameArea;
	public static StackPane eventPane;
	public static Random random = new Random();
	public static CameraController cameraController;
	public static ControlPanel controlPanel;
	public static Main instance;
	public static StackPane root;
	
	
	
    public static Main getInstance() {
		return instance;
	}



	public static void main(String[] args) {
        launch(args);
    }
	
	public static void endGame() {
		Platform.exit();
		
	}
    
    
    
    @Override
    public void start(Stage primaryStage) {
    	instance = this;
    	primaryStage.setTitle("Ja Boom Mine By InfinityBug");
    	primaryStage.setResizable(false);
    	
    	root = new StackPane();
    	Scene scene = new Scene(root,900,600);
    	
    	root.setPadding(new Insets(0,0,0,0));
    	root.setStyle("-fx-background-color: lemonchiffon;");
    	
    	eventPane = new StackPane();
    	
    	HBox hb = new HBox();
    	//hb.setSpacing(10);
    	
    	StackPane st1 = new StackPane();
    	
    	st1.getChildren().add(gameArea = new GameArea());
    	st1.getChildren().add(eventPane);
    	
    	final StackPane startPane = new StackPane();
    	startPane.setStyle(
	            "-fx-background-image: url(" +
	            		ImageStore.getInstance().startbg +
	            "); " +
	            "-fx-background-size: 100%, 100%;"
	        );
    	
    	Button startBtn = new Button("Start Game Now !!!");
    	startBtn.setStyle("-fx-background-color: greenyellow");
    	startBtn.setPadding(new Insets(20));
    	startBtn.setFont(new Font(24));
    	
    	startPane.getChildren().add(startBtn);
    	
    	startBtn.setOnAction(e->{
    		
    		startBtn.setText("Loading ...");
        	
        	new Thread(new Runnable() {
    			
    			@Override
    			public void run() {
    				// TODO Auto-generated method stub
    				try {
    					Thread.sleep(1000);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							root.getChildren().remove(startPane);
						}
					});
    				EventController.performOnLoad();
    			}
    		}).start();
    	});
    	
    	
    	root.getChildren().add(startPane);
    	
    	
    	
    	new EventController(scene);
    	
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        //System.out.println("Application Closed by click to Close Button(X)");
                        System.exit(0);
                    }
                });
            }
        });
    	
    	EventController.onLoad(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new SpawnController();
				cameraController = new CameraController();
				//cameraController.performSetCenter();
				CameraController.registerInstance(cameraController);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(100);
							Platform.runLater(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									controlPanel = new ControlPanel();
									hb.getChildren().add(controlPanel);
									hb.getChildren().add(st1);
									root.getChildren().add(hb);
									cameraController.performSetCenter();
								}
							});
							Thread.sleep(1000);
							Platform.runLater(new Runnable() {
								
								@Override
								public void run() {
									Player.mainPlayer.moveLeft();
								}
							});
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}

						
					}
				}).start();
				
			}
		});

    	
    	
    	
    	
    	/*Rule<Long> r = new Rule<Long>() {
			
			@Override
			public void onChange(Long curr, Long prev) {
				// TODO Auto-generated method stub
				//System.out.println(curr+" "+prev);
			}
			
			@Override
			public Long get() {
				// TODO Auto-generated method stub
				System.out.println(TimeController.getCurrentTime());
				return TimeController.getCurrentTime();
			}
		};*/
    }

	public static StackPane getEventPane() {
		return eventPane;
	}
    
    
}