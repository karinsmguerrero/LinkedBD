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

    public MainWindow() throws IOException {
    }

    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
    BorderPane root = loader.load();

    @Override
    public void start(Stage primaryStage) throws Exception{

        // loading FXML resources

        URL centralPane = getClass().getResource("welcomeWindow.fxml");
        AnchorPane welcomeWindow = FXMLLoader.load(centralPane);


        // constructing our scene using the root
        root.setCenter(welcomeWindow);

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("GUI/flat_ui.css");

        primaryStage.setScene(scene);
        primaryStage.show();
        /*layoutLeft = new VBox();
        TreeMenu folderTree = new TreeMenu();
        TreeView<String> treeMenu = folderTree.createTree();
        VBox.setVgrow(treeMenu, Priority.ALWAYS);
        layoutLeft.getChildren().addAll(treeMenu);

        layout = new BorderPane();
        layout.setLeft(layoutLeft);
        jm = new JsonManager("UNED", "cursos");
        TableView tableView = new TableView(jm.JsonToList());
        layout.setCenter(tableView.addTable());
        OptionMenu menu = new OptionMenu();
        layout.setTop(menu.createMenuBar());

        welcomeScene = new Scene(layout, 1300, 600);
        welcomeScene.getStylesheets().add("GUI/flat_ui.css");
        primaryStage.setTitle("Linked BD");
        primaryStage.setScene(welcomeScene);
        //primaryStage.setMaximized(true);
        primaryStage.show();*/
    }

    public BorderPane getRoot() {
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
