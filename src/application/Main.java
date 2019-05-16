package application;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CameraController;
import controller.EventController;
import controller.SpawnController;
import controller.TimeController;
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
		JFrame frame = new JFrame("Ja Boom Mine By InfinityBug");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.setSize(900, 600);
		panel.setSize(900, 600);
		frame.setLocation(300,200);
		panel.setBorder(new EmptyBorder(200, 200, 200, 200));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.RED);
		panel.setBackground(Color.RED);
		
		JLabel gameOverLabel = new JLabel("Game Over !");
		gameOverLabel.setForeground(Color.WHITE);
		gameOverLabel.setFont(new java.awt.Font(gameOverLabel.getName(), java.awt.Font.PLAIN, 24));
		gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel youHaveSurvivedFor = new JLabel("You have survived for "+TimeController.getCurrentTime()/1000+" seconds");
		youHaveSurvivedFor.setForeground(Color.WHITE);
		youHaveSurvivedFor.setFont(new java.awt.Font(youHaveSurvivedFor.getName(), java.awt.Font.PLAIN, 24));
		youHaveSurvivedFor.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton exitBtn = new JButton("Exit game");
		exitBtn.setBackground(Color.LIGHT_GRAY);
		exitBtn.setBorder(new EmptyBorder(20, 20, 20, 20));
		exitBtn.setFont(new java.awt.Font(exitBtn.getName(), java.awt.Font.PLAIN, 24));
		exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		panel.add(gameOverLabel);
		panel.add(youHaveSurvivedFor);
		panel.add(exitBtn);
		
		frame.getContentPane().add(panel);
		frame.pack();
		
		frame.setVisible(true);
	}
	
	public static synchronized void playBgSound() {
	  new Thread(new Runnable() {
	  // The wrapper thread is unnecessary, unless it blocks on the
	  // Clip finishing; see comments.
	    public void run() {
	      try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	        		ClassLoader.getSystemResource("sound/bg.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        while(clip.isRunning())
	        {
	          Thread.sleep(100);
	        }
	        Thread.sleep(100);
	      } catch (Exception e) {
	        System.err.println(e.getMessage());
	      }
	    }
	  }).start();
	}
    
    
    
    @Override
    public void start(Stage primaryStage) {
    	playBgSound();
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