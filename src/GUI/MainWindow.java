package GUI;

import data.files.JsonManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {
    HBox layoutTop;
    VBox layoutLeft;
    BorderPane layout;

    Scene welcomeScene;
    JsonManager jm;

    @Override
    public void start(Stage primaryStage) throws Exception{
        layoutLeft = new VBox();
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
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
