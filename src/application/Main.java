package application;

import controller.EventController;
import controller.SpawnController;
import gui.GameArea;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
 
public class Main extends Application {
	TextField Topic;
	DatePicker Date;
	TextArea Description;
	
	public static GameArea gameArea;
	public static StackPane eventPane;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    public HBox TopicControl() {
    	HBox render = new HBox();
    	
    	Label label = new Label("Topic : ");
    	TextField field = new TextField();
    	
    	Topic = field;
    	
    	field.setPrefWidth(200);
    	
    	render.getChildren().add(label);
    	render.getChildren().add(field);
    	
    	return render;
    }
    
    public HBox DateControl() {
    	HBox render = new HBox();
    	
    	Label label = new Label("Date : ");
    	DatePicker field = new DatePicker();
    	
    	Date = field;
    	
    	field.setPrefWidth(150);
    	
    	render.getChildren().add(label);
    	render.getChildren().add(field);
    	
    	return render;
    }
    
    public VBox TopControl() {
    	VBox render = new VBox();
    	
    	render.setSpacing(3);
    	
    	render.getChildren().add(TopicControl());
    	render.getChildren().add(DateControl());
    	
    	
    	return render;
    }
    
    public HBox BottomControl() {
    	HBox render = new HBox();
    	
    	render.setSpacing(3);
    	render.setAlignment(Pos.CENTER_RIGHT);
    	
    	Button OK = new Button("OK");
    	Button Clear = new Button("Clear");
    	
    	OK.setPrefWidth(60);
    	Clear.setPrefWidth(60);
    	
    	render.getChildren().add(OK);
    	render.getChildren().add(Clear);
    	
    	OK.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Message");
				a.setContentText("Topic : "+Topic.getText()+"\nDate : "+Date.getValue().toString()+"\nDescription : "+Description.getText());
				a.show();
			}
		});
    	
    	Clear.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Topic.setText("");
				Date.setValue(null);
				Description.setText("");
			}
		});
    	
    	return render;
    }
    
    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Ja Boom Mine By InfinityBug");
    	
    	StackPane root = new StackPane();
    	Scene scene = new Scene(root,600,600);
    	
    	Description = new TextArea();
    	
    	root.setPadding(new Insets(10,5,10,5));
    	
    	VBox div1 = new VBox();
    	
    	div1.setSpacing(8);
    	
    	div1.getChildren().add(TopControl());
    	div1.getChildren().add(Description);
    	div1.getChildren().add(BottomControl());
    	
    	eventPane = new StackPane();
    	
    	root.getChildren().add(gameArea = new GameArea());
    	root.getChildren().add(eventPane);
    	
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
			}
		});
    	
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
				EventController.performOnLoad();
			}
		}).start();
    	
    	
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
}