package GUI;

import configuration.Setting;
import data.files.JsonManager;
import data.files.RowMaker;
import data.generics.structures.SimpleList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MainWindow extends Application {
    HBox layoutTop;
    VBox layoutLeft;
    BorderPane layout;

    Scene welcomeScene;
    JsonManager jm;

    @Override
    public void start(Stage primaryStage) throws Exception{
        layoutLeft = new VBox();
        Tree folderTree = new Tree();
        TreeView<String> treeMenu = folderTree.createTree();
        VBox.setVgrow(treeMenu, Priority.ALWAYS);
        layoutLeft.getChildren().addAll(treeMenu);

        layout = new BorderPane();
        layout.setLeft(layoutLeft);
        jm = new JsonManager("UNED", "cursos");
        Table table = new Table(jm.JsonToList());
        layout.setCenter(table.addTable());
        OptionMenu menu = new OptionMenu();
        layout.setTop(menu.createMenuBar());

        welcomeScene = new Scene(layout);
        welcomeScene.getStylesheets().add("GUI/flat_ui.css");
        primaryStage.setTitle("Linked BD");
        primaryStage.setScene(welcomeScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
