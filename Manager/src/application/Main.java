package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		
		try {
			
			Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
			
			Scene scene = new Scene(parent);
			
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			
		} catch(Exception e) {
			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
