package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import model.AuthorizationLevel;

/**
 * 
 * Controller for the Register Dialog.
 * @author Alok Tripathy
 *
 */
public class RegisterController {

	@FXML
	private TextField userField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private ComboBox authBox;
	
	private Stage _dialogStage;
	
	private Main mainApplication;
	
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
	}
	
	public void setMainApp(Main main) {
		mainApplication = main;
	}
	
	/**
	 * Loads all authorization levels into auth ComboBox on launch.
	 */
	@FXML
	private void initialize() {
		for (AuthorizationLevel level : AuthorizationLevel.values()) {
			authBox.getItems().add(level.getName());
		}
	}
	
	@FXML
	public void handleOKPressed() {
		
		/* TODO insert user data into database */
		
	}
	
	@FXML
	public void handleCancelPressed() {
		_dialogStage.close();
	}
}
