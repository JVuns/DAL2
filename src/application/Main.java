package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			primaryStage.setTitle("Automatic driver logger");	
			
			Parent homeRoot = FXMLLoader.load(getClass().getResource("Sample.fxml"));

			Scene home = new Scene(homeRoot);
			
			primaryStage.setScene(home);
			primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
