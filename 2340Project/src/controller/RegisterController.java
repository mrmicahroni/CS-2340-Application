package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.AuthorizationLevel;
import model.User;
import database.Model;
import fxapp.Main;

/**
 * Controller for the Register Dialog.
 * @author Alok Tripathy
 */
public class RegisterController extends DialogController {

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> authBox;

    private User user;

    /**
     * Loads all authorization levels into the authorization ComboBox on launch.
     */
    @FXML
    private void initialize() {
        for (AuthorizationLevel level : AuthorizationLevel.values()) {
            authBox.getItems().add(level.getName());
        }
        user = new User();
    }

    /**
     * Handler for when the OK button on the register dialog is clicked.
     */
    @FXML
    public void handleOKPressed() {
        user.setName(userField.getText());
        user.setPassword(passwordField.getText());
        String auth = authBox.getSelectionModel().getSelectedItem().toString();
        for (AuthorizationLevel authLevel : AuthorizationLevel.values()) {
            if (auth.equals(authLevel.getName())) {
                user.setAuth(authLevel);
                break;
            }
        }

        if (Model.instance().checkUserExists(user.getName())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("User already exists");
            alert.setHeaderText("User already exists");
            alert.setContentText("A user with this username already exists");
            alert.showAndWait();
        } else if(!Model.instance().addUser(user)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("Error adding user");
            alert.setHeaderText("Error adding user");
            alert.setContentText("An error occurred while attempting to add the user");
            alert.show();
        }

        dialogStage.close();
    }

    /**
     * Handler for when the cancel button on the register dialog is clicked.
     */
    @FXML
    public void handleCancelPressed() {
        dialogStage.close();
    }
}