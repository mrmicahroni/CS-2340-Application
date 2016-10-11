package fxapp;

import java.io.IOException;

import com.sun.prism.Image;

import controller.*;
import model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

/**
 * Entry point into the application.
 */
public class Main extends Application {

	private static Stage mainStage;
	
	/**
	 * @return the single instance of the main stage.
	 */
	public static Stage stage() {
	    return mainStage;
	}
	
	public static final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

	@Override
	public void start(Stage stage) throws Exception {
		mainStage = stage;

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/WelcomeScreen.fxml"));

        Parent root = loader.load();
        stage.setScene(new Scene(root, Main.primaryScreenBounds.getWidth(), Main.primaryScreenBounds.getHeight()));
        stage.setMaximized(true);
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
