package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fxapp.Main;

/**
 * Controller superclass. Can either change the current dialog or display a dialog in a new screen.
 * 
 * @author Jonathan Chen
 */
public abstract class Controller {

    /**
     * Displays a dialog in the same window.
     * 
     * @param path the relative path to the FXML to be loaded
     * @return the controller associated with the dialog
     */
    protected Controller showScreen(String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(path));

            Parent root = loader.load();
            Stage mainStage = Main.stage();
            mainStage.setScene(new Scene(root, Main.primaryScreenBounds.getWidth(), Main.primaryScreenBounds.getHeight()));
            mainStage.getIcons().add(new Image("file:water.png"));
            mainStage.show();

            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Displays a dialog in the same window.
     * 
     * @param path the relative path to the FXML to be loaded
     * @param title the title of the new window
     * @return the controller associated with the dialog
     */
    protected Controller showScreen(String path, String title) {
        Controller controller = showScreen(path);
        Main.stage().setTitle(title);
        return controller;
    }

    /**
     * Creates a dialog in a new window but does not show it.
     * 
     * @param path the relative path to the FXML to be loaded
     * @return the controller associated with the dialog
     */
    private DialogController createDialog(String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(path));
            Pane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.getIcons().add(new Image("file:water.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.stage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            return controller;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Displays a dialog in a new window.
     * 
     * @param path the relative path to the FXML to be loaded
     * @return the controller associated with the dialog
     */
    protected DialogController showDialog(String path) {
        DialogController controller = createDialog(path);
        controller.dialogStage.show();
        return controller;
    }

    /**
     * Displays a dialog in a new window.
     * 
     * @param path the relative path to the FXML to be loaded
     * @param title the title of the new window
     * @return the controller associated with the dialog
     */
    protected DialogController showDialog(String path, String title) {
        DialogController controller = showDialog(path);
        controller.stage().setTitle(title);
        return controller;
    }

    /**
     * Displays a dialog in a new window and waits for the dialog to be closed.
     * 
     * @param path
     *            the relative path to the FXML to be loaded
     * @return the controller associated with the dialog
     */
    protected DialogController showDialogAndWait(String path) {
        DialogController controller = createDialog(path);
        controller.dialogStage.showAndWait();
        return controller;
    }

    /**
     * Displays a dialog in a new window and waits for the dialog to be closed.
     * 
     * @param path the relative path to the FXML to be loaded
     * @param title the title of the new window
     * @return the controller associated with the dialog
     */
    protected DialogController showDialogAndWait(String path, String title) {
        DialogController controller = createDialog(path);
        controller.dialogStage.setTitle(title);
        controller.dialogStage.showAndWait();
        return controller;
    }
}