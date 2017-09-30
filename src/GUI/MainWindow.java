package GUI;

import data.files.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainWindow extends Application {
    /*HBox layoutTop;
    VBox layoutLeft;
    BorderPane layout;

    Scene welcomeScene;
    JsonManager jm;*/

    // Creating a static root to pass to the controller
    private static BorderPane root = new BorderPane();

    /**
     * Just a root getter for the controller to use
     */
    public static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        DBList list = new DBList();

        // loading FXML resources
        URL treeMenuUrl = getClass().getResource("treeMenu.fxml");
        TreeView<String> treeMenu = FXMLLoader.load(treeMenuUrl);

        URL welcomeWindowUrl = getClass().getResource("welcomeWindow.fxml");
        AnchorPane welcomeWindow = FXMLLoader.load(welcomeWindowUrl);

        URL menuBarUrl = getClass().getResource("MenuBar.fxml");
        MenuBar menuBar = FXMLLoader.load(menuBarUrl);


        // constructing our scene using the static root
        root.setTop(menuBar);
        root.setCenter(welcomeWindow);
        root.setLeft(treeMenu);

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("GUI/flat_ui.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Linked BD");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
